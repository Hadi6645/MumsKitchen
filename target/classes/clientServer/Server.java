package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import entities.*;
import control.ReportRequest;
import control.ServerInstruction;
import control.ServerInstructionType;
import enums.ComplaintStatus;
import enums.EmployeeRole;
import enums.FoodOrder_Status;
import enums.Status;
import enums.UpdateMenuRequest_Status;
import server.ocsf.server.AbstractServer;
import server.ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	
	private static Session session;
	private static SessionFactory sessionFactory;
	
	public Server(int port) {
		super(port);
	}
	
	public static Session getSession()
	{
		return session;
	}
	private SessionFactory getSessionFactory() throws HibernateException
	{
		Configuration configuration= new Configuration();
		//configuration.addPackage("entities");
		//configuration.addPackage("control");
		configuration.addAnnotatedClass(Meal.class);
		configuration.addAnnotatedClass(BaseMenu.class);
		configuration.addAnnotatedClass(Dessert.class);
		configuration.addAnnotatedClass(Food.class);
		configuration.addAnnotatedClass(Ingredients.class);
		configuration.addAnnotatedClass(Meal.class);
		configuration.addAnnotatedClass(Drink.class);
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(EmployeeRole.class);
		configuration.addAnnotatedClass(Company.class);
		configuration.addAnnotatedClass(Restaurant.class);
		configuration.addAnnotatedClass(Address.class);
		configuration.addAnnotatedClass(OpeningHours.class);
		configuration.addAnnotatedClass(DiningSpace.class);
		configuration.addAnnotatedClass(RestaurantMenu.class);
		configuration.addAnnotatedClass(table.class);
		configuration.addAnnotatedClass(Menu.class);
		ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	
	private void initializeDatabase() {
		try{
		    sessionFactory= this.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			App.generateIngredient();
			App.generateMeals();
			App.generateDrinks();
			App.generateDesserts();
			App.generateBaseMenu();
			App.generateEmployees();
			App.generateCompany(); //important to keep in that order.
			App.generateTables();
			App.generateDiningspace();
			App.generateRestaurants();
			
			session.getTransaction().commit();
		} catch(Exception exception) {
			if(session != null)
			{
				session.getTransaction().rollback();
			}
			System.err.println("An error occured - cannot initialize database, changes have been rolled back.");
			exception.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	private int addToDatabase(Object data) {
		try {
			int id = (int) session.save(data);
			session.flush();
			return id;
		} catch(Exception exception) {
			if(session != null)
			{
				session.getTransaction().rollback();
			}
			System.err.println("An error occured- cannot add to database, changes have been rolled back.");
			exception.printStackTrace();
			return -1;
		} 
	}
	
	private int updateInDatabase(Object data)
	{
		return this.addToDatabase(data);
	}
	
	private List<Reservation> filterReservations(List<Reservation> reservations, LocalDateTime day)
	{
		 LocalDateTime begin = day.toLocalDate().atStartOfDay(); // 00:00:00
		 LocalDateTime end = begin.plusHours(24).minusSeconds(1); // 23:59:59
		 return reservations.stream().filter(resv -> resv.getTime().isAfter(begin) && resv.getTime().isBefore(end))
		    	 .collect(Collectors.toList());
	}
	
	private List<Reservation> filterReservationsByMonth(List<Reservation> reservations, LocalDate month)
	{
		LocalDateTime begin = month.withDayOfMonth(1).atStartOfDay();
		LocalDateTime end = month.withDayOfMonth(month.lengthOfMonth()).atTime(23,59,59); //end of the day
		return reservations.stream().filter(resv -> resv.getTime().isAfter(begin) && resv.getTime().isBefore(end))
		    	 .collect(Collectors.toList());
	}
	
	private List<Reservation> getReservationsByMonth(int resID, LocalDateTime month)
	{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reservation> query= builder.createQuery(Reservation.class);
	    Root<Reservation> reservations = query.from(Reservation.class);

	    //Constructing list of parameters
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    predicates.add(builder.equal(reservations.get("restaurantId"), resID));
	    //query itself
	    query.select(reservations).where(predicates.toArray(new Predicate[]{}));
	    //execute query and do something with result
	    List<Reservation> result = session.createQuery(query).getResultList();
	    //add filter
	    LocalDate tmpMonth = month.toLocalDate();
	    return filterReservationsByMonth(result, tmpMonth);
	}
	
	private List<Reservation> getReservations(int resID, int spaceID,  LocalDateTime day)
	{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reservation> query= builder.createQuery(Reservation.class);
	    Root<Reservation> reservations = query.from(Reservation.class);

	    //Constructing list of parameters
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    predicates.add(builder.equal(reservations.get("restaurantId"), resID));
	    predicates.add(builder.equal(reservations.get("diningSpaceId"), spaceID));
	    //query itself
	    query.select(reservations).where(predicates.toArray(new Predicate[]{}));
	    //execute query and do something with result
	    List<Reservation> result = session.createQuery(query).getResultList();
	    //add filter
	    return filterReservations(result, day);
	}
	
	private List<table> getReservationTables(Reservation reservation, List<Reservation> allDayReservations, LocalDateTime time){
		if(reservation == null) {
			System.err.println("error in getResevationTables,reservation is null .\n");
			return Collections.emptyList();//not the best thing!
		}
		DiningSpace space = reservation.getSpace();
		List<Reservation> reservations = allDayReservations;
		List<table> availableTables = space.getTables();
		LocalDateTime time1 =  time.plusHours(2);
		LocalDateTime time2 =  time.plusHours(-2);
		reservations = reservations.stream().filter(resv -> resv.getTime().isAfter(time2)  && resv.getTime().isBefore(time1)).collect(Collectors.toList());
		
		for(int i=0; i< reservations.size(); i++)
		{
			reservations.get(i).getTables().forEach(table -> {
				availableTables.remove(table); //*** not sure if it works
			});
		}
		
		return getOptimalTables(availableTables, reservation.getGuestsNumber());
	}
	
	
	private List<List<table>> getReservationTablesList(Object data){
		Reservation reservation = (Reservation)(data);
		if(reservation == null) {
			System.err.println("error in getReservationTablesList,reservation is null .\n");
			return Collections.emptyList();
		}
		Restaurant res = reservation.getRestaurant();
		OpeningHours hours = res.getOpeningHours();
		DiningSpace space = reservation.getSpace();
		LocalDateTime time = reservation.getTime();
		int day = time.getDayOfWeek().getValue();
		List<Reservation> allDayReservations = getReservations(res.getId(), space.getId(), time);
		List<List<table>> allTables =  Collections.emptyList();
		LocalTime openingTime = hours.getOpeningHours()[day][0];
		LocalTime closingTime = hours.getOpeningHours()[day][1];
		LocalDateTime firstReservation = time.withHour(openingTime.getHour()).withMinute(openingTime.getMinute()).plusMinutes(15);// add 15 minutes to opening
		LocalDateTime lastReservation = time.withHour(closingTime.getHour()).withMinute(closingTime.getMinute()).minusHours(1);// minus 1 hour for the last one
		
		LocalDateTime tmpTime = firstReservation;
		while(tmpTime.isBefore(lastReservation)) {
			allTables.add(this.getReservationTables(reservation, allDayReservations, tmpTime));
			tmpTime = tmpTime.plusMinutes(15); // check every 15 minutes
		}
		
		return allTables;
	}
	
	private List<table> getOptimalTables(List<table> availableTables, int guests)
	{
		int sum =0;
		Collections.sort(availableTables, (t1, t2) -> {
			return t2.getCapacity() - t1.getCapacity();
		});
		for(int i=0;i < availableTables.size(); i++)
		{
			sum+= availableTables.get(i).getCapacity();
		}
		List<table> optimal = Collections.emptyList();
		if(sum < guests) {
			return optimal;
		}
		// assume avTables are sorted by 4-3-2
		for(int i=0;i < availableTables.size(); i++)
		{
			table table = availableTables.get(i);
			int cap = table.getCapacity();
			if (cap == guests) {
				optimal.add(table);
				guests -= cap;
				break; //end the for
			}
			if(cap < guests) {
				optimal.add(table);
				guests -= cap;
			}
		}
		if(guests>0) {
			optimal.add(availableTables.get(availableTables.size()-1));
		}
		return optimal;
	}
	
	private boolean checkReservationIsPossible(Object data){
		Reservation reservation = (Reservation)(data);
		if(reservation == null) {
			System.err.println("error in checkReservationIsPossible,reservation is null .\n");
			return false;
		}
		Restaurant res = reservation.getRestaurant();
		DiningSpace space = reservation.getSpace();
		LocalDateTime time = reservation.getTime();
		List<Reservation> allDayReservations = getReservations(res.getId(), space.getId(), time);
		List<table> tables = this.getReservationTables(reservation, allDayReservations, time);
		return !tables.isEmpty();
	}
	
	private List<FoodOrder> filterFoodOrdersByMonth(List<FoodOrder> orders, LocalDate month)
	{
		LocalDateTime begin = month.withDayOfMonth(1).atStartOfDay();
		LocalDateTime end = month.withDayOfMonth(month.lengthOfMonth()).atTime(23,59,59); //end of the day
		return orders.stream().filter(order -> order.getTime().isAfter(begin) && order.getTime().isBefore(end))
		    	 .collect(Collectors.toList());
	}
	
	private List<FoodOrder> getFoodOrdersByMonth(int resID, LocalDateTime month)
	{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FoodOrder> query= builder.createQuery(FoodOrder.class);
	    Root<FoodOrder> orders = query.from(FoodOrder.class);

	    //Constructing list of parameters
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    predicates.add(builder.equal(orders.get("restaurantId"), resID));//TODO this is wrong
	    //query itself
	    query.select(orders).where(predicates.toArray(new Predicate[]{}));
	    //execute query and do something with result
	    List<FoodOrder> result = session.createQuery(query).getResultList();
	    //add filter
	    LocalDate tmpMonth = month.toLocalDate();
	    return filterFoodOrdersByMonth(result, tmpMonth);
	}
	
	private boolean checkEmployeeExists(Object data) {
		String[] info = (String[])(data);
		if(info == null) {
			// log error
			return false;
		}
		// check in database
		Criteria criteria = session.createCriteria(Employee.class);
		Employee emp = (Employee) criteria.add(Restrictions.eq("id", info[0])).uniqueResult();

		//Employee emp =  (Employee)session.get(Employee.class, info[0]);
		if(emp == null || !emp.getPassword().equals(info[1]) || emp.getIsLoggedIn()) { // if not found or wrong password
			return false;
		}
		// update the employee login status
		emp.setIsLoggedIn(true);
		this.addToDatabase(emp);
		//return true
		return true;
	}
	
	private Restaurant getRestaurant(Object data) {
		int restId =(int)data;
		if(restId <= 0) {
			// log error
			return null;
		}
		// check in database
		Criteria criteria = session.createCriteria(Restaurant.class);
		Restaurant restaurant = (Restaurant) criteria.add(Restrictions.eq("id", restId)).uniqueResult();
		return restaurant;
	}
	
	private int addNewMenu(Object data)
	{
		Menu menu = (Menu)data;
		if(menu == null) {
			return -1;
		}
		Menu toSave = menu;
		toSave.setallfood(toSave.getallfood());
		return this.addToDatabase(toSave);
	}
	
	private int addNewFoodOrder(Object data)
	{
		FoodOrder order = (FoodOrder)data;
		if(order == null) {
			return -1;
		}
		Customer customer = order.getClient();
		customer.setId(this.addToDatabase(customer));
		Menu menu = order.getOrder();
		menu.setId(this.addNewMenu(menu));
		Transaction transaction = order.getPayment();
		transaction.setId(this.addToDatabase(transaction));
		FoodOrder toSave = new FoodOrder(menu,order.getTotalPrice(),customer,order.getShippingFee(),transaction);
		toSave.setAllRelations(customer,menu,transaction);
		return this.addToDatabase(toSave);
	}
	
	private float[] cancelFoodOrder(Object data)
	{
		int foodOrderId = (int)data;
		float[] arr = {-1,-1};
		if(foodOrderId <= 0) {
			return arr; // -1 , -1
		}
		// check in database
		Criteria criteria = session.createCriteria(FoodOrder.class);
		FoodOrder fOrder = (FoodOrder) criteria.add(Restrictions.eq("id", foodOrderId)).uniqueResult();
		if(fOrder == null) { // if not found or wrong password
			return arr;
		}
		float moneyBack = fOrder.getTotalPrice();
		// check the hour
		LocalDateTime current = LocalDateTime.now();
		LocalDateTime minus3 = fOrder.getTime().minusHours(3);
		LocalDateTime minus1 = fOrder.getTime().minusHours(1);
		if (current.isAfter(minus3)) { //else stays full price
			if(current.isBefore(minus1)) {
				moneyBack = moneyBack / 2;
			} else {
				moneyBack = 0;
			}
		}
		arr[0] = moneyBack;
		arr[1] = fOrder.getTotalPrice();
		// cancel the order -> update the status
		fOrder.setStatus(FoodOrder_Status.CANCELED);
		this.updateInDatabase(fOrder);
		return arr;
	}
	
	private int cancelReservation(Object data)
	{
		int reservationId = (int)data;
		int penalty = -1;
		if(reservationId <= 0) {
			return penalty; // -1 , -1
		}
		// check in database
		Criteria criteria = session.createCriteria(Reservation.class);
		Reservation resv = (Reservation) criteria.add(Restrictions.eq("id", reservationId)).uniqueResult();
		if(resv == null) { // if not found or wrong password
			return penalty;
		}
		// check the hour
		LocalDateTime current = LocalDateTime.now();
		LocalDateTime minus1 = resv.getTime().minusHours(1);
		if(current.isBefore(minus1)) {
			penalty = 0;
		} else {
			penalty = resv.getGuestsNumber() * 10;
		}
		// cancel the reservations -> update the status
		resv.setStatus(Status.CANCELED);
		this.updateInDatabase(resv);
		return penalty;
	}
	
	private boolean addComplaint(Object data)
	{
		Complaint complaint = (Complaint)data;
		if(complaint == null) {
			return false;
		}
		Customer customer = complaint.getClient();
		customer.setId(this.addToDatabase(customer));
		int id = addToDatabase(complaint);
		return id > 0;
		
	}
	
	private List<Complaint> getComplaints()
	{
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Complaint> query= builder.createQuery(Complaint.class);
		query.from(Restaurant.class);
		List<Complaint> data= session.createQuery(query).getResultList();
		return data;
	}
	
	private List<Complaint> getActiveComplaints()
	{
		return this.getComplaints().stream().filter(comps -> comps.getComplaintStatus() == ComplaintStatus.ACTIVE).collect(Collectors.toList());
	}
	
	private List<Complaint> filterComplaintsByMonth(List<Complaint> complaints,LocalDateTime month)
	{
		LocalDate tmpMonth = month.toLocalDate();
		LocalDateTime begin = tmpMonth.withDayOfMonth(1).atStartOfDay();
		LocalDateTime end = tmpMonth.withDayOfMonth(tmpMonth.lengthOfMonth()).atTime(23,59,59); //end of the day
		return complaints.stream().filter(comps -> comps.getTime().isAfter(begin) && comps.getTime().isBefore(end)).collect(Collectors.toList());
	}
	
	private List<Complaint> getComplaintsByMonth(LocalDateTime month)
	{
		return this.filterComplaintsByMonth(this.getComplaints(), month);
	}
	
	private List<Complaint> getComplaintsByRestaurant(int restaurantId)
	{
		return this.getComplaints().stream().filter(comps -> comps.getRestaurant()!=null && comps.getRestaurant().getId() == restaurantId)
				.collect(Collectors.toList());
	}
	
	private List<Complaint> getComplaintsByRestaurantAndMonth(int restaurantId, LocalDateTime month)
	{
		List<Complaint> comps = this.getComplaintsByRestaurant(restaurantId);
		return this.filterComplaintsByMonth(comps, month);
	}
	
	private boolean updateComplaint(Object data)
	{
		Complaint complaint = (Complaint)data;
		if(complaint == null) {
			return false;
		}
		int id = this.updateInDatabase(complaint);
		return id == complaint.getId();
	}
	
	private boolean addNewUpdateMenuRequest(Object data)
	{
		UpdateMenuRequest request = (UpdateMenuRequest)data;
		if(request == null) {
			return false;
		}
		Menu menu = request.getMenu();
		menu.setId(this.addNewMenu(menu));
		UpdateMenuRequest toSave = new UpdateMenuRequest(menu,request.getStatus());
		toSave.setAllRelations(menu);
		int rId = this.addToDatabase(toSave);
		return rId > 0;
	}
	
	private boolean updateUpdateMenuRequest(Object data)
	{
		// TODO continue
		UpdateMenuRequest request = (UpdateMenuRequest)data;
		if(request == null) {
			return false;
		}
		if(request.getStatus()==UpdateMenuRequest_Status.APPROVED) {
			// update menu
			//get menu by id -> change the meals / drinks /....
			// set everything 
			//addToDatabse
		}
		updateInDatabase(request);
		return true;
	}
	
	private boolean logOut(Object data)
	{
		Employee employee = (Employee)data;
		if(employee == null) {
			return false;
		}
		employee.setIsLoggedIn(false);
		int empId = this.updateInDatabase(employee); //update
		return empId == employee.getId();
	}
	
	private List<NumberByDate> sortNumbersByDate(List<NumberByDate> numbers)
	{
		Collections.sort(numbers, (n1, n2) -> {
			return n2.getDate().isAfter(n1.getDate()) ? 1 : -1; //n2-n1
		});
		return numbers;
	}
	
	private DeniedClientReprt getDeniedCustomersReport(Object data)
	{
		ReportRequest request = (ReportRequest)data;
		if(request == null) {
			return null; // change this
		}
		int restaurantId = request.getRestaurantId();
		Month month = request.getMonth();
		LocalDateTime time = LocalDateTime.now().withMonth(month.getValue()); //this year with this month
		List<Reservation> deniedReservations = this.getReservationsByMonth(restaurantId, time).stream().filter(resv -> resv.getStatus() == Status.DENIED)
				.collect(Collectors.toList());
		List<NumberByDate> numbers = Collections.emptyList();//not the best thing!
		// sort the denied reservations by date first
		Collections.sort(deniedReservations, (d1, d2) -> {
			return d2.getTime().isAfter(d1.getTime()) ? 1 : -1; //n2-n1
		});
		LocalDateTime tmp = null;
		int counter = 0;
		for(int i=0; i< deniedReservations.size();i++) {
			Reservation resv = deniedReservations.get(i);
			LocalDateTime date = resv.getTime().toLocalDate().atStartOfDay();
			if (tmp == null) {
				tmp = date;
			}
			if(!date.isEqual(tmp)) { //date!=tmp
				numbers.add(new NumberByDate(tmp.toLocalDate(), counter));
				counter = 0;
				tmp = date;
			}
			counter++;
		}
		//check the last one!
		numbers.add(new NumberByDate(tmp.toLocalDate(), counter));
		numbers = this.sortNumbersByDate(numbers);
		DeniedClientReprt report = new DeniedClientReprt(restaurantId,LocalDateTime.now(),numbers);
		return report;
	}
	
	private List<DeniedClientReprt> getAllDeniedCustomerReports(Object data)
	{
		ReportRequest mainRequest = (ReportRequest)data;
		if(mainRequest == null) {
			return null; // change this
		}
		Month month = mainRequest.getMonth();
		List<Restaurant> restaurants = getRestaurauntsFromDB();
		List<DeniedClientReprt> reports = Collections.emptyList();
		restaurants.forEach(rest -> {
			reports.add(this.getDeniedCustomersReport(new ReportRequest(rest.getId(), month)));
		});
		return reports;
	}
	
	private FoodOrderReport getFoodOrderReport(Object data)
	{
		ReportRequest request = (ReportRequest)data;
		if(request == null) {
			return null; // change this
		}
		int restaurantId = request.getRestaurantId();
		Month month = request.getMonth();
		LocalDateTime time = LocalDateTime.now().withMonth(month.getValue()); //this year with this month
		List<FoodOrder> orders = this.getFoodOrdersByMonth(restaurantId, time);
		List<NumberByDate> numbers = Collections.emptyList();//not the best thing!
		// sort the denied reservations by date first
		Collections.sort(orders, (d1, d2) -> {
			return d2.getTime().isAfter(d1.getTime()) ? 1 : -1; //n2-n1
		});
		LocalDateTime tmp = null;
		int counter = 0;
		float totalPrice = 0;
		for(int i=0; i< orders.size();i++) {
			FoodOrder order = orders.get(i);
			LocalDateTime date = order.getTime().toLocalDate().atStartOfDay();
			if (tmp == null) {
				tmp = date;
			}
			if(!date.isEqual(tmp)) { //date!=tmp
				numbers.add(new NumberByDate(tmp.toLocalDate(), counter));
				counter = 0;
				tmp = date;
			}
			counter++;
			totalPrice += order.getTotalPrice();
		}
		//check the last one!
		numbers.add(new NumberByDate(tmp.toLocalDate(), counter));
		numbers = this.sortNumbersByDate(numbers);
		FoodOrderReport report = new FoodOrderReport(restaurantId,LocalDateTime.now(),numbers,totalPrice);
		return report;
	}
	
	private List<FoodOrderReport> getAllFoodOrderReports(Object data)
	{
		ReportRequest mainRequest = (ReportRequest)data;
		if(mainRequest == null) {
			return null; // change this
		}
		Month month = mainRequest.getMonth();
		List<Restaurant> restaurants = getRestaurauntsFromDB();
		List<FoodOrderReport> reports = Collections.emptyList();
		restaurants.forEach(rest -> {
			reports.add(this.getFoodOrderReport(new ReportRequest(rest.getId(), month)));
		});
		return reports;
	}
	
	private ComplaintsReport getComplaintsReport(Object data)
	{
		ReportRequest request = (ReportRequest)data;
		if(request == null) {
			return null; // change this
		}
		int restaurantId = request.getRestaurantId();
		Month month = request.getMonth();
		LocalDateTime time = LocalDateTime.now().withMonth(month.getValue()); //this year with this month
		List<Complaint> complaints = this.getComplaintsByRestaurantAndMonth(restaurantId, time);
		List<NumberByDate> numbers = Collections.emptyList();//not the best thing!
		// sort the denied reservations by date first
		Collections.sort(complaints, (c1, c2) -> {
			return c2.getTime().isAfter(c1.getTime()) ? 1 : -1; //n2-n1
		});
		LocalDateTime tmp = null;
		int counter = 0;
		for(int i=0; i< complaints.size();i++) {
			Complaint comp = complaints.get(i);
			LocalDateTime date = comp.getTime().toLocalDate().atStartOfDay();
			if (tmp == null) {
				tmp = date;
			}
			if(!date.isEqual(tmp)) { //date!=tmp
				numbers.add(new NumberByDate(tmp.toLocalDate(), counter));
				counter = 0;
				tmp = date;
			}
			counter++;
		}
		//check the last one!
		numbers.add(new NumberByDate(tmp.toLocalDate(), counter));
		numbers = this.sortNumbersByDate(numbers);
		ComplaintsReport report = new ComplaintsReport(restaurantId,LocalDateTime.now(),numbers);
		return report;
	}
	
	private List<ComplaintsReport> getAllComplaintReports(Object data)
	{
		ReportRequest mainRequest = (ReportRequest)data;
		if(mainRequest == null) {
			return null; // change this
		}
		Month month = mainRequest.getMonth();
		List<Restaurant> restaurants = getRestaurauntsFromDB();
		List<ComplaintsReport> reports = Collections.emptyList();
		restaurants.forEach(rest -> {
			reports.add(this.getComplaintsReport(new ReportRequest(rest.getId(), month)));
		});
		return reports;
	}
	
	private List<Report> getRestaurantReports(Object data)
	{
		ReportRequest request = (ReportRequest)data;
		if(request == null) {
			return null; // change this
		}
		List<Report> reports = Collections.emptyList();
		reports.add(this.getDeniedCustomersReport(request));
		reports.add(this.getFoodOrderReport(request));
		reports.add(this.getComplaintsReport(request));
		return reports;
	}
	
	private List<List<Report>> getAllRestaurantReports(Object data)
	{
		ReportRequest request = (ReportRequest)data;
		if(request == null) {
			return null; // change this
		}
		Month month = request.getMonth();
		List<Restaurant> restaurants = this.getRestaurauntsFromDB();
		List<List<Report>> allReports = Collections.emptyList();
		restaurants.forEach(restaurant -> {
			ReportRequest req = new ReportRequest(restaurant.getId(), month);
			allReports.add(this.getRestaurantReports(req));
		});
		return allReports;
	}
	
	private Restaurant getRestaurantByEmployee(Object data)
	{
		String employeeId = (String)data;
		if(employeeId==null) {
			return null;
		}
		// not efficient but hay \-/
		List<Restaurant> restaurants = this.getRestaurauntsFromDB();
		Restaurant rest = null;
		for(int i=0;i<restaurants.size();i++) {
			rest = restaurants.get(i);
			List<Employee> list = rest.getStaff().stream().filter(employee -> employee.getId() == employeeId).collect(Collectors.toList());
			if(list != null && !list.isEmpty()) {
				return rest;
			}
		}
		return null;
	}
	
	private boolean updateTavSagol(Object data) /////// continue
	{
		//TODO continue
		return false;
	}
	
	private List<Restaurant> getRestaurauntsFromDB()
	{
		List<Restaurant> restaurants;
		restaurants = App.getAllRestaurants();
		return restaurants;
	}
	private List<Food> getMenuFromDB(Object res) 
	{
		int restid = (int) res;
		return App.getRestaurantFood(restid);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// we need a switch
		ServerInstruction sInstruction = (ServerInstruction)(msg);
		if (sInstruction == null) {
			System.err.println("cannot handle message from client, sInstruction is null.\n");
			sendResponseToClient(null, client);
			return;
		}
		
		ServerInstructionType instruction = sInstruction.getInstruction();
		Object data = sInstruction.getData();
		Object response = null;
		if ( instruction == null) {
			System.err.println("cannot handle message from client, instruction is null.\n");
			sendResponseToClient(null, client);
			return;
		}
		session = sessionFactory.openSession();
		session.beginTransaction();
		switch(instruction) {
			case CHECK_EMPLOYEE_EXISTS: response = this.checkEmployeeExists(data);
				break;
			case GET_RESTAURANTS_LIST: response = this.getRestaurauntsFromDB();
				break;
			case GET_MENU: response = this.getMenuFromDB(data);
				break;
			case CHECK_UNRESERVED_TABLES: response = this.checkReservationIsPossible(data);
			   break;
			case GET_RESERVATION_POSSIBILITIES: response = this.getReservationTablesList(data);
			   break;
			case ADD_NEW_FOOD_ORDER: response = this.addNewFoodOrder(data);
				break;
			case CANCEL_FOOD_ORDER: response = this.cancelFoodOrder(data);
				break;
			case CANCEL_RESERVATION: response = this.cancelReservation(data);
				break;
			case ADD_COMPLAINT: response = this.addComplaint(data);
				break;
			case GET_ACTIVE_COMPLAINTS: response = this.getActiveComplaints();
				break;
			case UPDATE_COMPLAINT: response = this.updateComplaint(data);
				break;
			case UPDATE_TAV_SAGOL: response = this.updateTavSagol(data);
				break;
			case ADD_NEW_UPDATE_MENU_REQUEST: response = this.addNewUpdateMenuRequest(data);
				break;
			case UPDATE_UPDATE_MENU_REQUEST: response = this.updateUpdateMenuRequest(data);
				break;
			case LOG_OUT: response = this.logOut(data);
				break;
			case GET_DENIED_CUSTOMERS_REPORT: response = this.getDeniedCustomersReport(data);
				break;
			case GET_ALL_DENIED_CUSTOMERS_REPORTS: response = this.getAllDeniedCustomerReports(data);
				break;
			case GET_FOOD_ORDERS_REPORT: response = this.getFoodOrderReport(data);
				break;
			case GET_ALL_FOOD_ORDERS_REPORT: response = this.getAllFoodOrderReports(data);
				break;
			case GET_COMPLAINTS_REPORT: response = this.getComplaintsReport(data);
				break;
			case GET_ALL_COMPLAINTS_REPORT: response = this.getAllComplaintReports(data);
				break;
			case GET_RESTAURANT_REPORTS: response = this.getRestaurantReports(data);
				break;
			case GET_ALL_RESTAURANTS_REPORTS: response = this.getAllRestaurantReports(data);
				break;
			case GET_RESTAURANT_BY_EMPLOYEE: response = this.getRestaurantByEmployee(data);
				break;
			default:
				break;
		}
		session.close();
		sendResponseToClient(response, client);
	}
	
	private void sendResponseToClient(Object response,ConnectionToClient client)
	{
		try {
			client.sendToClient(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("cannot send respone to client.\n");
			e.printStackTrace();
		}
	}
	
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		// TODO Auto-generated method stub
		
		System.out.println("Client Disconnected.");
		super.clientDisconnected(client);
	}
	
	

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		System.out.println("Client connected: " + client.getInetAddress());
	}
	public static boolean authchange(Food food) throws IOException
	{
		String str;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Authenticate change for food?\n");
		str=reader.readLine(); 
		if(str.equalsIgnoreCase("yes"))
			return true;
		else return false;
	}
	public static void main(String[] args) throws IOException {
		//Server server = new Server(3000); //change this port to something else if you want, but remember to update the client's constructor
		
		if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			Server server = new Server(Integer.parseInt(args[0]));
			server.initializeDatabase();
			System.out.println("Data Fetched, Server On!");
			server.listen();
		}
	}
	
	public void runTest(Server server) {
		LocalDateTime date =  LocalDateTime.now();
		Reservation resv= new Reservation();
		server.getReservationTablesList(resv);
	}
	
	
	//connection close, connection established
}
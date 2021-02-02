package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
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
import control.ServerInstruction;
import control.ServerInstructionType;
import enums.EmployeeRole;
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
			App.generateResturantMenu();
			App.generateEmployees();
			App.generateCompany(); //important to keep in that order.
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
	
	private boolean addToDatabase(Object data) {
		try {
			session.save(data);
			session.flush();
			return true;
			//finally block is executed always even if you put a return statement in the try block. 
			//The finally block will be executed before the return statement.
		} catch(Exception exception) {
			if(session != null)
			{
				session.getTransaction().rollback();
			}
			System.err.println("An error occured- cannot add to database, changes have been rolled back.");
			exception.printStackTrace();
			// The finally block will be executed before the return statement.
			return false;
		} 
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
	    LocalDateTime begin = day.toLocalDate().atStartOfDay(); // 00:00:00
	    LocalDateTime end = begin.plusHours(24).minusSeconds(1); // 23:59:59
	    return result.stream().filter(resv -> resv.getTime().isAfter(begin) && resv.getTime().isBefore(end))
	    		.collect(Collectors.toList());
	}
	
	private List<table> getReservationTables(Object data){
		Reservation reservation = (Reservation)(data);
		if(reservation == null) {
			System.err.println("error in getResevationTables,reservation is null .\n");
		}
		Restaurant res = reservation.getRestaurant();
		DiningSpace space = reservation.getSpace();
		LocalDateTime time = reservation.getTime();
		List<Reservation> reservations = getReservations(res.getId(), space.getId(), time);
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
		if(emp == null || !emp.getPassword().equals(info[1])) { // if not found or wrong password
			return false;
		}
		return true;
	}
	
	private List<Restaurant> getRestaurauntsFromDB()
	{
		List<Restaurant> restaurants;
		restaurants = App.getAllRestaurants();
		return restaurants;
	}
	private List<Food> getMenuFromDB(Object id) //CHANGE to generic later!!!!!
	{
		int restId = (int) id;
		return App.getRestaurantFood(restId);
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
		case CHECK_UNRESERVED_TABLES: response = this.getReservationTables(data);
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
	
	
	//connection close, connection established
}
package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import enums.Type;
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
			App.generateTables();
			App.generateDiningspace();
			App.generateEmployees();
			App.generateCompany(); //important to keep in that order.
			App.generateRestaurants();
			

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
			//session.flush();
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
		List<Restaurant> restaurants = null;
		restaurants = App.getAllRestaurants();
		return restaurants;
	}
	private List<Food> getMenuFromDB(Object id) //CHANGE to generic later!!!!!
	{
		int restId = (int) id;
		List<Food> food;
		food = App.getAllFood();
		
		return food;
	}

	private List<DiningSpace> getDiningSpaceFromDB() ///////reservation
	{
		List<DiningSpace> diningspace = null;
		diningspace = App.getAllDiningSpace();
		return diningspace;
		
	}
	
	private boolean checkUnreservedTables(Object data1, Object data2, Object data3) ///////reservation
	{
		LocalDateTime time = (LocalDateTime)(data1);
		LocalDateTime nextTime = time.plusHours(1);
		Type diningType = (Type)(data2);
		int capacity = (int)(data3);
		
		if(time == null || diningType == null || capacity == 0) {
			return false;
		}
		Criteria criteria = session.createCriteria(DiningSpace.class);
		//DiningSpace dining = (DiningSpace) criteria.
		DiningSpace dining = (DiningSpace) criteria.add(Restrictions.eq("type",data3)).uniqueResult();
		if( (dining.getNonReservedTables(time, nextTime)) == null || dining.getFreeSpaceCount(time, nextTime)< capacity ) { 
			return false;
		}
		
		return true;
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
		case CHECK_UNRESERVED_TABLES: response = this.checkUnreservedTables(data, data, data); ////**********************//
		   break;
		case GET_DINING_SPACE: response = this.getDiningSpaceFromDB();
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
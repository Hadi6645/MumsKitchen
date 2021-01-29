package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	
	public Server(int port) {
		super(port);
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
package clientServer;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entities.BaseMenu;
import entities.Dessert;
import entities.Drink;
import entities.Food;
import entities.Ingredients;
import entities.Meal;
import server.ocsf.server.AbstractServer;
import server.ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	
	private static boolean isRunning;
	private static Thread loopThread;
	
	private static Session session;

	public static Session getSession()
	{
		return session;
	}
	
	public static SessionFactory getSessionFactory() throws HibernateException
	{
		Configuration configuration= new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Meal.class);
		configuration.addAnnotatedClass(BaseMenu.class);
		configuration.addAnnotatedClass(Dessert.class);
		configuration.addAnnotatedClass(Food.class);
		configuration.addAnnotatedClass(Ingredients.class);
		configuration.addAnnotatedClass(Meal.class);
		configuration.addAnnotatedClass(Drink.class);
		//configuration.addAnnotatedClass(RestaurantMenu.class);
		ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	
	
	public Server(int port) throws IOException {
		super(port);
		Server.isRunning = false;
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("Received Message: " + msg.toString());
		//sendToAllClients(msg);
		listener(msg);
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
	public static void viewmenu() throws Exception
	{
		App.viewMenu();
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
	public static void listener(Object command)
	{
		
	}
	private static void  fetch() throws Exception
	{
		System.out.println("Fetching Data, Please Wait.");
		try{
			SessionFactory sessionFactory= getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			App.generateIngredient();
			App.generateMeals();
			App.generateDrinks();
			App.generateDesserts();
		}
		catch(Exception exception)
		{
			if(session != null)
			{
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		}
		finally{session.close();
		}
	}
	/*public static void loop() throws IOException {
			loopThread = new Thread(new Runnable() {

				public void run() {
					try {
						fetch();
						System.out.println("Data Fetched!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			loopThread.start();
			isRunning = true;
	}*/
	public static void main(String[] args) throws Exception {
	/*	if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			SimpleChatServer server = new SimpleChatServer(Integer.parseInt(args[0]));
			server.listen();
		
		}*/
		Server server = new Server(3002);
		
		loopThread = new Thread(new Runnable() {

			public void run() {
				try {
					
					System.out.println("Fetching Data, Please Wait.");
					try{
						SessionFactory sessionFactory= getSessionFactory();
						session = sessionFactory.openSession();
						session.beginTransaction();
						
						App.generateIngredient();
						App.generateMeals();
						App.generateDrinks();
						App.generateDesserts();
						
						server.listen();
					}
					catch(Exception exception)
					{
						if(session != null)
						{
							session.getTransaction().rollback();
						}
						System.err.println("An error occured, changes have been rolled back.");
						exception.printStackTrace();
					}
				}
					finally{session.close();
					}
					
					System.out.println("Data Fetched!");
					
				
			}
		});
		loopThread.start();
		isRunning = true;
		
	}	
}

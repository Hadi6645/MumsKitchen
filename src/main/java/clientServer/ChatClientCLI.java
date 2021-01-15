package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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


public class ChatClientCLI {
	
	private static Session session;
	
	public static Session getSession()
	{
		return session;
	}
	private static SessionFactory getSessionFactory() throws HibernateException
	{
		Configuration configuration= new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package.
		/*configuration.addAnnotatedClass(Game.class);
		configuration.addAnnotatedClass(Costumer.class);
		configuration.addAnnotatedClass(CostumerGame.class);*/
		/*configuration.addAnnotatedClass(Meal.class);
		configuration.addAnnotatedClass(BaseMenu.class);
		configuration.addAnnotatedClass(Dessert.class);
		configuration.addAnnotatedClass(Food.class);
		configuration.addAnnotatedClass(Ingredients.class);
		configuration.addAnnotatedClass(Meal.class);
		configuration.addAnnotatedClass(Drink.class);*/
		//configuration.addAnnotatedClass(RestaurantMenu.class);
		configuration.addPackage("entities");
		configuration.addPackage("control");
		ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	
	private Client client;
	private boolean isRunning;
	private static final String SHELL_STRING = "Enter message (or exit to quit)> ";
	private Thread loopThread;

	public ChatClientCLI(Client client) {
		this.client = client;
		this.isRunning = false;
	}

	public void loop() throws IOException {
		loopThread = new Thread(new Runnable() {

			public void run() {
				
				try{SessionFactory sessionFactory= getSessionFactory();
				session = sessionFactory.openSession();
				session.beginTransaction();
				
				App.generateIngredient();
				App.generateMeals();
				App.generateDrinks();
				App.generateDesserts();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String message, foodid,changeid;
				while (client.isConnected()) {
					System.out.print(SHELL_STRING);

					try {
						message = reader.readLine();
						if (message.length()==0)
							continue;
						
						if (message.equalsIgnoreCase("menu")) {
							//String[] empty = null;
							App.viewMenu();
							System.out.println("\nMenu generated successfully.");
						}
						
						if (message.equalsIgnoreCase("food")) {
							System.out.println("Enter the meal you would like to view.");
							foodid = reader.readLine();
							App.printmealbyiD(Integer.parseInt(foodid));
						}
						
						if (message.equalsIgnoreCase("change")) {
							System.out.println("Enter the product you would like to change.");
							changeid = reader.readLine();
							App.getMeal(Integer.parseInt(changeid)).update();
							
						}
						
						if (message.equalsIgnoreCase("exit")) {
							System.out.println("Closing connection.");
								client.closeConnection();
						} else {
							//client.sendToServer(message);
							sendMessage(message);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
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
		});

		loopThread.start();
		this.isRunning = true;

	}

	public void displayMessage(Object message) {
		if (isRunning) {
			System.out.print("(Interrupted)\n");
		}
		System.out.println("Received message from server: " + message.toString());
		if (isRunning)
			System.out.print(SHELL_STRING);
	}

	public void closeConnection() {
		System.out.println("Connection closed.");
		System.exit(0);
	}
	
	public void sendMessage(String message)
	{
		String messageToSend = message;
		boolean isSendSubmitters = messageToSend.startsWith("#sendSubmitters");
		boolean isSend = messageToSend.startsWith("#send") && !isSendSubmitters;
		boolean isExit = messageToSend.startsWith("#exit");
		if(isSendSubmitters) {
			messageToSend = "Anis, Hadi"; 
		}
		if(isSend) {
			messageToSend = messageToSend.replaceFirst("#send ",""); 
		}
		if(isExit) {
			try {
				System.out.println("Closing connection.");
				client.closeConnection();
			} catch (IOException e1) {
			 	// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				client.sendToServer(messageToSend);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

package clientServer;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
import entities.Menu;
import entities.RestaurantMenu;


public class App
{
	
	private static Session session;
	
	/*private static SessionFactory getSessionFactory() throws HibernateException
	{
		Configuration configuration= new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package.
		/*configuration.addAnnotatedClass(Game.class);
		configuration.addAnnotatedClass(Costumer.class);
		configuration.addAnnotatedClass(CostumerGame.class);
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
	}*/
	
	public static void generateIngredient() throws Exception
	{
		/*SessionFactory sessionFactory= getSessionFactory();
		session = sessionFactory.openSession();
		*/
		session = Server.getSession();
		Ingredients salt = new Ingredients("salt");
		Ingredients pepper = new Ingredients("pepper");
		Ingredients meat = new Ingredients("meat");
		Ingredients oliveOil = new Ingredients("olive oil");
		Ingredients sugar = new Ingredients("sugar");
		Ingredients eggs = new Ingredients("eggs");
		Ingredients pouder = new Ingredients("pouder");
		Ingredients tomato = new Ingredients("tomato");
		Ingredients onions = new Ingredients("onions");
		Ingredients butter = new Ingredients("butter");
		Ingredients cheese = new Ingredients("cheese");
		Ingredients flour = new Ingredients("flour");
		Ingredients strawberry = new Ingredients("strawberry");
		Ingredients chocolate = new Ingredients("chocolate");
		session.save(salt); //0
		session.save(pepper); //1
		session.save(meat); //2
		session.save(oliveOil); //3
		session.save(sugar); //4
		session.save(eggs); //5
		session.save(pouder); //6
		session.save(tomato); //7
		session.save(onions); //8
		session.save(butter); //9
		session.save(cheese); //10
		session.save(flour); //11
		session.save(strawberry); //12
		session.save(chocolate); //13
		session.flush();
	}
	private static List<Ingredients> getAllIngredients() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Ingredients> query= builder.createQuery(Ingredients.class);
		query.from(Ingredients.class);
		List<Ingredients> data= session.createQuery(query).getResultList();
		return data;
	}
	public static Ingredients getIngredient(int ingID) throws Exception
	{
		Ingredients wanteding;
		List<Ingredients> alling = getAllIngredients();
		wanteding = alling.get(ingID);
		return wanteding;
	}
	public static void generateMeals() throws Exception
	{
		session = Server.getSession();
		List<Ingredients> ingr = getAllIngredients();
		List<Ingredients> burgerIng = new ArrayList<Ingredients>();
		burgerIng.add(ingr.get(0));
		burgerIng.add(ingr.get(1));
		burgerIng.add(ingr.get(2));
		List<Ingredients> burgerOptional = new ArrayList<Ingredients>();
		burgerOptional.add(ingr.get(5));
		burgerOptional.add(ingr.get(7));
		burgerOptional.add(ingr.get(8));
		burgerOptional.add(ingr.get(10));
		Meal burger = new Meal("Hamburger","Our special home made burger, just meat, spices and vegetables if wanted",
				60,burgerIng,burgerOptional);
		List<Ingredients> steakIng = new ArrayList<Ingredients>();
		steakIng.add(ingr.get(0));
		steakIng.add(ingr.get(1));
		steakIng.add(ingr.get(2));
		steakIng.add(ingr.get(3));
		steakIng.add(ingr.get(9));
		List<Ingredients> steakOptional = new ArrayList<Ingredients>();
		steakOptional.add(ingr.get(7));
		steakOptional.add(ingr.get(8));
		Meal steak = new Meal("Steak","Finest steaks, cooked by our talented chefs with love",
				130,steakIng,steakOptional);
		
		session.save(burger); //0
		session.save(steak); //1
		//session.flush();
	}
	
	private static List<Meal> getAllMeals() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Meal> query= builder.createQuery(Meal.class);
		query.from(Meal.class);
		List<Meal> data= session.createQuery(query).getResultList();
		return data;
	}
	
	public static void generateDrinks() throws Exception
	{
		session = Server.getSession();
		Drink cocacola = new Drink("CocaCola","330ml Sparkling Cola drink in a can",8.50,true,0);
		Drink appleJuice = new Drink("Apple Juice","Pregat apple juice 500ml in a bottle",8,false,0);
		Drink carlsberg = new Drink("Carlsberg beer","330ml glass carlsberg beer bottle",25,false,5);
		
		session.save(cocacola); //0
		session.save(appleJuice); //1
		session.save(carlsberg); //2
		//session.flush();
	}
	
	private static List<Drink> getAllDrinks() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Drink> query= builder.createQuery(Drink.class);
		query.from(Drink.class);
		List<Drink> data= session.createQuery(query).getResultList();
		return data;
	}
	
	public static void generateDesserts() throws Exception
	{
		session = Server.getSession();
		List<Ingredients> ingr = getAllIngredients();
		List<Ingredients> cheesecakeIng = new ArrayList<Ingredients>();
		cheesecakeIng.add(ingr.get(4));
		cheesecakeIng.add(ingr.get(5));
		cheesecakeIng.add(ingr.get(6));
		cheesecakeIng.add(ingr.get(9));
		cheesecakeIng.add(ingr.get(10));
		cheesecakeIng.add(ingr.get(11));
		List<Ingredients> cheesecakeOptional = new ArrayList<Ingredients>();;
		cheesecakeOptional.add(ingr.get(12));
		Dessert cheesecake = new Dessert("Cheese Cake","Our special sweet cheese cake made with love, can come with strawberries if asked",
				45,cheesecakeIng,cheesecakeOptional);
		
		
		List<Ingredients> chocolatecakeIng = new ArrayList<Ingredients>();;
		chocolatecakeIng.add(ingr.get(4));
		chocolatecakeIng.add(ingr.get(5));
		chocolatecakeIng.add(ingr.get(6));
		chocolatecakeIng.add(ingr.get(9));
		chocolatecakeIng.add(ingr.get(12));
		chocolatecakeIng.add(ingr.get(11));
		List<Ingredients> chocolateOptional = new ArrayList<Ingredients>();;
		chocolateOptional.add(ingr.get(13));
		Dessert chocolatecake = new Dessert("Chocolate Cake","Our special sweet chocolate cake made with love, can come with strawberries if asked",
				45,chocolatecakeIng,chocolateOptional);

		session.save(cheesecake); //0
		session.save(chocolatecake); //1
		//session.flush();
	}
	
	private static List<Dessert> getAllDesserts() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Dessert> query= builder.createQuery(Dessert.class);
		query.from(Dessert.class);
		List<Dessert> data= session.createQuery(query).getResultList();
		return data;
	}
	
	private static BaseMenu generateBaseMenu() throws Exception
	{
		List<Meal> meals = getAllMeals();
		List<Drink> drinks = getAllDrinks();
		List<Dessert> desserts = getAllDesserts();
		
		List<Meal> baseMeals = new ArrayList<Meal>();
		baseMeals.add(meals.get(0));
		
		List<Drink> baseDrinks = new ArrayList<Drink>();
		baseDrinks.add(drinks.get(0));
		baseDrinks.add(drinks.get(1));
		
		List<Dessert> baseDesserts = new ArrayList<Dessert>();
		baseDesserts.add(desserts.get(1));
		
		BaseMenu common = new BaseMenu(baseMeals,baseDrinks,baseDesserts);
		
		return common;
	}
	
	private static RestaurantMenu generateResturantMenu() throws Exception
	{
		List<Meal> meals = getAllMeals();
		List<Drink> drinks = getAllDrinks();
		List<Dessert> desserts = getAllDesserts();
		
		List<Meal> resMeals = new ArrayList<Meal>();
		resMeals.add(meals.get(1));
		
		List<Drink> resDrinks = new ArrayList<Drink>();
		resDrinks.add(drinks.get(2));
		
		List<Dessert> resDesserts = new ArrayList<Dessert>();
		resDesserts.add(desserts.get(0));
		
		Menu menu = new Menu(resMeals,resDrinks,resDesserts);
		BaseMenu common = generateBaseMenu();
		
		RestaurantMenu resMenu = new RestaurantMenu(common,menu);
		
		
		return resMenu;
	}
	private static List<Food> getAllFood() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Food> query= builder.createQuery(Food.class);
		query.from(Food.class);
		List<Food> data= session.createQuery(query).getResultList();
		return data;
	}
	public static Food getMeal(int foodID) throws Exception
	{
		Food wantedMeal;
		List<Food> allfood = getAllFood();
		wantedMeal = allfood.get(foodID);
		return wantedMeal;
	}
	public static void printmealbyiD(int foodID) throws Exception
	{
		getMeal(foodID).print();
	}
	public static void printIngredients() throws Exception
	{
		List<Ingredients> ingr = getAllIngredients();
		for(Ingredients ingredient : ingr)
		{
			ingredient.print();
		}
	}
	public static void viewMenu() throws Exception
	{
		RestaurantMenu resMenu = generateResturantMenu();
		BaseMenu base = resMenu.getcommon();
		Menu menu = resMenu.getindv();
		
		List<Meal> baseMeals = base.getmeals();
		List<Meal> resMeals = menu.getmeals();
		List<Drink> baseDrinks = base.getdrinks();
		List<Drink> resDrinks = menu.getdrinks();
		List<Dessert> baseDesserts = base.getdesserts();
		List<Dessert> resDesserts = menu.getdesserts();
		
		for(Meal meal : baseMeals)
		{
			meal.print();
		}
		for(Meal meal : resMeals)
		{
			meal.print();
			System.out.print("\n");
		}
		
		for(Drink drink : baseDrinks)
		{
			drink.print();
		}
		for(Drink drink : resDrinks)
		{
			drink.print();
		}
		
		for(Dessert dessert : baseDesserts)
		{
			dessert.print();
		}
		for(Dessert dessert : resDesserts)
		{
			dessert.print();
		}
		//session.flush();
	}
	
	/*public static void main( String[] args)
	{
		
		try{SessionFactory sessionFactory= getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		generateIngredient();
		generateMeals();
		generateDrinks();
		generateDesserts();
		//generateGames();
		//generateCostumers();
		//generateDeals();
		
		printAllGames();
		System.out.print("\n");
		printAvgRatings();
		System.out.print("\n");
		printAllCostumersSorted();
		System.out.print("\n");
		printMostSpent();
		//printAlldeals();
		viewMenu();
		System.out.print("\n");
		//session.getTransaction().commit();// Save everything.
		System.out.println("Done!");
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
		//System.out.print("It's working!");
	}*/
}

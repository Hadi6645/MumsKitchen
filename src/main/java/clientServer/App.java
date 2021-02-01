package clientServer;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import entities.Address;
import entities.BaseMenu;
import entities.Company;
import entities.Dessert;
import entities.DiningSpace;
import entities.Drink;
import entities.Employee;
import entities.Food;
import entities.Ingredients;
import entities.Meal;
import entities.Menu;
import entities.OpeningHours;
import entities.Restaurant;
import entities.RestaurantMenu;
import entities.table;
import enums.EmployeeRole;
import enums.Temperature;
import enums.Type;

public class App
{
	
	private static Session session;
	public static void generateIngredient() throws Exception
	{
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
		Ingredients honey = new Ingredients("honey");
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
		session.save(honey); //14
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
		Drink cocacola = new Drink("CocaCola","330ml Sparkling Cola drink in a can",8.50,true,0,Temperature.COLD);
		Drink appleJuice = new Drink("Apple Juice","Pregat apple juice 500ml in a bottle",8,false,0,Temperature.COLD);
		Drink carlsberg = new Drink("Carlsberg beer","330ml glass carlsberg beer bottle",25,false,5,Temperature.COLD);
		
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
	
	static void generateBaseMenu() throws Exception //change the constructor dont forget!
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
		
		BaseMenu common = new BaseMenu(getAllMeals(),getAllDrinks(),getAllDesserts()); 
		
		session.save(common);
	}
	
	static BaseMenu getBaseMenu()
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<BaseMenu> query= builder.createQuery(BaseMenu.class);
		query.from(BaseMenu.class);
		BaseMenu data= session.createQuery(query).uniqueResult();
		return data;
	}
	
	static void generateResturantMenu() throws Exception
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
		
		Menu menu = new Menu(getAllMeals(),getAllDrinks(),getAllDesserts());
		BaseMenu common = getBaseMenu();
		session.save(menu);
		RestaurantMenu resMenu = new RestaurantMenu(common,menu);
		session.save(resMenu);
	}
	private static RestaurantMenu getRestaurantMenu() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<RestaurantMenu> query= builder.createQuery(RestaurantMenu.class);
		query.from(RestaurantMenu.class);
		RestaurantMenu data= session.createQuery(query).uniqueResult();
		return data;
	}
	static List<Food> getAllFood()
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Food> query= builder.createQuery(Food.class);
		query.from(Food.class);
		List<Food> data= session.createQuery(query).getResultList();
		return data;
	}
	
	public static void generateTables()
	{
		session = Server.getSession();
		table table2 = new table(2);
		table table3 = new table(3);
		table table4 = new table(4);
		session.save(table2); //1
		session.save(table3); //2
		session.save(table4); //3
		session.flush();
	}
	
	static List<table> getAllTables()
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<table> query= builder.createQuery(table.class);
		query.from(table.class);
		List<table> data= session.createQuery(query).getResultList();
		return data;
	}

	public static void generateDiningspace()
	{
		session = Server.getSession();
		Type inside = Type.INSIDE;
		 Type outside = Type.OUTSIDE;
	     List<table> tables = getAllTables();
		 DiningSpace insideRes =  new DiningSpace(inside, false); 
		 DiningSpace outsideRes =  new DiningSpace(outside, true);
		 
		 insideRes.addTable(tables.get(1));
		 insideRes.addTable(tables.get(1));
		 insideRes.addTable(tables.get(1));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(3));
		 insideRes.addTable(tables.get(3));
		 insideRes.addTable(tables.get(3));
		 insideRes.addTable(tables.get(3));
		 insideRes.addTable(tables.get(3));
		 
		 outsideRes.addTable(tables.get(1));
		 outsideRes.addTable(tables.get(1));
		 outsideRes.addTable(tables.get(2)); 
		 outsideRes.addTable(tables.get(2));
		 outsideRes.addTable(tables.get(3));
		 outsideRes.addTable(tables.get(3));
		 outsideRes.addTable(tables.get(3));
		 session.save(insideRes);
		 session.save(outsideRes);
		 session.flush();
		
	}
	
	static List<DiningSpace> getAllDiningSpace()
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<DiningSpace> query= builder.createQuery(DiningSpace.class);
		query.from(DiningSpace.class);
		List<DiningSpace> data =  session.createQuery(query).getResultList();
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
	/*public static void viewMenu() throws Exception
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
	}*/
	public static void generateEmployees() throws Exception
	{
		session = Server.getSession();
		String CEO_id = "123456789";
    	String CEO_password = "smellycat";
    	String CEO_firstName = "Phoebe";
    	String CEO_lastName = "Buffay";
    	EmployeeRole CEO_role = EmployeeRole.CEO;
    	Employee CEOO = new Employee(CEO_id, CEO_password, CEO_firstName, CEO_lastName, CEO_role);
    	
    	String Dietitian_id = "987654321";
    	String Dietitian_password = "iknow";
    	String Dietitian_firstName = "Monica";
    	String Dietitian_lastName = "Geller";
    	EmployeeRole Dietitian_role = EmployeeRole.DIETITION;
    	Employee Dietitian = new Employee(Dietitian_id, Dietitian_password, Dietitian_firstName, Dietitian_lastName, Dietitian_role);
		
		
		Employee ceo = new Employee("12345678","1234","Lia","Komo",EmployeeRole.CEO);
		Employee dietition = new Employee("98765432","1234","Mat","Blob",EmployeeRole.DIETITION);
		Employee manager = new Employee("012345678","1234","Karmen","Jungle",EmployeeRole.MANAGER);
		//session.save(ceo); //0
		//session.save(dietition); //1
		
		session.save(CEOO); //0
		session.save(Dietitian); //1
		session.save(manager); //2
		//session.flush();
	}
	
	private static List<Employee> getEmployees() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Employee> query= builder.createQuery(Employee.class);
		query.from(Employee.class);
		List<Employee> data= session.createQuery(query).getResultList();
		return data;
	}
	
	public static Employee getEmployeeByID(String employeeID) throws Exception
	{
		Employee wantedEmployee = null;
		List<Employee> allEmployees = getEmployees();
		
		for(Employee employee : allEmployees)
		{
			if(employee.getId().equals(employeeID))
			{
				wantedEmployee = employee;
				break;
			}
		}
		return wantedEmployee;
	}
	
	private static Employee getCEO() throws Exception
	{
		Employee wantedEmployee = null;
		List<Employee> allEmployees = getEmployees();
		
		for(Employee employee : allEmployees)
		{
			if(employee.getRole() == EmployeeRole.CEO)
			{
				wantedEmployee = employee;
				break;
			}
		}
		return wantedEmployee;
	}
	
	private static Employee getDietition() throws Exception
	{
		Employee wantedEmployee = null;
		List<Employee> allEmployees = getEmployees();
		
		for(Employee employee : allEmployees)
		{
			if(employee.getRole() == EmployeeRole.DIETITION)
			{
				wantedEmployee = employee;
				break;
			}
		}
		return wantedEmployee;
	}
	
	public static void generateCompany() throws Exception
	{
		Employee ceo = getCEO();
		Employee dietition = getDietition();
		
		Company Company = new Company(ceo, dietition);
		
		session.save(Company);
	}
	
	private static Company getCompany() throws Exception
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Company> query= builder.createQuery(Company.class);
		query.from(Company.class);
		Company data= session.createQuery(query).uniqueResult();
		return data;
	}
	
	public static void generateRestaurants() throws Exception
	{
		String Name = "Haifa Branch";
    	Address Address = new Address("Haifa", "Yefe Nof", 42 );
    	String Telephone = "036427130";
    	List<Employee> Staff = new ArrayList<>(); 
    	OpeningHours Hours = new OpeningHours();
    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
    	//List<DiningSpace> Spaces = new ArrayList<>();
    	List<DiningSpace> Spaces = getAllDiningSpace();
    	
    	
    	BaseMenu resMenu = getBaseMenu();
    	session.save(Address);
    	session.save(Hours);
    	Restaurant res1 = new Restaurant(Name,Address,Telephone,Staff,Hours,Spaces,resMenu);
    	
    	
    	String Name2 = "Tel Aviv Branch";
    	Address Address2 = new Address("Tel Aviv", "Exhibition Gardens", 11 );
    	String Telephone2 = "036427080";
    	List<Employee> Staff2 = new ArrayList<>(); 
    	OpeningHours Hours2 = new OpeningHours();
    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
    	//List<DiningSpace> Spaces2 = new ArrayList<>();
    	List<DiningSpace> Spaces2 = getAllDiningSpace();
    	session.save(Address2);
    	session.save(Hours2);
    	//RestaurantMenu resMenu2 = generateResturantMenu();
    	
    	Restaurant res2 = new Restaurant(Name2,Address2,Telephone2,Staff2,Hours2,Spaces2,resMenu);
    	
    	session.save(res1);
    	session.save(res2);
    	
    	Company mainCompany = getCompany();
    	
    	mainCompany.AddRestaurant(res1);
    	mainCompany.AddRestaurant(res2);
    	
    	session.save(mainCompany);
	}
	
	public static List<Restaurant> getAllRestaurants()
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Restaurant> query= builder.createQuery(Restaurant.class);
		query.from(Restaurant.class);
		List<Restaurant> data= session.createQuery(query).getResultList();
		return data;
	}
	
	
}

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
import enums.DiningType;

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
		Ingredients salmon = new Ingredients("salmon");
		Ingredients lentil = new Ingredients("lentil");
		Ingredients garlic = new Ingredients("garlic");
		Ingredients lemon = new Ingredients("lemon");
		Ingredients rice = new Ingredients("rice");
		Ingredients soy = new Ingredients("soy");
		Ingredients avocado = new Ingredients("avocado");
		Ingredients chicken = new Ingredients("chicken");
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
		session.save(salmon); //15
		session.save(lentil); //16
		session.save(garlic); //17
		session.save(lemon); //18
		session.save(rice); //19
		session.save(soy); //20
		session.save(avocado); //21
		session.save(chicken); //22
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
		//burger
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
		session.save(burger); //0
		for(Ingredients ingred : burgerIng)
		{
			ingred.getBaseFoodList().add(burger);
			session.save(ingred);
		}
		for(Ingredients ingred : burgerOptional)
		{
			ingred.getOptionalFoodList().add(burger);
			session.save(ingred);
		}
		//steak
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
		session.save(steak); //1
		for(Ingredients ingred : steakIng)
		{
			ingred.getBaseFoodList().add(steak);
			session.save(ingred);
		}
		for(Ingredients ingred : steakOptional)
		{
			ingred.getOptionalFoodList().add(steak);
			session.save(ingred);
		}
		//salmon fish
		List<Ingredients> salmonbase = new ArrayList<Ingredients>();
		salmonbase.add(ingr.get(1));
		salmonbase.add(ingr.get(3));
		salmonbase.add(ingr.get(15));
		salmonbase.add(ingr.get(17));
		List<Ingredients> salmonOptional = new ArrayList<Ingredients>();
		salmonOptional.add(ingr.get(18));
		Meal salmonFish = new Meal("Salmon Fish","Finest Salmon piece cooked in the over",
				100,salmonbase,salmonOptional);	
		session.save(salmonFish); //1
		for(Ingredients ingred : salmonbase)
		{
			ingred.getBaseFoodList().add(salmonFish);
			session.save(ingred);
		}
		for(Ingredients ingred : salmonOptional)
		{
			ingred.getOptionalFoodList().add(salmonFish);
			session.save(ingred);
		}
		//lentils
		List<Ingredients> lentilbase = new ArrayList<Ingredients>();
		lentilbase.add(ingr.get(1));
		lentilbase.add(ingr.get(8));
		lentilbase.add(ingr.get(16));
		List<Ingredients> lentilOptional = new ArrayList<Ingredients>();
		lentilOptional.add(ingr.get(8));
		lentilOptional.add(ingr.get(19));
		Meal lentil = new Meal("Lentils","Purest lentil picked carefully and cooked with love",
				45,lentilbase,lentilOptional);	
		session.save(lentil); //1
		for(Ingredients ingred : lentilbase)
		{
			ingred.getBaseFoodList().add(lentil);
			session.save(ingred);
		}
		for(Ingredients ingred : lentilOptional)
		{
			ingred.getOptionalFoodList().add(lentil);
			session.save(ingred);
		}
		//sushi
		List<Ingredients> sushibase = new ArrayList<Ingredients>();
		sushibase.add(ingr.get(15));
		sushibase.add(ingr.get(19));
		sushibase.add(ingr.get(20));
		List<Ingredients> sushiOptional = new ArrayList<Ingredients>();
		sushiOptional.add(ingr.get(21));
		Meal sushi = new Meal("Sushi","Finest salmon piece rolled carefully for an amazing sushi rolls combo",
				70,sushibase,sushiOptional);	
		session.save(sushi); //1
		for(Ingredients ingred : sushibase)
		{
			ingred.getBaseFoodList().add(sushi);
			session.save(ingred);
		}
		for(Ingredients ingred : sushiOptional)
		{
			ingred.getOptionalFoodList().add(sushi);
			session.save(ingred);
		}
		//thai
		List<Ingredients> thaibase = new ArrayList<Ingredients>();
		thaibase.add(ingr.get(0));
		thaibase.add(ingr.get(1));
		thaibase.add(ingr.get(19));
		thaibase.add(ingr.get(22));
		List<Ingredients> thaiOptional = new ArrayList<Ingredients>();
		thaiOptional.add(ingr.get(20));
		Meal thai = new Meal("Thai","Thai Rice and Chicken straight out of the wok",
				50,thaibase,thaiOptional);	
		session.save(thai); //1
		for(Ingredients ingred : thaibase)
		{
			ingred.getBaseFoodList().add(thai);
			session.save(ingred);
		}
		for(Ingredients ingred : thaiOptional)
		{
			ingred.getOptionalFoodList().add(thai);
			session.save(ingred);
		}
		//chicken
		List<Ingredients> chickenbase = new ArrayList<Ingredients>();
		chickenbase.add(ingr.get(17));
		chickenbase.add(ingr.get(18));
		chickenbase.add(ingr.get(22));
		List<Ingredients> chickenOptional = new ArrayList<Ingredients>();
		chickenOptional.add(ingr.get(8));
		Meal chicken = new Meal("Chicken","Chicken Breast, low calories, very healthy!",
				50,chickenbase,chickenOptional);	
		session.save(chicken); //1
		for(Ingredients ingred : chickenbase)
		{
			ingred.getBaseFoodList().add(chicken);
			session.save(ingred);
		}
		for(Ingredients ingred : chickenOptional)
		{
			ingred.getOptionalFoodList().add(chicken);
			session.save(ingred);
		}
		//toast
		List<Ingredients> toastbase = new ArrayList<Ingredients>();
		toastbase.add(ingr.get(6));
		toastbase.add(ingr.get(10));
		toastbase.add(ingr.get(11));
		List<Ingredients> toastOptional = new ArrayList<Ingredients>();
		toastOptional.add(ingr.get(7));
		toastOptional.add(ingr.get(21));
		Meal toast = new Meal("Toast","Tasty cheese toast rich of ingredients",
				40,toastbase,toastOptional);	
		session.save(toast); //1
		for(Ingredients ingred : toastbase)
		{
			ingred.getBaseFoodList().add(toast);
			session.save(ingred);
		}
		for(Ingredients ingred : toastOptional)
		{
			ingred.getOptionalFoodList().add(toast);
			session.save(ingred);
		}
		session.flush();
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
		Drink espresso = new Drink("Espresso","Small Strong Espresso Coffee",10,false,0,Temperature.HOT);
		Drink coffee = new Drink("Coffee","Large cup of Coffee",14,false,0,Temperature.HOT);
		Drink water = new Drink("Water","Small bottle of neviot water",8,false,0,Temperature.COLD);
		
		session.save(cocacola); //0
		session.save(appleJuice); //1
		session.save(carlsberg); //2
		session.save(espresso); //3
		session.save(coffee); //4
		session.save(water); //5
		session.flush();
	}
	
//	private static List<Drink> getAllDrinks() throws Exception
//	{
//		List<Food> allfood = getAllFood();
//		List<Drink> alldrinks = new ArrayList<Drink>();
//		for(Food food:allfood)
//		{
//			if(food.getClass() == Drink.class)
//				alldrinks.add((Drink) food);
//		}
//		return alldrinks;
//	}
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
		List<Ingredients> cheesecakeOptional = new ArrayList<Ingredients>();
		cheesecakeOptional.add(ingr.get(12));
		Dessert cheesecake = new Dessert("Cheese Cake","Our special sweet cheese cake made with love, can come with strawberries if asked",
				45,cheesecakeIng,cheesecakeOptional);
		session.save(cheesecake); //0
		for(Ingredients ingred : cheesecakeIng)
		{
			ingred.getBaseFoodList().add(cheesecake);
			session.save(ingred);
		}
		for(Ingredients ingred : cheesecakeOptional)
		{
			ingred.getOptionalFoodList().add(cheesecake);
			session.save(ingred);
		}
		
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
		for(Ingredients ingred : chocolatecakeIng)
		{
			ingred.getBaseFoodList().add(chocolatecake);
			session.save(ingred);
		}
		for(Ingredients ingred : chocolateOptional)
		{
			ingred.getOptionalFoodList().add(chocolatecake);
			session.save(ingred);
		}
		
		session.save(chocolatecake); //1
		session.flush();
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
	
	static void generateBaseMenu() throws Exception
	{
		session = Server.getSession();
		List<Food> allfood =getAllFood();
		BaseMenu common = new BaseMenu();
		common.addfood(allfood.get(0));
		common.addfood(allfood.get(1));
		common.addfood(allfood.get(6));
		common.addfood(allfood.get(9));
		common.addfood(allfood.get(12));
		common.addfood(allfood.get(13));
		common.addfood(allfood.get(14));
		common.addfood(allfood.get(15));
		session.save(common);
		session.flush();
	}
	
	static Menu getBaseMenu()
	{
		List<Menu> allmenus = getAllMenus();
		Menu menureturn = null;
		for(Menu menu : allmenus)
		{
			if(menu.getClass() == BaseMenu.class)
			{
    			 menureturn = menu;
    			 break;
			}
		}
		return menureturn;
	}
//	static Menu getMenu()
//	{
//		List<Menu> allmenus = getAllMenus();
//		Menu menureturn = null;
//		for(Menu menu : allmenus)
//		{
//			if(menu.getClass() != BaseMenu.class)
//			{
//    			 menureturn = menu;
//    			 break;
//			}
//		}
//		return menureturn;
//	}
	static Menu generateHaifaMenu() throws Exception
	{
		session = Server.getSession();
		List<Food> allfood =getAllFood();
		List<Food> haifaFood = new ArrayList<Food>();
		haifaFood.addAll(getBaseMenu().getallfood());
		Menu haifaMenu = new Menu();
		haifaMenu.setallfood(haifaFood);
		session.save(haifaMenu);
		session.flush();
		
		return haifaMenu;
	}
	static Menu generateTelAvivMenu() throws Exception
	{
		session = Server.getSession();
		List<Food> allfood =getAllFood();
		List<Food> telAvivFood = new ArrayList<Food>();
		telAvivFood.addAll(getBaseMenu().getallfood());
		telAvivFood.add(allfood.get(2));
		telAvivFood.add(allfood.get(7));
		telAvivFood.add(allfood.get(10));
		Menu telAvivMenu = new Menu();
		telAvivMenu.setallfood(telAvivFood);
		session.save(telAvivMenu);
		session.flush();
		
		return telAvivMenu;
	}
	static Menu generateMajdalShamsMenu() throws Exception
	{
		session = Server.getSession();
		List<Food> allfood =getAllFood();
		List<Food> majdalShamsFood = new ArrayList<Food>();
		majdalShamsFood.addAll(getBaseMenu().getallfood());
		majdalShamsFood.add(allfood.get(3));
		majdalShamsFood.add(allfood.get(11));
		Menu majdalShamsMenu = new Menu();
		majdalShamsMenu.setallfood(majdalShamsFood);
		session.save(majdalShamsMenu);
		session.flush();
		
		return majdalShamsMenu;
	}
	static Menu generateAkkoMenu() throws Exception
	{
		session = Server.getSession();
		List<Food> allfood =getAllFood();
		List<Food> akkoFood = new ArrayList<Food>();
		akkoFood.addAll(getBaseMenu().getallfood());
		akkoFood.add(allfood.get(4));
		akkoFood.add(allfood.get(5));
		akkoFood.add(allfood.get(8));
		Menu akkoMenu = new Menu();
		akkoMenu.setallfood(akkoFood);
		session.save(akkoMenu);
		session.flush();
		
		return akkoMenu;
	}
	
	
	static List<Menu> getAllMenus()
	{
		session = Server.getSession();
		CriteriaBuilder builder= session.getCriteriaBuilder();
		CriteriaQuery<Menu> query= builder.createQuery(Menu.class);
		query.from(Menu.class);
		List<Menu> data= session.createQuery(query).getResultList();
		return data;
	}
	
	static List<Food> getRestaurantFood(int id)
	{
		session = Server.getSession();
		List<Restaurant> rests = getAllRestaurants(); 
		Restaurant rest = rests.get(id); 
		//List<Menu> allrestmenus = getAllMenus();
		//int temp = allrestmenus.indexOf(rest.getMenu());
		Menu restmenu = rest.getMenu();
		List<Food> result =  restmenu.getallfood();
		for(Food food : result)
		{
			//System.out.print(food.getName());
			System.out.print("");
		}
		return result;
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
		 DiningType inside =  DiningType.INSIDE;
		 DiningType outside =  DiningType.OUTSIDE;
	     List<table> tables = getAllTables();
		 DiningSpace insideRes =  new DiningSpace(inside, false); 
		 DiningSpace outsideRes =  new DiningSpace(outside, true);
		 
		 insideRes.addTable(tables.get(0));
		 insideRes.addTable(tables.get(0));
		 insideRes.addTable(tables.get(0));
		 insideRes.addTable(tables.get(1));
		 insideRes.addTable(tables.get(1));
		 insideRes.addTable(tables.get(1));
		 insideRes.addTable(tables.get(1));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(2));
		 insideRes.addTable(tables.get(2));
		 
		 outsideRes.addTable(tables.get(0));
		 outsideRes.addTable(tables.get(0));
		 outsideRes.addTable(tables.get(1)); 
		 outsideRes.addTable(tables.get(1));
		 outsideRes.addTable(tables.get(2));
		 outsideRes.addTable(tables.get(2));
		 outsideRes.addTable(tables.get(2));
		 for(table tab: tables)
		 {
			 session.save(tab);
		 }
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

	public static void printIngredients() throws Exception
	{
		List<Ingredients> ingr = getAllIngredients();
		for(Ingredients ingredient : ingr)
		{
			ingredient.print();
		}
	}

	public static void generateEmployees() throws Exception
	{
		session = Server.getSession();
		String CEO_id = "123456789";
    	String CEO_password = "smellycat";
    	String CEO_firstName = "Phoebe";
    	String CEO_lastName = "Buffay";
    	EmployeeRole CEO_role = EmployeeRole.CEO;
    	Employee ceo = new Employee(CEO_id, CEO_password, CEO_firstName, CEO_lastName, CEO_role);
    	
    	String Dietitian_id = "987654321";
    	String Dietitian_password = "iknow";
    	String Dietitian_firstName = "Monica";
    	String Dietitian_lastName = "Geller";
    	EmployeeRole Dietitian_role = EmployeeRole.DIETITION;
    	Employee dietitian = new Employee(Dietitian_id, Dietitian_password, Dietitian_firstName, Dietitian_lastName, Dietitian_role);
		
		
		Employee hind = new Employee("111111111","1234","Hind","Abusaleh",EmployeeRole.MANAGER);
		Employee lydia = new Employee("222222222","1234","lydia","Abusaleh",EmployeeRole.MANAGER);
		Employee anis = new Employee("333333333","1234","Anis","Srouji",EmployeeRole.MANAGER);
		Employee hadi = new Employee("444444444","1234","Hadi","Salami",EmployeeRole.MANAGER);
		
		Employee lia = new Employee("555","1234","Lia","Komo",EmployeeRole.WAITER);
		Employee mat = new Employee("666","1234","Mat","Blob",EmployeeRole.WAITER);
		Employee karmen = new Employee("777","1234","Karmen","Jungle",EmployeeRole.WAITER);
		Employee michael = new Employee("888","1234","Michael","Buble",EmployeeRole.WAITER);
		Employee adam = new Employee("999","1234","Adam","Cohen",EmployeeRole.WAITER);
		session.save(ceo); //0
		session.save(dietitian); //1
		session.save(hind); //2
		session.save(lydia); //3
		session.save(anis); //4
		session.save(hadi); //5
		session.save(lia); //6
		session.save(mat); //7
		session.save(karmen); //8
		session.save(michael); //9
		session.save(adam); //10
		session.flush();
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
		session = Server.getSession();
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
		session = Server.getSession();
		String Name = "Haifa Branch";
    	Address Address = new Address("Haifa", "Yefe Nof", 42 );
    	String Telephone = "036427130";
    	List<Employee> Staff = new ArrayList<>(); 
    	OpeningHours Hours = new OpeningHours();
    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
    	//List<DiningSpace> Spaces = new ArrayList<>();
    	List<DiningSpace> Spaces = getAllDiningSpace();
    	Menu haifaMenu = generateHaifaMenu();
    	session.save(haifaMenu);
    	session.save(Address);
    	session.save(Hours);
    	Restaurant res1 = new Restaurant(Name,Address,Telephone,Staff,Hours,Spaces,haifaMenu);
    	for(DiningSpace dinespace: Spaces)
    	{
    		dinespace.setRest(res1);
    		session.save(dinespace);
    	}
    	haifaMenu.setRestaurant(res1);
    	session.save(haifaMenu);
    	
    	String Name2 = "Tel Aviv Branch";
    	Address Address2 = new Address("Tel Aviv", "Exhibition Gardens", 11 );
    	String Telephone2 = "036427080";
    	List<Employee> Staff2 = new ArrayList<>(); 
    	OpeningHours Hours2 = new OpeningHours();
    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
    	//List<DiningSpace> Spaces2 = new ArrayList<>();
    	//List<DiningSpace> Spaces2 = getAllDiningSpace();
    	session.save(Address2);
    	session.save(Hours2);
    	Menu telAvivMenu = generateTelAvivMenu();
    	
    	Restaurant res2 = new Restaurant(Name2,Address2,Telephone2,Staff2,Hours2,Spaces,telAvivMenu);
    	/*for(DiningSpace dinespace: Spaces2)
    	{
    		dinespace.setRest(res2);
    		session.save(dinespace);
    	}*/
    	telAvivMenu.setRestaurant(res2);
    	session.save(telAvivMenu);
    	
    	String Name3 = "Majdal Shams Branch";
    	Address Address3 = new Address("Majdal Shams", "Hermon Road", 15 );
    	String Telephone3 = "049513451";
    	List<Employee> Staff3 = new ArrayList<>(); 
    	OpeningHours Hours3 = new OpeningHours();
    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
    	//List<DiningSpace> Spaces2 = new ArrayList<>();
    	//List<DiningSpace> Spaces2 = getAllDiningSpace();
    	session.save(Address3);
    	session.save(Hours3);
    	Menu majdalShamsMenu = generateMajdalShamsMenu();
    	
    	Restaurant res3 = new Restaurant(Name3,Address3,Telephone3,Staff3,Hours3,Spaces,majdalShamsMenu);
    	/*for(DiningSpace dinespace: Spaces2)
    	{
    		dinespace.setRest(res2);
    		session.save(dinespace);
    	}*/
    	majdalShamsMenu.setRestaurant(res3);
    	session.save(majdalShamsMenu);
    	
    	String Name4 = "Akko Branch";
    	Address Address4 = new Address("Akko", "Ha-Hagana", 30 );
    	String Telephone4 = "049819023";
    	List<Employee> Staff4 = new ArrayList<>(); 
    	OpeningHours Hours4 = new OpeningHours();
    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
    	//List<DiningSpace> Spaces2 = new ArrayList<>();
    	//List<DiningSpace> Spaces2 = getAllDiningSpace();
    	session.save(Address4);
    	session.save(Hours4);
    	Menu akkoMenu = generateAkkoMenu();
    	
    	Restaurant res4 = new Restaurant(Name4,Address4,Telephone4,Staff4,Hours4,Spaces,akkoMenu);
    	/*for(DiningSpace dinespace: Spaces2)
    	{
    		dinespace.setRest(res2);
    		session.save(dinespace);
    	}*/
    	akkoMenu.setRestaurant(res4);
    	session.save(akkoMenu);
    	
    	session.save(res1);
    	session.save(res2);
    	session.save(res3);
    	session.save(res4);
    	
    	Company mainCompany = getCompany();
    	
    	mainCompany.AddRestaurant(res1);
    	mainCompany.AddRestaurant(res2);
    	mainCompany.AddRestaurant(res3);
    	mainCompany.AddRestaurant(res4);
    	
    	session.save(mainCompany);
    	session.flush();
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

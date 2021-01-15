package userInterface;
////
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.BaseMenu;
import entities.Company;
import entities.Dessert;
import entities.DiningSpace;
import entities.Drink;
import entities.Employee;
import entities.Meal;
import entities.Menu;
import entities.OpeningHours;
import entities.Restaurant;
import entities.RestaurantMenu;
import enums.EmployeeRole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application{	
	
	 public static  Company start_company() {

		 // System.out.println("hiiiiiiiiiiiiiiiiiiiiii");
		 
	    	String CEO_id = "123456789";
	    	String CEO_password = "smelly_cat";
	    	String CEO_firstName = "Phoebe";
	    	String CEO_lastName = "Buffay";
	    	EmployeeRole CEO_role = EmployeeRole.CEO;
	    	Employee CEO = new Employee(CEO_id, CEO_password, CEO_firstName, CEO_lastName, CEO_role);
	    	
	    	String Dietitian_id = "987654321";
	    	String Dietitian_password = "i_know";
	    	String Dietitian_firstName = "Monica";
	    	String Dietitian_lastName = "Geller";
	    	EmployeeRole Dietitian_role = EmployeeRole.DIETITION;
	    	Employee Dietitian = new Employee(Dietitian_id, Dietitian_password, Dietitian_firstName, Dietitian_lastName, Dietitian_role);
	    	
	    	Company Company = new Company(CEO, Dietitian);
	    	
	    	String Name = "Haifa Branch";
	    	Address Address = new Address("Haifa", "Yefe Nof", 42 );
	    	String Telephone = "036427130";
	    	List<Employee> Staff = new ArrayList<>(); 
	    	OpeningHours Hours = new OpeningHours();
	    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
	    	List<DiningSpace> Spaces = new ArrayList<>();
	    	List<Meal> meals = new ArrayList<>();
	    	List<Drink> drinks = new ArrayList<>();
	    	List<Dessert> desserts = new ArrayList<>();
	    	BaseMenu common = new BaseMenu(meals,drinks,desserts) ;
	    	Menu ind =new Menu(meals, drinks, desserts);
	    	RestaurantMenu Menu = new RestaurantMenu(common, ind);
	    	
	    	Restaurant res1 = new Restaurant(Name,Address,Telephone,Staff,Hours,Spaces,Menu);
	    	
	    	String Name2 = "Tel Aviv Branch";
	    	Address Address2 = new Address("Tel Aviv", "Exhibition Gardens", 11 );
	    	String Telephone2 = "036427080";
	    	List<Employee> Staff2 = new ArrayList<>(); 
	    	OpeningHours Hours2 = new OpeningHours();
	    	//Hours.setOpeningHours(int day, LocalTime open, LocalTime close);
	    	List<DiningSpace> Spaces2 = new ArrayList<>();
	    	List<Meal> meals2 = new ArrayList<>();
	    	List<Drink> drinks2 = new ArrayList<>();
	    	List<Dessert> desserts2 = new ArrayList<>();
	    	Menu ind2 =new Menu(meals2, drinks2, desserts2);
	    	RestaurantMenu Menu2 = new RestaurantMenu(common, ind2);
	    	
	    	Restaurant res2 = new Restaurant(Name2,Address2,Telephone2,Staff2,Hours2,Spaces2,Menu2);
	    	
	    	Company.AddRestaurant(res1);
	    	Company.AddRestaurant(res2);
	    	
	    	return Company;
	    }
	 
	 private static Scene scene;    
	    @Override
	    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
	        scene = new Scene(loadFXML("Primary"), 500, 500);
	        stage.setScene(scene);
	        stage.setTitle("Mum's kitchen");
	        stage.show();
	    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
   
    

    public static void main(String[] args) {
        launch();
    }

}
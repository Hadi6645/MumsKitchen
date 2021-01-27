/**
 * Sample Skeleton for 'Show_Menu.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import control.Cache;
import entities.Dessert;
import entities.Drink;
import entities.Meal;
import entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class Show_Menu {

    @FXML // fx:id="meals_list"
    private ListView<String> meals_list; // Value injected by FXMLLoader
    private ObservableList<String> listViewData_meals = FXCollections.observableArrayList();
    
    @FXML // fx:id="drinks_list"
    private ListView<String> drinks_list; // Value injected by FXMLLoader
    private ObservableList<String> listViewData_drinks = FXCollections.observableArrayList();
    
    @FXML // fx:id="desserts_list"
    private ListView<String> desserts_list; // Value injected by FXMLLoader
    private ObservableList<String> listViewData_desserts = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
    // String val = null;
    	Cache cache= Cache.getCache();
    	Restaurant restaurant ;
    	//restaurant = PrimaryController.get_Restaurant();
    	restaurant = cache.getRestaurant();
    	 List<Meal> Meals = new ArrayList<Meal>();
    	// System.out.println("hiiiiiiiiiiiiiiiiiiiiii");
    	 Meals = restaurant.getMenu().get_meals();
    
    	 List<Drink> Drinks = new ArrayList<Drink>();
    	 Drinks = restaurant.getMenu().get_drinks();
    	 
    	 List<Dessert> Desserts = new ArrayList<Dessert>();
    	 Desserts = restaurant.getMenu().get_dessert();
    	 
    	 for(int i=0; i<Meals.size();i++) {
       	   listViewData_meals.add(Meals.get(i).getName());
          }
    	 
    	 for(int i=0; i<Drinks.size();i++) {
         	   listViewData_drinks.add(Drinks.get(i).getName());
            }
    	 
    	 for(int i=0; i<Desserts.size();i++) {
       	   listViewData_desserts.add(Desserts.get(i).getName());
          }
    	 
    	 // Init meals_list.
    	 meals_list.setItems(listViewData_meals);
    	 meals_list.setCellFactory((list) -> {
             return new ListCell<String>() {
                 @Override
                 protected void updateItem(String item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item);
                     }
                 }
             };
         });
    	 
    	 // Init drinks_list.
    	 drinks_list.setItems(listViewData_drinks);
    	 drinks_list.setCellFactory((list) -> {
             return new ListCell<String>() {
                 @Override
                 protected void updateItem(String item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item);
                     }
                 }
             };
         });
         
    	 // Init desserts_list.
    	 desserts_list.setItems(listViewData_desserts);
    	 desserts_list.setCellFactory((list) -> {
             return new ListCell<String>() {
                 @Override
                 protected void updateItem(String item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item);
                     }
                 }
             };
         });
    	 
    	// Handle meals_list selection changes.
    	 meals_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
            	show_Food(null);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
    	 
    	// Handle drinks_list selection changes.
    	 drinks_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
            	show_Food(null);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
    	 
    	// Handle ListView selection changes.
    	 desserts_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
            	show_Food(null);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
    	 
    	 
    }
    
    @FXML
    void show_Food(ActionEvent event) throws IOException {
          //App.setRoot("Food_page");
    }
    
}

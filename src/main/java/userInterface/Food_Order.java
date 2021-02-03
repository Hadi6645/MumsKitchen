/**
 * Sample Skeleton for 'Food_Order.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import control.Cache;
import entities.Dessert;
import entities.Drink;
import entities.Food;
import entities.Meal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class Food_Order {

    @FXML // fx:id="meals_list"
    private ListView<Meal> meals_list; // Value injected by FXMLLoader
    private ObservableList<Meal> listViewData_meals = FXCollections.observableArrayList();
    
    @FXML // fx:id="drinks_list"
    private ListView<Drink> drinks_list; // Value injected by FXMLLoader
    private ObservableList<Drink> listViewData_drinks = FXCollections.observableArrayList();

    @FXML // fx:id="desserts_list"
    private ListView<Dessert> desserts_list; // Value injected by FXMLLoader
    private ObservableList<Dessert> listViewData_desserts = FXCollections.observableArrayList();

    @FXML // fx:id="order_list"
    private ListView<Food> order_list; // Value injected by FXMLLoader
    private ObservableList<Food> listViewData_order = FXCollections.observableArrayList();
    
    Cache cache = Cache.getCache();
    protected static Session session;
    static  int i = 0;
    @FXML
    public void initialize() {
    	cache.requestMenu(cache.getRestId());
    	 List<Food> foods = cache.getCachedFood();
    	 
    	 for(Food food : foods)
 		 {
    		 if(food.getClass() == Meal.class)
    			 listViewData_meals.add((Meal) food);
    		 if(food.getClass() == Drink.class)
    			 listViewData_drinks.add((Drink) food);
    		 if(food.getClass() == Dessert.class)
    			 listViewData_desserts.add((Dessert) food);
 		 }
    	 // Init meals_list.
    	 meals_list.setItems(listViewData_meals);
    	 meals_list.setCellFactory((list) -> {
             return new ListCell<Meal>() {
                 @Override
                 protected void updateItem(Meal item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.getName());
                     }
                 }
             };
         });
    	 
    	 // Init drinks_list.
    	 drinks_list.setItems(listViewData_drinks);
    	 drinks_list.setCellFactory((list) -> {
             return new ListCell<Drink>() {
                 @Override
                 protected void updateItem(Drink item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.getName());
                     }
                 }
             };
         });
         
    	 // Init desserts_list.
    	 desserts_list.setItems(listViewData_desserts);
    	 desserts_list.setCellFactory((list) -> {
             return new ListCell<Dessert>() {
                 @Override
                 protected void updateItem(Dessert item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.getName());
                     }
                 }
             };
         });
    	 
    	// Handle meals_list selection changes.
    	 meals_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
            	cache.setCurrent_meal(newValue);
            	App.setRoot("MealInfo");
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
    	 
    	// Handle drinks_list selection changes.
    	 drinks_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
            	cache.setCurrent_drink(newValue);
            	App.setRoot("DrinkInfo");
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
    	 
    	// Handle desserts_list selection changes.
    	 desserts_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
            	cache.setCurrent_dessert(newValue);
            	App.setRoot("DessertInfo");
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
    	 
    	 
    }
    

    @FXML
    void Payment_completion(ActionEvent event) throws IOException {
         App.setRoot("Make_Order");///////////////////////////////////////////////////////////////
    }

    @FXML
    void add_dessert_to_order(ActionEvent event) {
    	 listViewData_order.add(i, cache.getCurrent_dessert());
         i++;
         order_list.setItems(listViewData_order);
         order_list.setCellFactory((list) -> {
             return new ListCell<Food>() {
                 @Override
                 protected void updateItem(Food item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.getName());
                     }
                 }
             };
         });
      // Handle order_list selection changes.
         order_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            cache.setCurrent_food(newValue);
         });
    }

    @FXML
    void add_drink_to_order(ActionEvent event) {
    	listViewData_order.add(i, cache.getCurrent_drink());
        i++;
        order_list.setItems(listViewData_order);
        order_list.setCellFactory((list) -> {
            return new ListCell<Food>() {
                @Override
                protected void updateItem(Food item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        });
     // Handle order_list selection changes.
        order_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("ListView Selection Changed (selected: " + newValue + ")");
           cache.setCurrent_food(newValue);
        });
    }

    @FXML
    void add_meal_to_order(ActionEvent event) {
           listViewData_order.add(i, cache.getCurrent_meal());
           i++;
           order_list.setItems(listViewData_order);
           order_list.setCellFactory((list) -> {
               return new ListCell<Food>() {
                   @Override
                   protected void updateItem(Food item, boolean empty) {
                       super.updateItem(item, empty);

                       if (item == null || empty) {
                           setText(null);
                       } else {
                           setText(item.getName());
                       }
                   }
               };
           });
           
        // Handle order_list selection changes.
           order_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
               System.out.println("ListView Selection Changed (selected: " + newValue + ")");
              cache.setCurrent_food(newValue);
           });
    }

    @FXML
    void backtoMain(ActionEvent event) throws IOException {
    	  App.setRoot("Main_Page");
    }
    
    @FXML
    void delete_food(ActionEvent event) {
    	for(int j =0; j< listViewData_order.size(); j++) {
    		if(listViewData_order.get(j) == cache.getCurrent_food()) {
    			listViewData_order.remove(j);
    		}
    	}
    	 order_list.setItems(listViewData_order);
         order_list.setCellFactory((list) -> {
             return new ListCell<Food>() {
                 @Override
                 protected void updateItem(Food item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.getName());
                     }
                 }
             };
         });
    }

}

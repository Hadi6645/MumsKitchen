/**
 * Sample Skeleton for 'Primary.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import control.Authenticator;
import control.Cache;
import entities.Company;
import entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;

public class PrimaryController {

    @FXML // fx:id="listView_2"
    private ListView<String> listView_2; // Value injected by FXMLLoader
    private ObservableList<String> listViewData1 = FXCollections.observableArrayList();
    
    @FXML // fx:id="login_B"
    private Button login_B; // Value injected by FXMLLoader
    
    private static Restaurant My_res;
    private static String val;
    @FXML
    public void initialize() {
    
    // String val = null;
    	Cache cache = Cache.getCache();
     	//Company company = new Company();
         //company = App.start_company();
        //listView_2 = (ListView<Restaurant>) company.getRestaurants();
    	cache.requestRestaurants();
         List<Restaurant> res = cache.getCachedRestaurants();
        //res = company.getRestaurants();
        for(int i=0; i<res.size();i++) {
     	   listViewData1.add(res.get(i).getName());
        }
        
        // Init ListView.
        listView_2.setItems(listViewData1);
        listView_2.setCellFactory((list) -> {
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
        
        
     // Handle ListView selection changes.
        listView_2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        	val = newValue;
            System.out.println("ListView Selection Changed (selected: " + newValue + ")");
           try {
         	  show_Restaurant(null);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
        });
        
        for(int i=0; i<res.size();i++) {
    		if(res.get(i).getName() == val) {
    			My_res = res.get(i);
    			cache.setRestId(My_res.getId());
    			cache.setRestaurant(My_res);
    		}
    	}
        
     }
    
    public static Restaurant get_Restaurant() {
    	return My_res;
    }

     @FXML
     void show_Restaurant(ActionEvent event) throws IOException {
           App.setRoot("Main_Page");
     }
     
     @FXML
     void goTologIn(ActionEvent event)  throws IOException{
    	 App.setRoot("logIn");
     }


}


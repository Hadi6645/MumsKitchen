/* Sample Skeleton for 'MealInfo.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;
import java.util.List;

import control.Cache;
import entities.Ingredients;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class DessertInfo {

    @FXML // fx:id="descriptiontext"
    private TextArea descriptiontext; // Value injected by FXMLLoader

    @FXML // fx:id="base_list"
    private ListView<Ingredients> base_list; // Value injected by FXMLLoader
    private ObservableList<Ingredients> listViewData_base = FXCollections.observableArrayList();
    
    @FXML // fx:id="optinal_list"
    private ListView<Ingredients> optinal_list; // Value injected by FXMLLoader
    private ObservableList<Ingredients> listViewData_optinal = FXCollections.observableArrayList();
    
    
    Cache cache = Cache.getCache();
    Ingredients ingre ;
    
    @FXML
    public void initialize() {
    	descriptiontext.setText("Product: "+(cache.getCurrent_dessert().getID()-1) + "\n" + cache.getCurrent_dessert().getName() + "\n" + "Description: " + 
    cache.getCurrent_dessert().getDesc() + "\n" + "Price: " + cache.getCurrent_dessert().getPrice());
    	
    	List<Ingredients> base = cache.getCurrent_dessert().getBaseIng();
    	 for(int i=0; i<base.size();i++) {
    		 listViewData_base.add(base.get(i));
          }
    	 
    	// Init ListView.
    	 base_list.setItems(listViewData_base);
    	 base_list.setCellFactory((list) -> {
             return new ListCell<Ingredients>() {
                 @Override
                 protected void updateItem(Ingredients item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.getName());
                     }
                 }
             };
         });
         
       /*  
      // Handle ListView selection changes.
    	 base_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
          	 
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
         */
    	 
    	 List<Ingredients> op = cache.getCurrent_meal().getOptIng();
    	 for(int i=0; i<op.size();i++) {
    		 listViewData_optinal.add(op.get(i));
          }
    	 
    	// Init ListView.
    	 optinal_list.setItems(listViewData_optinal);
    	 optinal_list.setCellFactory((list) -> {
             return new ListCell<Ingredients>() {
                 @Override
                 protected void updateItem(Ingredients item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.getName());
                     }
                 }
             };
         });
    	 
    	 // Handle ListView selection changes.
    	 base_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            ingre = newValue;
       	    
         });
    }
    
    
    @FXML
    void delete_op_fromeal(ActionEvent event) {
    	for(int j =0; j< listViewData_optinal.size(); j++) {
    		if(listViewData_optinal.get(j) == ingre) {
    			listViewData_optinal.remove(j);
    		}
    	}
    	// Init ListView.
   	 optinal_list.setItems(listViewData_optinal);
   	 optinal_list.setCellFactory((list) -> {
            return new ListCell<Ingredients>() {
                @Override
                protected void updateItem(Ingredients item, boolean empty) {
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

    @FXML
    void update_return(ActionEvent event) throws IOException {
    	
    	cache.getCurrent_dessert().setNewIng(listViewData_optinal);
    	App.setRoot("Food_Order");

    }

}
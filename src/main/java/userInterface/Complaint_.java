/* Sample Skeleton for 'Cancelation.fxml' Controller Class
 */

package userInterface;

import java.awt.List;
import java.io.IOException;

import control.Authenticator;
import control.Cache;
import entities.Complaint;
import entities.Restaurant;
import enums.ComplaintType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Complaint_ {

	  @FXML // fx:id="description_text"
	    private TextField description_text; // Value injected by FXMLLoa
	 
    @FXML // fx:id="applycomplaint"
    private Button applycomplaint; // Value injected by FXMLLoader
    
    @FXML // fx:id="IDcomplaint"
    private TextField IDcomplaint; // Value injected by FXMLLoader
    
    @FXML // fx:id="listView_2"
    private ListView<String> listView_2; // Value injected by FXMLLoader
    private ObservableList<String> listViewData1 = FXCollections.observableArrayList();
    
    @FXML // fx:id="complaintID"
    private Label complaintID; // Value injected by FXMLLoader
    
    
    @FXML // fx:id="continue_"
    private Button continue_; // Value injected by FXMLLoader


    private static String val;
    
    private static Complaint MyComplaint;
    private static ComplaintType complaintType;
    //private List<String> enumList =  MyComplaint.getAllComplaintType();
    
   
    
    public void initialize() {
    	Cache cache = Cache.getCache();
    	MyComplaint = cache.getComplaint();
    	
    	 MyComplaint.setDescription(description_text.getText());
    	
    	for(int i=0; i<4;i++) {
      	   listViewData1.add(MyComplaint.getAllComplaintType().get(i));
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
           complaintType = ComplaintType.valueOf(val);
		   MyComplaint.setType(complaintType);
        });
        
        for(int i=0; i<4;i++) {
    		if(MyComplaint.getComplaintType().toString() == val) {
    			complaintType = ComplaintType.valueOf(val);
    			MyComplaint.setType(complaintType);
    			cache.setComplaint(MyComplaint);
    		}
    	}
    	int foodOrderID = Integer.parseInt(IDcomplaint.getText());
         MyComplaint.addFoodOrderId(foodOrderID);
        
    }
    
    
    @FXML
    void apply_complaint(ActionEvent event) throws IOException {
    	
       App.setRoot("ComplaintDONE");
       
    }
    
    

}
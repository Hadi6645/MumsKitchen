/**
 * Sample Skeleton for 'Cancelation.fxml' Controller Class
 */

package userInterface;

import control.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class Cancelation {

    @FXML // fx:id="food_order"
    private CheckBox food_order; // Value injected by FXMLLoader

    @FXML // fx:id="reservation"
    private CheckBox reservation; // Value injected by FXMLLoader

    @FXML // fx:id="cancel_id"
    private TextField cancel_id; // Value injected by FXMLLoader

    @FXML // fx:id="CANCEL"
    private Button CANCEL; // Value injected by FXMLLoader
    

    private boolean isfoodordercancel = food_order.isSelected();
    
    @FXML
    void cancelation(ActionEvent evsent) {
    	DataService dat = DataService.getDataService();
    			;
    	if(isfoodordercancel ) {
    		dat.CancelFoodOrder(1);
    	}
    	else {
    		
    	}
    	

    }

}

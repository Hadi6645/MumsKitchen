/**
 * Sample Skeleton for 'Cancelation.fxml' Controller Class
 */

package userInterface;


import control.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
    
    @FXML // fx:id="resultLabel"
    private Label resultLabel; // Value injected by FXMLLoader

    private boolean isfoodordercancel = food_order.isSelected();
    int id;
    
    @FXML
    void cancelation(ActionEvent evsent) {
    	DataService dat = DataService.getDataService();
    	id = Integer.parseInt(cancel_id.getText());
    	String message = "";
    	if(isfoodordercancel ) {
    		float[] arr = dat.CancelFoodOrder(id);
    		message = "Your Order Was Canceled, You will recieve a payment of "+arr[0];
    	}
    	else {
    		int penalty = dat.CancelReservation(id);
    		message = "Your Order Was Canceled"+(penalty == 0 ? "" : "There is a penalty of "+penalty );
    	}
    	resultLabel.setText(message);

    }

}
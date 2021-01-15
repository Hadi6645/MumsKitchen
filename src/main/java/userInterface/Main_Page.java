/**
 * Sample Skeleton for 'Main_Page.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;

import entities.Company;
import entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Main_Page {

	
	
    @FXML // fx:id="show_menu_B"
    private Button show_menu_B; // Value injected by FXMLLoader

    @FXML // fx:id="cancelation_B"
    private Button cancelation_B; // Value injected by FXMLLoader

    @FXML // fx:id="complaint_B"
    private Button complaint_B; // Value injected by FXMLLoader

    @FXML // fx:id="food_order_B"
    private Button food_order_B; // Value injected by FXMLLoader

    @FXML // fx:id="reservation_B"
    private Button reservation_B; // Value injected by FXMLLoader

    @FXML // fx:id="aboutus_B"
    private Button aboutus_B; // Value injected by FXMLLoader
    
    
    @FXML
    void aboutus_act(ActionEvent event) throws IOException {
    	 App.setRoot("About_US");
    }

    @FXML
    void cancelation_act(ActionEvent event) throws IOException {
    	 App.setRoot("Cancelation");
    }

    @FXML
    void complaint_act(ActionEvent event) throws IOException {
    	 App.setRoot("Complaint");
    }

    @FXML
    void food_order_act(ActionEvent event) throws IOException {
    	 App.setRoot("Food_Order");
    }

    @FXML
    void reservation_act(ActionEvent event) throws IOException {
    	 App.setRoot("Reservation");
    }

    @FXML
    void show_menu_act(ActionEvent event) throws IOException {
    	 App.setRoot("Show_Menu");
    }

}

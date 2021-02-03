/**
 * Sample Skeleton for 'MakeReservation.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;

import control.Cache;
import control.DataService;
import entities.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MakeReservation {

    @FXML // fx:id="reser_text"
    private TextArea reser_text; // Value injected by FXMLLoader
    
    static int reservation_id;
    
    @FXML
    public void initialize() {
    	Cache cache = Cache.getCache();
    	Reservation reservation = cache.getReservation();
    	DataService data = DataService.getDataService();
        reservation_id = data.sendReservation(reservation);
        
        if(reservation_id >0) {
        	reser_text.setText("Your reservation has been absorbed.\n " + "Your Reservation ID is: " 
        + Integer.toString(reservation.getId()) + "Thank you for choosing our restaurant, we are waiting for you :)");
        	
        }
        else {
        	reser_text.setText("For some reason your reservation may not be absorbed\r\n"
        			+ "Please try again :(");
        }
    	
    }

    @FXML
    void backtoMain(ActionEvent event) throws IOException {
    	App.setRoot("Main_Page");
    }

}

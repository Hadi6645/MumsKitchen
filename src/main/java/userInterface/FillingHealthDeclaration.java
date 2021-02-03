/**
 * Sample Skeleton for 'FillingHealthDeclaration.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;

import control.Cache;
import entities.HealthReportSignature;
import entities.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FillingHealthDeclaration {

    @FXML // fx:id="guestName"
    private TextField guestName; // Value injected by FXMLLoader

    @FXML // fx:id="guestID"
    private TextField guestID; // Value injected by FXMLLoader

    @FXML
    void Agree_func(ActionEvent event) throws IOException {
    	Cache cache = Cache.getCache();
    	Reservation reservation = cache.getReservation();
    	HealthReportSignature h = new HealthReportSignature(guestName.getText(), guestID.getText());
    	reservation.addtoGuestsSignatures(h);
    	App.setRoot("InsertInformations");
    }

}

/**
 * Sample Skeleton for 'InsertInformations.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import control.Cache;
import entities.CreditCard;
import entities.Customer;
import entities.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class InsertInformations {

    @FXML // fx:id="expirationDate"
    private DatePicker expirationDate; // Value injected by FXMLLoader

    @FXML // fx:id="Helth_list"
    private ListView<String> Helth_list; // Value injected by FXMLLoader
    private ObservableList<String> listViewData1 = FXCollections.observableArrayList();
    
    @FXML // fx:id="first_name"
    private TextField first_name; // Value injected by FXMLLoader

    @FXML // fx:id="last_name"
    private TextField last_name; // Value injected by FXMLLoader

    @FXML // fx:id="phone"
    private TextField phone; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private TextField email; // Value injected by FXMLLoader

    @FXML // fx:id="cardNumber"
    private TextField cardNumber; // Value injected by FXMLLoader

    @FXML // fx:id="cvv"
    private TextField cvv; // Value injected by FXMLLoader

    @FXML // fx:id="no_confirmation"
    private Text no_confirmation; // Value injected by FXMLLoader

    
    static int flag = 0;
    
    
    @FXML
    void ConfirmReservation(ActionEvent event) throws IOException {
    	Cache cache = Cache.getCache();
    	Reservation reservation = cache.getReservation();
    	Customer customer = new Customer(first_name.getText(), last_name.getText(), phone.getText(), email.getText());
    	LocalDate expdate = expirationDate.getValue();
    	CreditCard CreditCard = new CreditCard(cardNumber.getText(), cvv.getText(), expdate);
    	cache.getReservation().setCustomer(customer);
    	cache.getReservation().setCreditCard(CreditCard);
    	
    	 for(int i=1; i<=reservation.getGuestsNumber(); i++) {
       	   listViewData1.add("Guest"+ Integer.toString(i));
          }
    	 
    	// Init ListView.
    	 Helth_list.setItems(listViewData1);
    	 Helth_list.setCellFactory((list) -> {
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
    	 Helth_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         	//val = newValue;
             System.out.println("ListView Selection Changed (selected: " + newValue + ")");
            try {
            	flag ++;
          	 App.setRoot("FillingHealthDeclaration");
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         });
    	 
 if(flag == reservation.getGuestsNumber()) {
    	 App.setRoot("MakeReservation");
 }
 else {
	 no_confirmation.setText("You need to fill the Health Declaration for all the guests.");
 }
    }

    @FXML
    void backtoMain(ActionEvent event) throws IOException {
    	 App.setRoot("Main_Page");
    }

}

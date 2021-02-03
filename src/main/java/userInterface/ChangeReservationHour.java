/**
 * Sample Skeleton for 'ChangeReservationHour.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import control.Cache;
import control.DataService;
import entities.Reservation;
import entities.Restaurant;
import entities.table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ChangeReservationHour {

    @FXML // fx:id="listView_hours"
    private ListView<LocalTime> listView_hours; // Value injected by FXMLLoader
    private ObservableList<LocalTime> listViewData1 = FXCollections.observableArrayList();
    
    static LocalTime new_choosentime;
    Cache cache = Cache.getCache();
    Reservation reservation = cache.getReservation();
    
    @FXML
    public void initialize() {
    	
    	Restaurant res = cache.getRestaurant();
    	LocalTime[][] opening = res.getOpeningHours().getOpeningHours();
    	int daynum = reservation.getTime().getDayOfWeek().getValue();
    	LocalTime openhour = opening[daynum][0];
    	DataService data = DataService.getDataService();
    	
    	List<List<table>> mylist = data.CanfirmReservation(reservation);
    	int i=0;
    	 List<table> listAtI = mylist.get(i);
    	 while(listAtI.get(0) == null) {
    		 i++;
    		 LocalTime newtime = openhour.plusMinutes(i*15);
    		 listViewData1.add(newtime);
    		 listAtI = mylist.get(i);
    	 }
    	 
    	 // Init ListView.
     	listView_hours.setItems(listViewData1);
     	listView_hours.setCellFactory((list) -> {
             return new ListCell<LocalTime>() {
                 @Override
                 protected void updateItem(LocalTime item, boolean empty) {
                     super.updateItem(item, empty);

                     if (item == null || empty) {
                         setText(null);
                     } else {
                         setText(item.toString());
                     }
                 }
             };
         });
         
         
      // Handle ListView selection changes.
     	listView_hours.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         	//val = newValue;
             System.out.println("ListView Selection Changed (selected: " + newValue.toString() + ")");
             new_choosentime = newValue;
         	
         });
    	
    }
    
    @FXML
    void BacktoMain(ActionEvent event) throws IOException {
            App.setRoot("Main_Page");
    }

    @FXML
    void confirmation_func(ActionEvent event) throws IOException {
    	LocalDateTime localdatetime = reservation.getTime();
        localdatetime = localdatetime.withHour(new_choosentime.getHour());
    	localdatetime = localdatetime.withMinute(new_choosentime.getMinute());
    	localdatetime = localdatetime.withSecond(new_choosentime.getSecond());
    	cache.getReservation().setTime(localdatetime);
    	App.setRoot("InsertInformations");
    	
    }

}

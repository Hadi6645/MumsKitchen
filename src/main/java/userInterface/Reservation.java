/**
 * Sample Skeleton for 'Reservation.fxml' Controller Class
 */

package userInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import control.Cache;
import entities.Restaurant;
import enums.DiningType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Reservation {

    @FXML // fx:id="choosen_date"
    private DatePicker choosen_date; // Value injected by FXMLLoader

    @FXML // fx:id="guest_number"
    private TextField guest_number; // Value injected by FXMLLoader

    @FXML // fx:id="Inside_box"
    private CheckBox Inside_box; // Value injected by FXMLLoader

    @FXML // fx:id="outside_box"
    private CheckBox outside_box; // Value injected by FXMLLoader

    @FXML // fx:id="listView_hours"
    private ListView<LocalTime> listView_hours; // Value injected by FXMLLoader
    private ObservableList<LocalTime> listViewData1 = FXCollections.observableArrayList();

    
    @FXML // fx:id="confirm_B"
    private Button confirm_B; // Value injected by FXMLLoader

    
    static DiningType space_type;
    //  private static LocalTime val;
      private LocalTime choosen_time;
      private LocalDateTime localdatetime;
      
    
    
    
    @FXML
    void confirm_act(ActionEvent event) {
    //	entities.Reservation reser = new entities.Reservation(Integer.parseInt(guest_number.getText()), space_type, localdatetime);
   	// System.out.println("hallllooooooossssss");
    }

    @FXML
    void inside_func(ActionEvent event) {
    	space_type = DiningType.INSIDE;
    }

    @FXML
    void outside_func(ActionEvent event) {
    	space_type = DiningType.OUTSIDE;
    }

    @FXML
    void show_hourlist(ActionEvent event) {

    	//String dayName;
    	LocalTime openhour;
    	LocalTime closehour;
    	LocalTime newhour;
    	int daynum=0 ;
    	Cache cache = Cache.getCache();
    	Restaurant res = cache.getRestaurant();
    	LocalTime[][] opening = res.getOpeningHours().getOpeningHours();
    	System.out.println("hallllooooooossssss");
    	LocalDate day = choosen_date.getValue();
    	daynum = day.getDayOfWeek().getValue();
    	/*DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault());
    	System.out.println(day.format(format));
    	dayName = day.format(format);
    	//System.out.println("hallllooooooossssss");
    	if(dayName == "Sunday") daynum=1;
    	else if(dayName == "Monday") daynum = 2;
    	else if(dayName == "Tuesday") daynum = 3;
    	else if(dayName == "Wednesday") daynum = 4;
    	else if(dayName == "Thursday") daynum = 5;
    	else if(dayName == "Friday") daynum = 6;
    	else if(dayName == "Saturday") daynum = 7;*/
    	
    	openhour = opening[daynum][1];
    	closehour = opening[daynum][2];
    	newhour = openhour.plusMinutes(15);
    	
    	while (newhour != closehour.minusHours(1).plusMinutes(15)) {
    		listViewData1.add(newhour);
    		newhour = newhour.plusMinutes(15);
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
           choosen_time = newValue;
        });
        
      /*  for(int i=0; i<res.size();i++) {
    		if(res.get(i).getName() == val) {
    			My_res = res.get(i);
    			cache.setRestId(My_res.getId());
    			cache.setRestaurant(My_res);
    		}
    	}*/
    	
    	localdatetime = localdatetime.withDayOfMonth(day.getDayOfMonth());
    	localdatetime = localdatetime.withMonth(day.getMonthValue());
    	localdatetime = localdatetime.withYear(day.getYear());
    	localdatetime = localdatetime.withHour(choosen_time.getHour());
    	localdatetime = localdatetime.withMinute(choosen_time.getMinute());
    	localdatetime = localdatetime.withSecond(choosen_time.getSecond());
    }

}

package userInterface;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class SecondaryController {

	 @FXML
	    private ListView<String> listView_1;
	    private ObservableList<String> listViewData = FXCollections.observableArrayList();
	    
	    @FXML
	    public void initialize() {
	        System.out.println("test contorller");
	        
	     // Add some sample data.
	        listViewData.add("1");
	        listViewData.add("2");
	        listViewData.add("3");
	        listViewData.add("4");
	        
	     // Init ListView.
	        listView_1.setItems(listViewData);
	        listView_1.setCellFactory((list) -> {
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
	        listView_1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            System.out.println("ListView Selection Changed (selected: " + newValue.toString() + ")");
	           try {
				show_menu(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        });
	    }
	    @FXML
	    void show_menu(ActionEvent event)  throws IOException{

	    
	    }


}
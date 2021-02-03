package userInterface;

/**
 * Sample Skeleton for 'Food_Order_DONE.fxml' Controller Class
 */




import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Food_Order_DONE {

    @FXML // fx:id="back_to_main"
    private Button back_to_main; // Value injected by FXMLLoader

    @FXML
    void back_to_main_page(ActionEvent event) throws IOException {
    	App.setRoot("Main_Page");

    }

}
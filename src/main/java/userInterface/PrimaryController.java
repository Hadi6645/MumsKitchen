/**
 * Sample Skeleton for 'Primary.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class PrimaryController {

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    @FXML // fx:id="Branch1"
    private Button Branch1; // Value injected by FXMLLoader

    @FXML // fx:id="Branch2"
    private Button Branch2; // Value injected by FXMLLoader

    @FXML // fx:id="Branch3"
    private Button Branch3; // Value injected by FXMLLoader

    @FXML // fx:id="Branch4"
    private Button Branch4; // Value injected by FXMLLoader

    @FXML // fx:id="logInBtn"
    private Button logInBtn; // Value injected by FXMLLoader
    
    @FXML
    void show_Branch1(ActionEvent event) throws IOException {
    	App.setRoot("Haifa_main");
    }

    @FXML
    void show_Branch2(ActionEvent event) throws IOException {
    	App.setRoot("Telaviv_main");
    }

    @FXML
    void show_Branch3(ActionEvent event) throws IOException {
    	App.setRoot("Majdal_main");
    }

    @FXML
    void show_Branch4(ActionEvent event) throws IOException {
    	App.setRoot("Acre_main");
    }
    
    @FXML
    void goto_logIn_panel(ActionEvent event)  throws IOException{
    	App.setRoot("LogInController");
    }

}

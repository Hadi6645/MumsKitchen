/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */
package userInterface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PrimaryController {

    @FXML // fx:id="menuBtn"
    private Button menuBtn; // Value injected by FXMLLoader

    @FXML // fx:id="menu_text"
    private TextArea menu_text; // Value injected by FXMLLoader

    @FXML // fx:id="logInBtn"
    private Button logInBtn; // Value injected by FXMLLoader
    
    @FXML
    void show_menu(ActionEvent event)  throws IOException{
    	menu_text.setText("hiiiiiiiii");
    	App.setRoot("Secondary");
    }
    
    @FXML
    void goto_logIn_panel(ActionEvent event)  throws IOException{
    	App.setRoot("LogIn");
    }

}


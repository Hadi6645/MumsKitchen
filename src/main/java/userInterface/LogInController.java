/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */
package userInterface;

import java.io.IOException;

import control.Authenticator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LogInController {

    @FXML // fx:id="error_lbl"
    private Label error_lbl; // Value injected by FXMLLoader

    @FXML // fx:id="id_textField"
    private TextField id_textField; // Value injected by FXMLLoader

    @FXML // fx:id="pass_textField"
    private TextField pass_textField;
    

    @FXML
    void attempt_logIn(ActionEvent event)  throws IOException{
    	error_lbl.setText("");
    	Authenticator auth = Authenticator.getAuthenticator();
    	boolean successfulLogIn = auth.logIn(id_textField.getText(), pass_textField.getText());
    	if (successfulLogIn) {
    		App.setRoot("UserPanel");
    	} else {
    		error_lbl.setText("Wrong id or password. Please try again.");
    	}
    }

}


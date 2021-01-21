package userInterface;

import java.io.IOException;

import control.Authenticator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UserPanelController {
	
	@FXML
    void logOut(ActionEvent event)  throws IOException{
    	Authenticator auth = Authenticator.getAuthenticator();
    	auth.logOut("1"); //change this to user id
    	
    	App.setRoot("Main_Page");
    }
	
}

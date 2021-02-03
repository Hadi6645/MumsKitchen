/**
 * Sample Skeleton for 'DrinkInfo.fxml' Controller Class
 */

package userInterface;

import java.io.IOException;

import control.Cache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class DrinkInfo {

    @FXML // fx:id="descriptiontext"
    private TextArea descriptiontext; // Value injected by FXMLLoader
    
    Cache cache = Cache.getCache();

    @FXML
    void return_func(ActionEvent event) throws IOException {

    	App.setRoot("Food_Order");
    	
    }
    
    @FXML
    public void initialize() {
    	
    	if(cache.getCurrent_drink().getSparke() == true) {
    	descriptiontext.setText("Product: "+(cache.getCurrent_drink().getID()-1) + "\n" + cache.getCurrent_drink().getName() + "\n" + "Description: " + 
    cache.getCurrent_drink().getDesc() + "\n" + "Price: " + cache.getCurrent_drink().getPrice()+"\n" + "Alcohol Percent: " 
    			+ String.valueOf(cache.getCurrent_drink().getalcPercent()) + "spark drink");
    	}
    	else {
    		descriptiontext.setText("Product: "+(cache.getCurrent_drink().getID()-1) + "\n" + cache.getCurrent_drink().getName() + "\n" + "Description: " + 
    			    cache.getCurrent_drink().getDesc() + "\n" + "Price: " + cache.getCurrent_drink().getPrice() +"\n"+ "Alcohol Percent: " 
    			    			+ String.valueOf(cache.getCurrent_drink().getalcPercent()) );
    	}
    }

}

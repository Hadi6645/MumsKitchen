/**
 * Sample Skeleton for 'DIETITION_page.fxml' Controller Class
 */

package userInterface;

import java.awt.event.MouseEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class DIETITION_page {

    @FXML // fx:id="add_meal_B"
    private Button add_meal_B; // Value injected by FXMLLoader

    @FXML // fx:id="add_drink_B"
    private Button add_drink_B; // Value injected by FXMLLoader

    @FXML // fx:id="add_dessert_B"
    private Button add_dessert_B; // Value injected by FXMLLoader

    @FXML // fx:id="commit_B"
    private Button commit_B; // Value injected by FXMLLoader

    @FXML // fx:id="choiceBox_meal"
    private ChoiceBox<?> choiceBox_meal; // Value injected by FXMLLoader

    @FXML // fx:id="choiceBox_drink"
    private ChoiceBox<?> choiceBox_drink; // Value injected by FXMLLoader

    @FXML // fx:id="choiceBox_dessert"
    private ChoiceBox<?> choiceBox_dessert; // Value injected by FXMLLoader

    @FXML
    void add_dessert_act(ActionEvent event) {

    }

    @FXML
    void add_drink_act(ActionEvent event) {

    }

    @FXML
    void add_meal_act(ActionEvent event) {

    }

    @FXML
    void commit_updates_act(ActionEvent event) {

    }

    @FXML
    void updateItem(MouseEvent event) {

    }

}


package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.Main;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    public Label fstNameLabel;
    @FXML
    public Label lstNameLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label regNumberLabel;

    public void initialize(URL location, ResourceBundle resources) {
        fstNameLabel.setText((String)Main.currentUser.get("FirstName"));
        lstNameLabel.setText((String)Main.currentUser.get("LastName"));
        emailLabel.setText((String)Main.currentUser.get("Email"));
        regNumberLabel.setText((String)Main.currentUser.get("RegistrationNumber"));
    }

    @FXML
    public void handleParkingSpotsButton() {
        Main main = new Main();
        try {
            main.changeMainStage("fxml/viewAvailableSpots.fxml", "Parking Application - Available Spots");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

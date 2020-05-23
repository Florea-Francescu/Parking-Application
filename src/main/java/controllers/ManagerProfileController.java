package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerProfileController implements Initializable {
    @FXML
    public Label fstNameLabel;
    @FXML
    public Label lstNameLabel;
    @FXML
    public Label emailLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fstNameLabel.setText((String) Main.currentUser.get("FirstName"));
        lstNameLabel.setText((String)Main.currentUser.get("LastName"));
        emailLabel.setText((String)Main.currentUser.get("Email"));
    }

    @FXML
    public void handleLogOutButton(){
        Main main = new Main();
        main.currentUser = null;

        try {
            main.changeMainStage("/fxml/login.fxml", "Parking Application - Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleParkingSpotsButton(){
        Main main = new Main();
        main.currentUser = null;

        try {
            main.changeMainStage("/fxml/viewAllSpots.fxml", "Parking Application - Parking Spots");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

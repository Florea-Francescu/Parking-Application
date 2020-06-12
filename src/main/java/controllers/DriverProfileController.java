package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverProfileController implements Initializable {
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
            main.changeMainStage("/fxml/viewAvailableSpots.fxml", "Parking Application - Available Spots");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void handleFinesButton() {
        Main main = new Main();
        try {
            main.changeMainStage("/fxml/viewFines.fxml", "Parking Application - Fines");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleWalletButton(){
        Main main = new Main();
        try {
            main.changeMainStage("/fxml/viewWallet.fxml", "Parking Application - MyWallet");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }


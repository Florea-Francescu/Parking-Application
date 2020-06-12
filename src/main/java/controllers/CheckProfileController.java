package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckProfileController implements Initializable {
    private static JSONObject user;

    @FXML
    public Label fstNameLabel;
    @FXML
    public Label lstNameLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label regNumberLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fstNameLabel.setText((String)user.get("FirstName"));
        lstNameLabel.setText((String)user.get("LastName"));
        emailLabel.setText((String)user.get("Email"));
        regNumberLabel.setText((String)user.get("RegistrationNumber"));
    }

    public static void setUser(JSONObject user){
        CheckProfileController.user = user;
    }

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) fstNameLabel.getScene().getWindow();
        stage.close();
    }
}

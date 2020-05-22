package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.java.Main;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;


public class LoginController {
    @FXML
    public TextField emailInput;
    @FXML
    public TextField passInput;
    @FXML
    public Label errorLabel;
    @FXML
    public RadioButton driverButton;
    @FXML
    public RadioButton managerButton;

    private boolean sameAccount(JSONObject o1, JSONObject o2){
        return o1.get("Email").equals(o2.get("Email")) &&
                o1.get("Password").equals(o2.get("Password"));
    }

    @FXML
    public void loginActionHandle(){
        String loginFile = "/users.json";
        Base64.Encoder enc = Base64.getEncoder();

        if(managerButton.isSelected()){
            loginFile = "/managers.json";
        }

        try {
            Object obj = new JSONParser().parse(new FileReader(getClass().getResource(loginFile).getPath()));
            JSONArray userData = (JSONArray)obj;

            String username = emailInput.getText();
            String password = passInput.getText();
            JSONObject newUser = new JSONObject();
            newUser.put("Email", username);
            newUser.put("Password", enc.encodeToString(((String)password).getBytes()));

            boolean exists = false;
            Iterator<JSONObject> it = userData.iterator();
            JSONObject currentUser = null;
            while (it.hasNext()) {
                currentUser = it.next();
                if(sameAccount(currentUser, newUser)){
                    exists = true;
                    break;
                }
            }

            if(exists && driverButton.isSelected())
                userLoginSuccessful(currentUser);
            else if(exists && managerButton.isSelected())
                managerLoginSuccessful(currentUser);
            else
                failedLogIn();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void managerLoginSuccessful(JSONObject user) {
        System.out.println("Manager logged in!");
    }

    private void userLoginSuccessful(JSONObject user) {
        Main main = new Main();
        Main.currentUser = user;

        try {
            main.changeMainStage("/fxml/driverProfile.fxml", "Parking Application - Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCreateAccountButton()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create New Account");
        try {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createAccount.fxml"));
           Parent root = (Parent)fxmlLoader.load();
           window.setScene(new Scene(root,400,400));
           window.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void failedLogIn(){
        errorLabel.setText("Username or password are incorrect!");
    }
}
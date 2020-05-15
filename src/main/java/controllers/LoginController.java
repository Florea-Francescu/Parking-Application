package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class LoginController {
    @FXML
    public TextField emailInput;
    @FXML
    public TextField passInput;
    @FXML
    public Label errorLabel;

    private boolean sameAccount(JSONObject o1, JSONObject o2){
        return o1.get("Email").equals(o2.get("Email")) && o1.get("Password").equals(o2.get("Password"));
    }

    @FXML
    public void loginActionHandle(){
        try {
            Object obj = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("main/resources/users.json").getPath()));
            JSONArray userData = (JSONArray)obj;

            String username = emailInput.getText();
            String password = passInput.getText();
            JSONObject newUser = new JSONObject();
            newUser.put("Email", username);
            newUser.put("Password", password);

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

            if(exists)
                loginSuccessful(currentUser);
            else
                failedLogIn();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void loginSuccessful(JSONObject user) {
        Main main = new Main();
        Main.currentUser = user;

        try {
            main.changeMainStage("fxml/profile.fxml", "Parking Application - Profile");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void failedLogIn(){
        errorLabel.setText("Username or password are incorrect!");
    }
}
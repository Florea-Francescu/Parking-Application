package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;


public class LoginController {
    @FXML
    public TextField userInput;
    @FXML
    public TextField passInput;
    @FXML
    public Label errorLabel;

    @FXML
    public void loginActionHandle(){
        try {
            Object obj = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("main/resources/users.json").getPath()));
            JSONArray userData = (JSONArray)obj;

            String username = userInput.getText();
            String password = passInput.getText();
            JSONObject newUser = new JSONObject();
            newUser.put("username", username);
            newUser.put("password", password);

            boolean exists = false;
            Iterator<JSONObject> it = userData.iterator();
            while (it.hasNext()) {
                if(it.next().toJSONString().equals(newUser.toJSONString())){
                    exists = true;
                    break;
                }
            }

            if(exists)
                logIn();
            else
                failedLogIn();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/createAccount.fxml"));
           Parent root = (Parent)fxmlLoader.load();
           window.setScene(new Scene(root,400,400));
           window.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void logIn(){
        errorLabel.setText("Logged In Successfully!");
    }

    private void failedLogIn(){
        errorLabel.setText("Username or password are incorrect!");
    }
}

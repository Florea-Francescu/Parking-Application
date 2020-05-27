package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.Base64;
import java.util.Iterator;


public class CreateAccountController {

    @FXML
    public TextField emailInput;

    @FXML
    public TextField fstNameInput;

    @FXML
    public TextField lstNameInput;

    @FXML
    public TextField regNumberInput;

    @FXML
    public PasswordField passInput;

    @FXML
    public PasswordField confPassInput;

    @FXML
    public Label errorLabel;
    
    @FXML
    public void handleCreateAccountButton(){
        String email = emailInput.getText();
        String fstName = fstNameInput.getText();
        String lstName = lstNameInput.getText();
        String regNumber = regNumberInput.getText();
        String password = passInput.getText();
        String confPassword = confPassInput.getText();


        if(email == null || email.isEmpty())
        {
            errorLabel.setText("Please type your email");
            return;
        }
        if(!(email.contains("@gmail.com")  || email.contains("@yahoo.com")))
        {
            errorLabel.setText("Please type a valid email");
            return;
        }

        if(fstName == null || fstName.isEmpty())
        {
            errorLabel.setText("Complete your name");
            return;
        }

        if(lstName == null || lstName.isEmpty())
        {
            errorLabel.setText("Complete your name");
            return;
        }

        if(regNumber == null || regNumber.isEmpty())
        {
            errorLabel.setText("Please type car registration number");
            return;
        }

        if(password == null || password.isEmpty())
        {
            errorLabel.setText("Please type your password");
            return;
        }
        if(confPassword == null || confPassword.isEmpty() || !password.equals(confPassword))
        {
            errorLabel.setText("You must confirm your password");
            return;
        }

        Base64.Encoder enc = Base64.getEncoder();

        JSONObject newUser = new JSONObject();
        newUser.put("Email", email);
        newUser.put("FirstName", fstName);
        newUser.put("LastName", lstName);
        newUser.put("RegistrationNumber", regNumber);
        newUser.put("Password", enc.encodeToString(password.getBytes()));
        newUser.put("ConfirmPassword", enc.encodeToString(confPassword.getBytes()));
        
            try {
                FileReader reader = new FileReader("src/main/resources/users.json");
                Object obj = new JSONParser().parse(reader);
                JSONArray userData = (JSONArray) obj;
                reader.close();

                boolean exists = false;
                Iterator<JSONObject> it = userData.iterator();
                while (it.hasNext()) {
                    if (it.next().get("RegistrationNumber").equals(newUser.get("RegistrationNumber"))) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    accountAlreadyExists();
                } else {
                    userData.add(newUser);
                    FileWriter file = new FileWriter("src/main/resources/users.json");
                    file.write(userData.toJSONString());
                    file.close();
                    createAccount();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    private void accountAlreadyExists()
    {
        errorLabel.setText("Account already exists!");
    }

    private void createAccount()
    {

        errorLabel.setText("Successfully created account!");

    }
}

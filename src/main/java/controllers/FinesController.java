package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import main.java.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class FinesController implements Initializable {

    @FXML
    public Label errorLabel;

    @FXML
    public Label amountLabel;

    @FXML
    public RadioButton halfButton;

    @FXML
    public RadioButton allButton;

    private double percent;
    @FXML
    public void radioButtonChanged() {
        if (halfButton.isSelected()) {
            percent = 0.5;
        }
        if (allButton.isSelected()) {
            percent = 1.0;
        }
    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            FileReader readerFines = new FileReader("src/main/resources/fined_cars.json");
            Object objFines = new JSONParser().parse(readerFines);
            JSONArray userFines = (JSONArray) objFines;
            readerFines.close();
            Iterator<JSONObject> itFines = userFines.iterator();
            while (itFines.hasNext())
            {
                JSONObject fines = itFines.next();
                if(fines.get("RegistrationNumber").equals((String) Main.currentUser.get("RegistrationNumber")))
                {
                    amountLabel.setText((String)fines.get("Value"));
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void handlePayButton(){
    try{
        FileReader readerUser = new FileReader("src/main/resources/users.json");
        Object objUser = new JSONParser().parse(readerUser);
        JSONArray userData = (JSONArray) objUser;
        readerUser.close();

        FileReader readerFines = new FileReader("src/main/resources/fined_cars.json");
        Object objFines = new JSONParser().parse(readerFines);
        JSONArray userFines = (JSONArray) objFines;
        readerFines.close();

        boolean exists = false;
        Iterator<JSONObject> itReader = userData.iterator();
        Iterator<JSONObject> itFines = userFines.iterator();
        while(itReader.hasNext()) {
            JSONObject sameUser = itReader.next();
            int dif;
            if (sameUser.get("RegistrationNumber").equals((String) Main.currentUser.get("RegistrationNumber"))) {
                {
                    while(itFines.hasNext())
                    {
                        JSONObject fine = itFines.next();
                        if(sameUser.get("RegistrationNumber").equals(fine.get("RegistrationNumber")))
                        {
                            if(parseInt((String)sameUser.get("Currency")) >= percent * parseInt((String)fine.get("Value")))
                            {
                                exists = true;
                                dif = (int)(parseInt((String) sameUser.get("Currency")) - percent * parseInt((String)fine.get("Value")));
                                sameUser.replace("Currency", dif + "");
                                fine.replace("Value", ((int)(parseInt((String)fine.get("Value")) - percent * parseInt((String)fine.get("Value")))) + "");
                                amountLabel.setText((String)fine.get("Value"));
                            }
                        }
                    }
                }
            }
        }
        if(exists)
        {
            FileWriter file = new FileWriter("src/main/resources/users.json");
            file.write(userData.toJSONString());
            file.close();

            FileWriter file2 = new FileWriter("src/main/resources/fined_cars.json");
            file2.write(userFines.toJSONString());
            file2.close();
            SuccesTransaction();
        }
        else{
            ErrorTransaction();
        }

    }catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }

    }

    @FXML
    public void handleProfileButton(){
        Main main = new Main();
        try {
            main.changeMainStage("/fxml/driverProfile.fxml", "Parking Application - Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void SuccesTransaction()
    {
        if(percent == 1.0)
            errorLabel.setText("All your debts were paid");
        else
            errorLabel.setText("Half of your debts were paid");

    }
    private void ErrorTransaction()
    {
        errorLabel.setText("No enough currency in your wallet");
    }
}

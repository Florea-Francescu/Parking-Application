package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class DriverWalletController implements Initializable {
    @FXML
    public Label currencyLabel;

    @FXML
    public Label errorLabel;

    @FXML
    public TextField codeField;

    public void initialize(URL location, ResourceBundle resources) {
        currencyLabel.setText((String) Main.currentUser.get("Currency"));
    }

    @FXML
    public void handleOKbutton(){
        String code = codeField.getText();

        try {
            FileReader reader = new FileReader("src/main/resources/currency.json");
            Object obj = new JSONParser().parse(reader);
            JSONArray userCurrency = (JSONArray) obj;
            reader.close();

            FileReader readerUser = new FileReader("src/main/resources/users.json");
            Object objUser = new JSONParser().parse(readerUser);
            JSONArray userData = (JSONArray) objUser;
            readerUser.close();

            boolean exists = false;
            Iterator<JSONObject> it = userData.iterator();
            Iterator<JSONObject> itCurrency = userCurrency.iterator();
            while (it.hasNext()) {
                JSONObject sameUser = it.next();
                int sum;
                if (sameUser.get("Email").equals((String)Main.currentUser.get("Email"))){
                    while(itCurrency.hasNext())
                    {
                        JSONObject newCurrency = itCurrency.next();
                        if(newCurrency.get("Cod").equals(code))
                        {
                            exists = true;
                            sum = parseInt((String)sameUser.get("Currency")) + parseInt((String)newCurrency.get("Value"));
                            sameUser.replace("Currency", sum  + "");
                            currencyLabel.setText((String) sameUser.get("Currency"));
                            break;
                        }
                    }
                }
            }
            if (exists) {
                FileWriter file = new FileWriter("src/main/resources/users.json");
                file.write(userData.toJSONString());
                file.close();
                SuccesTransaction();
            } else {
                ErrorTransaction();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void SuccesTransaction()
    {
        errorLabel.setText("Currency added in your wallet");

    }
    private void ErrorTransaction()
    {
        errorLabel.setText("This code is not correct!");
    }

    @FXML
    public void handleProfileButton() {
        Main main = new Main();
        try {
            main.changeMainStage("/fxml/driverProfile.fxml", "Parking Application - Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

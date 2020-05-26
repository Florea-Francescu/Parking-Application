package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import main.java.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class ReserveSpotController {
    @FXML
    public RadioButton oneHourButton;

    @FXML
    public RadioButton twoHoursButton;

    @FXML
    public RadioButton sixHoursButton;

    @FXML
    public Button acceptButton;

    @FXML
    public Button cancelButton;

    @FXML
    public Label priceLabel;

    @FXML
    public void handleReserveButton(){
        try {
            Object obj = new JSONParser().parse(new FileReader(getClass().getResource("/reserved_spots.json").getPath()));
            JSONArray spotsData = (JSONArray)obj;

            String regNumber = (String)Main.currentUser.get("RegistrationNumber");
            String username = (String)Main.currentUser.get("Email");
            JSONObject newSpot = new JSONObject();
            newSpot.put("Email", username);
            newSpot.put("RegistrationNumber", regNumber);
            
            boolean exists = false;
            Iterator<JSONObject> it = spotsData.iterator();
            JSONObject currentSpot;
            while (it.hasNext()) {
                currentSpot = it.next();
                if(sameRegNumber(currentSpot, newSpot)){
                    exists = true;
                    break;
                }
            }

            if(oneHourButton.isSelected()) {
                newSpot.put("Hours", "1");
            }
            if(twoHoursButton.isSelected()) {
                newSpot.put("Hours", "2");
            }
            if(sixHoursButton.isSelected())
            {
                newSpot.put("Hours", "6");
            }
            spotsData.add(newSpot);
            FileWriter file = new FileWriter("src/main/resources/reserved_spots.json");
            file.write(spotsData.toJSONString());
            file.close();
            Stage stage = (Stage) acceptButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancelButton(){
        Stage stage = (Stage) acceptButton.getScene().getWindow();
        stage.close();
    }

    private boolean sameRegNumber(JSONObject o1, JSONObject o2){
        return o1.get("RegistrationNumber").equals(o2.get("RegistrationNumber"));
    }

}

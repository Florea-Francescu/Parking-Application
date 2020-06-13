package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import main.java.Main;
import main.java.entities.ParkingSpot;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.java.Main;
import main.java.entities.ParkingSpot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import static java.lang.Integer.parseInt;

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
    public Label errorLabel;

    private int priceHour;

    @FXML
    public void radioButtonChanged() {
        priceHour = 0;
        if (oneHourButton.isSelected()) {
            priceHour = ParkingSpot.price * 1;
        }
        if (twoHoursButton.isSelected()) {

            priceHour = ParkingSpot.price * 2;
        }
        if (sixHoursButton.isSelected()) {
            priceHour = ParkingSpot.price * 6;
        }
        priceLabel.setText("" + priceHour);
    }

    @FXML
    public void handleReserveButton() {

        if (parseInt((String) Main.currentUser.get("Currency")) >= priceHour) {
            try {
                FileReader readerUser = new FileReader("src/main/resources/users.json");
                Object objUser = new JSONParser().parse(readerUser);
                JSONArray userData = (JSONArray) objUser;
                readerUser.close();

                Iterator<JSONObject> itReader = userData.iterator();
                while(itReader.hasNext()) {
                    JSONObject sameUser = itReader.next();
                    int dif;
                    if (sameUser.get("Email").equals((String) Main.currentUser.get("Email"))) {
                        dif = parseInt((String) sameUser.get("Currency")) - priceHour;
                        sameUser.replace("Currency", dif + "");
                    }
                }
                FileWriter fileW = new FileWriter("src/main/resources/users.json");
                fileW.write(userData.toJSONString());
                fileW.close();

                Object obj = new JSONParser().parse(new FileReader(getClass().getResource("/reserved_spots.json").getPath()));
                JSONArray spotsData = (JSONArray) obj;

                String regNumber = (String) Main.currentUser.get("RegistrationNumber");
                String username = (String) Main.currentUser.get("Email");
                JSONObject newSpot = new JSONObject();
                newSpot.put("Email", username);
                newSpot.put("RegistrationNumber", regNumber);
                newSpot.put("ID", ParkingSpot.idSpot);

                if (oneHourButton.isSelected()) {
                    newSpot.put("Hours", "1");
                }
                if (twoHoursButton.isSelected()) {
                    newSpot.put("Hours", "2");
                }
                if (sixHoursButton.isSelected()) {
                    newSpot.put("Hours", "6");
                }
                spotsData.add(newSpot);
                FileWriter file = new FileWriter("src/main/resources/reserved_spots.json");
                file.write(spotsData.toJSONString());
                file.close();

                FileReader reader = new FileReader(getClass().getResource("/parking_spots.json").getPath());
                Object obj1 = new JSONParser().parse(reader);
                reader.close();
                JSONArray ps = (JSONArray) obj1;
                Iterator<JSONObject> it = ps.iterator();

                while (it.hasNext()) {
                    JSONObject currentSpot = it.next();
                    if (currentSpot.get("ID").equals(ParkingSpot.idSpot)) {
                        currentSpot.replace("Status", "Unavailable");
                    }
                }

                FileWriter fw = new FileWriter("src/main/resources/parking_spots.json");
                fw.write(ps.toJSONString());
                fw.close();

                Stage stage = (Stage) acceptButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else
        {
            showError();
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
    private void showError()
    {
        errorLabel.setText("Not enogh currency");
    }

}

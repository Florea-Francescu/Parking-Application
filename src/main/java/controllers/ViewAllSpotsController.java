package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.Main;
import main.java.entities.ManagerParkingSpot;
import main.java.utils.OtherUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ViewAllSpotsController implements Initializable {

    @FXML
    public TableView table;
    @FXML
    public TableColumn<ManagerParkingSpot, String> idColumn;
    @FXML
    public TableColumn<ManagerParkingSpot, String> floorColumn;
    @FXML
    public TableColumn<ManagerParkingSpot, String> priceColumn;
    @FXML
    public TableColumn<ManagerParkingSpot, String> statusColumn;
    @FXML
    public TableColumn<ManagerParkingSpot, String> claimedColumn;
    @FXML
    public TableColumn<ManagerParkingSpot, Button> actionColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ManagerParkingSpot> avSpots = FXCollections.observableArrayList();

        try {
            OtherUtils ju = new OtherUtils();
            FileReader reader = new FileReader("src/main/resources/parking_spots.json");
            Object obj = new JSONParser().parse(reader);
            JSONArray spots = (JSONArray) obj;

            reader.close();
            Iterator<JSONObject> it = spots.iterator();
            while(it.hasNext())
            {
                JSONObject currentSpot = it.next();

                //check if the spot is reserved
                String claimed = "-";
                if(currentSpot.get("Status").equals("Unavailable")) {
                    FileReader reservedReader = new FileReader("src/main/resources/reserved_spots.json");
                    Object obj2 = new JSONParser().parse(reservedReader);
                    reservedReader.close();
                    JSONArray reservedSpots = (JSONArray) obj2;

                    Iterator<JSONObject> it2 = reservedSpots.iterator();
                    while (it2.hasNext()) {
                        JSONObject currentReserved = it2.next();
                        if (currentReserved.get("ID").equals(currentSpot.get("ID"))) {
                            claimed = (String) currentReserved.get("RegistrationNumber");
                        }
                    }
                }

                ManagerParkingSpot ps = new ManagerParkingSpot((String)currentSpot.get("ID"), (String)currentSpot.get("Floor"),
                        (String)currentSpot.get("PricePerHour"), (String)currentSpot.get("Status"), claimed);
                if(!ju.isSuspect(ps.getClaimed())) { //the if where the app figures out if it should display a report button
                    ps.removeButton();
                }

                avSpots.add(ps);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        floorColumn.setCellValueFactory(
                new PropertyValueFactory<>("floor")
        );
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        claimedColumn.setCellValueFactory(
                new PropertyValueFactory<>("claimed")
        );
        actionColumn.setCellValueFactory(
                new PropertyValueFactory<>("action")
        );

        table.setItems(avSpots);
    }

    @FXML
    public void handleProfileButton(){
        Main main;
        main = new Main();
        try {
            main.changeMainStage("/fxml/managerProfile.fxml", "Parking Application - Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleFinesButton(){
        Main main = new Main();

        try {
            main.changeMainStage("/fxml/managerFines.fxml", "Parking Application - Fines");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

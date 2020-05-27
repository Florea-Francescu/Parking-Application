package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.Main;
import main.java.entities.ParkingSpot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ViewAvailableSpotsController implements Initializable {
    @FXML
    public TableView table;
    @FXML
    public TableColumn<ParkingSpot, String> idColumn;
    @FXML
    public TableColumn<ParkingSpot, String> floorColumn;
    @FXML
    public TableColumn<ParkingSpot, String> priceColumn;
    @FXML
    public TableColumn<ParkingSpot, Button> actionColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<ParkingSpot> avSpots = FXCollections.observableArrayList();

        try {
            FileReader reader = new FileReader("src/main/resources/parking_spots.json");
            Object obj = new JSONParser().parse(reader);
            JSONArray spots = (JSONArray) obj;
            reader.close();
            Iterator<JSONObject> it = spots.iterator();
            while(it.hasNext())
            {
                JSONObject currentSpot = it.next();
                if(((String)currentSpot.get("Status")).equals("Available"))
                {
                    ParkingSpot ps = new ParkingSpot((String)currentSpot.get("ID"), (String)currentSpot.get("Floor"), (String)currentSpot.get("PricePerHour"));
                    avSpots.add(ps);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        floorColumn.setCellValueFactory(
                new PropertyValueFactory<>("floor")
        );

        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("pricePerHour")
        );

        actionColumn.setCellValueFactory(
                new PropertyValueFactory<>("action")
        );

        table.setItems(avSpots);
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

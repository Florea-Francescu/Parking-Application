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
import main.java.entities.FinedUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ManagerFinesController implements Initializable {

    @FXML
    TableView table;
    @FXML
    TableColumn<FinedUser, String> regNumberColumn;
    @FXML
    TableColumn<FinedUser, String> fstNameColumn;
    @FXML
    TableColumn<FinedUser, String> lstNameColumn;
    @FXML
    TableColumn<FinedUser, String> emailColumn;
    @FXML
    TableColumn<FinedUser, Button> actionColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<FinedUser> finedCars = FXCollections.observableArrayList();

        try {
            FileReader reader = new FileReader("src/main/resources/fined_cars.json");
            Object obj = new JSONParser().parse(reader);
            JSONArray allCars = (JSONArray)obj;
            reader.close();

            Iterator<JSONObject> it = allCars.iterator();
            while(it.hasNext()) {
                JSONObject currentCar = it.next();

                FileReader reader2 = new FileReader("src/main/resources/users.json");
                Object obj2 = new JSONParser().parse(reader2);
                JSONArray users = (JSONArray)obj2;
                reader2.close();

                Iterator<JSONObject> it2 = users.iterator();
                JSONObject currentUser = it2.next();
                do{
                    if(currentUser.get("RegistrationNumber").equals(currentCar.get("RegistrationNumber"))){
                        break;
                    }
                    currentUser = it2.next();
                }
                while(it2.hasNext());

                FinedUser fu = new FinedUser((String)currentUser.get("FirstName"), (String)currentUser.get("LastName"),
                        (String)currentUser.get("Email"), (String)currentUser.get("RegistrationNumber"));

                finedCars.add(fu);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        regNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("regNumber")
        );
        fstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("fstName")
        );
        lstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lstName")
        );
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        actionColumn.setCellValueFactory(
                new PropertyValueFactory<>("action")
        );

        table.setItems(finedCars);
    }

    @FXML
    public void handleProfileButton(){
        Main main = new Main();
        try {
            main.changeMainStage("/fxml/managerProfile.fxml", "Parking Application - Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleParkedCarsButton(){
        Main main = new Main();
        try {
            main.changeMainStage("/fxml/viewAllSpots.fxml", "Parking Application - Parking Spots");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

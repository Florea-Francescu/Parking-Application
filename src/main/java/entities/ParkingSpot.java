package main.java.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ParkingSpot {
    private String id;
    private String floor;
    private String pricePerHour;
    private Button action;

    public ParkingSpot(String id, String floor, String pricePerHour)
    {
        this.id = id;
        this.floor = floor;
        this.pricePerHour = pricePerHour;
        action = new Button("Claim");
        action.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation dialogue");
                alert.setHeaderText("Please, confirm this parking spot");
                alert.setContentText("Do you want to claim this spot?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                } else {
                    // cancel
                }
            });
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFloor(String floor) {
        this.floor= floor;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public String getFloor() {
        return floor;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public Button getAction() {
        return action;
    }
}

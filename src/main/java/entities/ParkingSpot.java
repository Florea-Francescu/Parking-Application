package main.java.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

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

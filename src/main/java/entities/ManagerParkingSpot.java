package main.java.entities;

import javafx.scene.control.Button;

public class ManagerParkingSpot {
    private String id;
    private String floor;
    private String price;
    private String status;
    private String claimed;
    private Button action;

    public ManagerParkingSpot(String id, String floor, String price, String status, String claimed){
        this.id = id;
        this.floor = floor;
        this.price = price;
        this.status = status;
        this.claimed = claimed;
        action = new Button("Report");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClaimed() {
        return claimed;
    }

    public void setClaimed(String claimed) {
        this.claimed = claimed;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
}

package main.java.entities;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import static java.lang.Integer.parseInt;


public class ParkingSpot {
    private String id;
    private String floor;
    private String pricePerHour;
    private Button action;
    public static int price;
    public static String idSpot;
    public ParkingSpot(String id, String floor, String pricePerHour)
    {
        this.id = id;
        this.floor = floor;
        this.pricePerHour = pricePerHour;
        action = new Button("Claim");
        action.setOnAction(event -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Confirm the parking spot");
            try {
                price = parseInt(pricePerHour);
                idSpot = id;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/reserveSpot.fxml"));
                Parent root = (Parent)fxmlLoader.load();
                window.setScene(new Scene(root,400,200));
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
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

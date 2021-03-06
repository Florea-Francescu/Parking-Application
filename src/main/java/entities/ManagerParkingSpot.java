package main.java.entities;

import javafx.scene.control.Button;
import main.java.utils.JavaMailUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.mail.MessagingException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class ManagerParkingSpot {
    private String id;
    private String floor;
    private String price;
    private String status;
    private String claimed;
    private Button action;

    private JSONObject carOwner = null;

    public ManagerParkingSpot(String id, String floor, String price, String status, String claimed){
        this.id = id;
        this.floor = floor;
        this.price = price;
        this.status = status;
        this.claimed = claimed;
        action = new Button("Report");
        addButtonFunctionality();
    }

    private void addButtonFunctionality(){
        action.setOnAction(e -> {
            String message = "";
            findCarOwner();
            message = "The car with the registration number " + claimed + ", owned by " + carOwner.get("FirstName") + " " +
                     carOwner.get("LastName") + " was reported by the application! It is parked at the " + id + " parking spot.";

            try {
                JavaMailUtils.sendMail("parkingapplication3@gmail.com", "Parking Application - SUSPICIOUS ACTIVITY!", message);
            } catch (MessagingException messagingException) {
                messagingException.printStackTrace();
            }
            action.setText("Reported!");
            action.setDisable(true);
        });
    }

    private void findCarOwner(){
        try {
            FileReader reader = new FileReader(getClass().getResource("/users.json").getPath());
            Object obj = new JSONParser().parse(reader);
            reader.close();
            JSONArray users = (JSONArray)obj;

            Iterator<JSONObject> it = users.iterator();
            while (it.hasNext()){
                JSONObject current = it.next();
                if(current.get("RegistrationNumber").equals(this.claimed)) {
                    this.carOwner = current;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void removeButton(){
        this.action = null;
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

package main.java.entities;

import javafx.scene.control.Button;
import main.java.utils.JavaMailUtils;

import javax.mail.MessagingException;

public class FinedUser {
    private String fstName;
    private String lstName;
    private String email;
    private String regNumber;
    private Button action;

    public FinedUser(String fstName, String lstName, String email, String regNumber) {
        this.fstName = fstName;
        this.lstName = lstName;
        this.email = email;
        this.regNumber = regNumber;

        action = new Button("Report");
        addButtonFunctionality();
    }

    private void addButtonFunctionality(){
        action.setOnAction(e -> {
            String message = "The car with the registration number " + regNumber + ", owned by " + fstName + " " + lstName +
                    " (" + email + ")" + " was reported by the parking application for not paying their fines!";
            try {
                JavaMailUtils.sendMail("parkingapplication3@gmail.com", "Parking Application - UNPAID FINES!", message);
            } catch (MessagingException messagingException) {
                messagingException.printStackTrace();
            }
            action.setText("Reported!");
            action.setDisable(true);
        });
    }

    public String getFstName() {
        return fstName;
    }

    public void setFstName(String fstName) {
        this.fstName = fstName;
    }

    public String getLstName() {
        return lstName;
    }

    public void setLstName(String lstName) {
        this.lstName = lstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
}

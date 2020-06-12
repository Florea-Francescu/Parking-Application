package main.java.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.controllers.CheckProfileController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class OtherUtils {
    public boolean isSuspect(String registrationNumber) {
        boolean answer = false;

        try {
            FileReader fr = new FileReader(getClass().getResource("/suspect_cars.json").getPath());
            Object obj = new JSONParser().parse(fr);
            fr.close();

            JSONArray allCars = (JSONArray)obj;

            Iterator<JSONObject> it = allCars.iterator();
            while(it.hasNext()){
                JSONObject currentCar = it.next();

                if(currentCar.get("RegistrationNumber").equals(registrationNumber)){
                    answer = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return answer;
    }

    public static JSONObject getUser(String regNumber){
        JSONObject user = null;

        try {
            FileReader reader = new FileReader("src/main/resources/users.json");
            Object obj = new JSONParser().parse(reader);
            reader.close();
            JSONArray users = (JSONArray)obj;

            Iterator<JSONObject> it = users.iterator();
            while(it.hasNext()){
                JSONObject jo = it.next();
                if(jo.get("RegistrationNumber").equals(regNumber)){
                    user = jo;
                    break;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void createCheckProfile(JSONObject user) throws IOException {
        String title = createCheckProfileTitle(user);

        CheckProfileController.setUser(user);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/checkProfile.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        window.setScene(new Scene(root));
        window.show();
    }

    private static String createCheckProfileTitle(JSONObject user){
        return (String)user.get("FirstName") + " " + (String)user.get("LastName") + "'s Profile";
    }
}

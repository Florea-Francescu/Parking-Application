package main.java.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
}

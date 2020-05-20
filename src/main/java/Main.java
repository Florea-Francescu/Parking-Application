package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

public class Main extends Application {

    private static Stage mainWindow;
    public static JSONObject currentUser = null;

    public void changeMainStage(String name, String title) throws Exception{
        Parent newLayout = FXMLLoader.load(getClass().getResource(name));
        mainWindow.setTitle(title);
        mainWindow.setScene(new Scene(newLayout));
        mainWindow.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        primaryStage.setTitle("Parking Application - Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

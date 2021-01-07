package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.FileManagerController;
import ro.mta.se.lab.model.Settings;

import java.io.IOException;
import java.util.Set;

import static ro.mta.se.lab.controller.WeatherViewController.initializeMenuButton;

/**
 * @author VladTeapa
 *
 * Main class
 */

public class Main extends Application {

    private static Scene scene;

    /**
     * Start function that is called at start of the program
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Settings.countryList = FileManagerController.readConfigFile(Settings.fileInput);

        scene = new Scene(loadFXML("./view/WeatherView"));
        stage.setScene(scene);

        initializeMenuButton(scene, Settings.countryList);
        Settings.scene = scene;

        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
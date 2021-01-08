package ro.mta.se.lab.model;

import javafx.scene.Scene;

import java.util.List;

/**
 * @author VladTeapa
 * Class that has parameters for the program such as input file and api key and other parameters that the app will set
 */
public class Settings {
    /**
     * Hardcoded params
     */
    public final static String fileInput = "src/main/resources/ro/mta/se/lab/config/config.cfg";
    public final static String apiKey = "069426cb83249d34ad107aeeb36205a7";
    public final static String loggerStatus = "src/main/resources/ro/mta/se/lab/logger/loggerStatus";
    public final static String loggerHistory = "src/main/resources/ro/mta/se/lab/logger/loggerHistory";

    /**
     * Dynamic params
     */
    public static List<Country> countryList;
    public static Scene scene;

    private Settings(){

    }
}

package ro.mta.se.lab.controller;

import org.json.JSONObject;
import ro.mta.se.lab.model.Settings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * WeatherApiController
 * @author VladTeapa
 */

public class WeatherApiController {

    private final FileManagerController fileManagerController;

    public WeatherApiController(FileManagerController fileManagerController) {
        this.fileManagerController = fileManagerController;
    }

    /**
     * Function that returns weather data for a certain city
     * @param city is the city where we want to see what the weather is like
     * @return is the data about weather
     */
    public JSONObject getWeather(String city) {
        JSONObject jsonObject = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + Settings.apiKey))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonObject = new JSONObject(response.body().toString());
        } catch (IOException | InterruptedException e) {
            fileManagerController.writeToLog(Settings.loggerStatus, "Api error!", FileManagerController.APPEND, FileManagerController.ERROR);
            return null;
        }
        fileManagerController.writeToLog(Settings.loggerHistory, (jsonObject.toString()), FileManagerController.APPEND, FileManagerController.INFO);
        return jsonObject;
    }
}

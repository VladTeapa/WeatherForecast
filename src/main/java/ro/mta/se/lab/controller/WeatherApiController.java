package ro.mta.se.lab.controller;

import org.json.JSONObject;
import ro.mta.se.lab.model.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class WeatherApiController {

    public WeatherApiController() {

    }

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
            e.printStackTrace();
        }
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
}

package ro.mta.se.lab.controller;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import org.json.JSONObject;
import ro.mta.se.lab.model.Country;
import ro.mta.se.lab.model.Settings;

import java.util.ArrayList;
import java.util.List;

public class WeatherViewController {

    public static void initializeMenuButton(Scene scene, List<Country> countryList){
        MenuButton menuButton = (MenuButton) scene.lookup("#countryDropBox");
        if(menuButton == null){
            System.out.println("Menu button is null!");
            return;
        }
        if(countryList == null)
            return;
        for(Country country: countryList){
            MenuItem menuItem = new MenuItem(country.getCode());
            menuItem.setOnAction(event->{
                System.out.println("Changed country!");
                menuButton.setText(menuItem.getText());
                MenuButton cityMenuButton = (MenuButton) scene.lookup("#cityDropBox");
                cityMenuButton.getItems().clear();
                cityMenuButton.setText("City");
                for(int j = 0;j<country.getCityList().size();j++){
                    MenuItem item = new MenuItem(country.getCityList().get(j).getDenumire());
                    item.setOnAction(e->{
                        System.out.println("Changed city!");
                        cityMenuButton.setText(item.getText());
                        WeatherApiController weatherApiController = new WeatherApiController();
                        JSONObject jsonObject = weatherApiController.getWeather(item.getText());

                        Label temperatureLabel = (Label) scene.lookup("#temperatureLabel");
                        Label humidityLabel = (Label) scene.lookup("#humidityLabel");
                        Label windSpeedLabel = (Label) scene.lookup("#windSpeedLabel");
                        Label pressureLabel = (Label) scene.lookup("#pressureLabel");

                        temperatureLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("temp") - 272.15)) +" \u2103");
                        humidityLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("humidity"))) +"%");
                        windSpeedLabel.setText(String.format("%.1f Km/H", (jsonObject.getJSONObject("wind").getDouble("speed")*3.6)));
                        pressureLabel.setText(String.format("%.1f hPa", (jsonObject.getJSONObject("main").getDouble("pressure"))));

                    });
                    cityMenuButton.getItems().add(item);
                    System.out.println(item.getText());
                }
            }); // add functionality
            menuButton.getItems().add(menuItem);
        }
    }
}

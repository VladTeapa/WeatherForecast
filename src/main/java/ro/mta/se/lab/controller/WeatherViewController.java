package ro.mta.se.lab.controller;

import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
                for(int j = 0;j<country.getCityList().size();j++){
                    MenuItem item = new MenuItem(country.getCityList().get(j).getDenumire());
                    item.setOnAction(e->{
                        System.out.println("Changed city!");
                        cityMenuButton.setText(item.getText());
                    });
                    cityMenuButton.getItems().add(item);
                    System.out.println(item.getText());
                }
            }); // add functionality
            menuButton.getItems().add(menuItem);
        }
    }
}

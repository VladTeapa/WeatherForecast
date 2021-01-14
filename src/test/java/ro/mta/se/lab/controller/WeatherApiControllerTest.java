package ro.mta.se.lab.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class WeatherApiControllerTest {

    @Mock
    FileManagerController fileManagerController;

    @BeforeEach
    void setUp() {
        fileManagerController = mock(FileManagerController.class);
    }

    @Test
    public void weatherApi(){
        WeatherApiController weatherApiController = new WeatherApiController(fileManagerController);
        assertEquals(weatherApiController.getWeather("Bucharest").getString("name"), "Bucharest");
        assertEquals(weatherApiController.getWeather("Bucharest").getInt("cod"), 200);
        assertEquals(weatherApiController.getWeather("Bucharest").getInt("id"), 683506);
        assertEquals(weatherApiController.getWeather("Bucharest").getInt("timezone"), 7200);
        assertEquals(weatherApiController.getWeather("Paris").getInt("timezone"), 3600);
        assertEquals(weatherApiController.getWeather("Paris").getString("name"), "Paris");
        assertEquals(weatherApiController.getWeather("Tokyo").getString("name"), "Tokyo");
        assertEquals(weatherApiController.getWeather("Tokyo").getJSONObject("sys").getString("country"), "JP");
        assertEquals(weatherApiController.getWeather("Tokyo").getInt("timezone"), 32400);
        assertEquals(weatherApiController.getWeather("Tokyo").getInt("id"), 1850144);
        System.out.println("Passed weatherApi!");
    }
}
package ro.mta.se.lab.controller;

import javafx.scene.Scene;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class WeatherViewControllerTest {

    @Mock
    Scene scene;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();


    @Test
    void initializeMenuButton() {
        assertEquals(WeatherViewController.initializeMenuButton(scene), true);
    }
}
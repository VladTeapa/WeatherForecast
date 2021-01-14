package ro.mta.se.lab.controller;

import javafx.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
class WeatherViewControllerTest {

    @Mock
    Scene scene;

    @BeforeEach
    void setUp() {
        scene = mock(Scene.class);
    }


    @Test
    void initializeMenuButton() {
        assertFalse(WeatherViewController.initializeMenuButton(scene));
        assertThrows(NullPointerException.class, () -> {
            WeatherViewController.initializeMenuButton(null);
        });
    }
}
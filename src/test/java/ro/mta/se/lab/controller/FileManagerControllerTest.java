package ro.mta.se.lab.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.mta.se.lab.model.Settings;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerControllerTest {

    FileManagerController fileManagerController;

    @BeforeEach
    void setUp() {
        fileManagerController = new FileManagerController();
    }

    @Test
    void readConfigFile() {
        assertEquals(fileManagerController.readConfigFile(null), null);
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).size(), 10);
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(0).getCode(), "RO");
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(0).getCityList().size(), 2);
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(0).getCityList().get(0).getDenumire(), "Bucharest");
        System.out.println("Passed readConfigFile!");
    }

    @Test
    void writeToLog() {
        assertEquals(fileManagerController.writeToLog(null, null, 0, 0), -1);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "", FileManagerController.NEW, FileManagerController.INFO), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "", FileManagerController.APPEND, FileManagerController.WARNING), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "", FileManagerController.NEW, FileManagerController.ERROR), 0);
        System.out.println("Passed writeToLog!");
    }
}
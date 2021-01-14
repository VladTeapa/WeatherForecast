package ro.mta.se.lab.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.mta.se.lab.model.Settings;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(0).getCityList().get(1).getDenumire(), "Oradea");
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(1).getCityList().get(1).getDenumire(), "Lyon");
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(1).getCityList().get(0).getDenumire(), "Paris");
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(9).getCityList().get(1).getDenumire(), "Kyoto");
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(1).getCode(), "FR");
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(1).getCityList().get(0).getId(), 3);
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(1).getCityList().get(0).getLatitude(), 48.856613);
        assertEquals(fileManagerController.readConfigFile(Settings.fileInput).get(1).getCityList().get(0).getLongitude(), 2.352222);
        System.out.println("Passed readConfigFile!");
    }

    @Test
    void writeToLog() {
        assertEquals(fileManagerController.writeToLog(null, null, 0, 0), -1);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "", FileManagerController.NEW, FileManagerController.INFO), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "", FileManagerController.APPEND, FileManagerController.WARNING), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "", FileManagerController.NEW, FileManagerController.ERROR), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "DATADATADATA", FileManagerController.NEW, FileManagerController.ERROR), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "DATADATADATA", FileManagerController.APPEND, FileManagerController.WARNING), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, "DATADATADATA", FileManagerController.NEW, FileManagerController.INFO), 0);
        assertEquals(fileManagerController.writeToLog(Settings.loggerStatus, null, FileManagerController.APPEND, FileManagerController.INFO), 0);
        System.out.println("Passed writeToLog!");
    }
}
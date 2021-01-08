package ro.mta.se.lab.controller;

import ro.mta.se.lab.model.City;
import ro.mta.se.lab.model.Country;
import ro.mta.se.lab.model.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author VladTeapa
 * File manager class
 */
public class FileManagerController {

    /**
     * Parameters that can be used to tell when logging if you want to append or create a new file
     */
    public final static int APPEND = 0;
    public final static int NEW = 1;

    /**
     * Parameters that can be used to tell if the logging data is info, warning or error
     */
    public final static int INFO = 0;
    public final static int WARNING = 1;
    public final static int ERROR = 2;

    /**
     * Reads the cities and countries from file
     * @param path is where the config file is located
     * @return
     */
    public static List<Country> readConfigFile(String path){
        boolean flag;
        List<Country> result = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            FileManagerController.writeToLog(Settings.loggerStatus, "File is null!", FileManagerController.APPEND, FileManagerController.ERROR);
            return null;
        }

        if (file.isDirectory()) {
            FileManagerController.writeToLog(Settings.loggerStatus, "File is a directory!", FileManagerController.APPEND, FileManagerController.ERROR);
            return null;
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException){
            FileManagerController.writeToLog(Settings.loggerStatus, "File not found!", FileManagerController.APPEND, FileManagerController.ERROR);
            return null;
        }
        String fileData = null;
        while(scanner.hasNextLine()){
            fileData = scanner.nextLine();
            String data[] = fileData.split("\\s+");
            if(data.length!=5){
                System.out.println(data.length);
                System.out.println("Bad file format!");
                return null;
            }
            City city = new City();
            city.setId(Integer.parseInt(data[0]));
            city.setDenumire(data[1]);
            city.setLatitude(Double.parseDouble(data[2]));
            city.setLongitude(Double.parseDouble(data[3]));
            flag = false;
            for (Country country : result) {
                if (country.getCode().equals(data[4])) {
                    flag = true;
                    country.getCityList().add(city);
                    break;
                }
            }
            if(!flag){
                Country aux = new Country(new ArrayList<>(), data[4]);
                aux.getCityList().add(city);
                result.add(aux);
            }
        }
        FileManagerController.writeToLog(Settings.loggerStatus, "Finished reading config file!", FileManagerController.APPEND, FileManagerController.INFO);
        return result;
    }

    /**
     * Function that writes to a log file
     * @param file the file to be written in
     * @param data data to written
     * @param type if the function should append or write to new file
     * @param logLevel log level, if log level is -1 console logging is disabled
     * @return 0 for succes, everything else is error
     */
    public static int writeToLog(String file, String data, int type, int logLevel){
        try {
            FileWriter fileWriter = null;
            if(type == FileManagerController.APPEND)
                fileWriter = new FileWriter(file, true);
            else
                fileWriter = new FileWriter(file, false);
            fileWriter.write(data + "\n");
            String dataFile = null;
            boolean flag = true;
            switch (logLevel){
                case INFO:
                    dataFile = "Info: ";
                    break;
                case WARNING:
                    dataFile = "Warning: ";
                    break;
                case ERROR:
                    dataFile = "Error: ";
                    break;
                default:
                    flag = false;
                    break;
            }
            if(flag)
                System.out.println(dataFile + data + "\n");
            fileWriter.close();
        } catch (IOException e){
            return -1;
        }
        return 0;
    }

    /**
     * We don't need to be able to create an instance
     */
    private FileManagerController(){

    }
}

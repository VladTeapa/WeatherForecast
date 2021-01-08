package ro.mta.se.lab.controller;

import ro.mta.se.lab.model.City;
import ro.mta.se.lab.model.Country;

import java.io.File;
import java.io.FileNotFoundException;
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
    public static int APPEND = 0;
    public static int NEW = 1;


    /**
     * Reads the cities and countries from file
     * @param path
     * @return
     */
    public static List<Country> readConfigFile(String path){
        boolean flag;
        List<Country> result = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not existing!");
            return null;
        }

        if (file.isDirectory()) {
            System.out.println("File is directory!");
            return null;
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("File not found!");
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
        System.out.println("Finished reading config file!");
        return result;
    }

    /**
     * Function that writes to a log file
     * @param file the file to be written in
     * @param type if the function should append or write to new file
     * @return 0 for succes, everything else is error
     */
    public static int writeToLog(String file, int type){
        return 0;
    }

    /**
     * We don't need to be able to create an instance
     */
    private FileManagerController(){

    }
}

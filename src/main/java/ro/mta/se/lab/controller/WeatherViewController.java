package ro.mta.se.lab.controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONObject;
import ro.mta.se.lab.model.Country;
import ro.mta.se.lab.model.Settings;

import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 * WeatherViewController class
 * @author VladTeapa
 */
public class WeatherViewController {

    /**
     * This function initialises the menu buttons and defines the event listeners for menu buttons and menu items
     * @param scene
     */
    public static boolean initializeMenuButton(Scene scene) {
        FileManagerController fileManagerController = new FileManagerController();
        List<Country> countryList = Settings.countryList;

        MenuButton menuButton = (MenuButton) scene.lookup("#countryDropBox");

        if (menuButton == null) {
            System.out.println("Menu button is null!");
            return false;
        }

        if (countryList == null)
            return false;

        for (Country country : countryList) {
            MenuItem menuItem = new MenuItem(country.getCode());
            menuItem.setOnAction(event -> {

                /**
                 * Event listener for menuButton
                 */

                fileManagerController.writeToLog(Settings.loggerStatus, "Changed country!", FileManagerController.APPEND, FileManagerController.INFO);
                menuButton.setText(menuItem.getText());
                MenuButton cityMenuButton = (MenuButton) scene.lookup("#cityDropBox");
                cityMenuButton.getItems().clear();
                cityMenuButton.setText("City");
                for (int j = 0; j < country.getCityList().size(); j++) {
                    MenuItem item = new MenuItem(country.getCityList().get(j).getDenumire());
                    item.setOnAction(e -> {

                        /**
                         * Event listener for menuItem
                         **/

                        fileManagerController.writeToLog(Settings.loggerStatus, "Changed city!", FileManagerController.APPEND, FileManagerController.INFO);
                        cityMenuButton.setText(item.getText());
                        WeatherApiController weatherApiController = new WeatherApiController(new FileManagerController());
                        JSONObject jsonObject = weatherApiController.getWeather(item.getText());


                        /**
                         * Getting labels references
                         */

                        Label temperatureLabel = (Label) scene.lookup("#temperatureLabel");
                        Label humidityLabel = (Label) scene.lookup("#humidityLabel");
                        Label windSpeedLabel = (Label) scene.lookup("#windSpeedLabel");
                        Label pressureLabel = (Label) scene.lookup("#pressureLabel");
                        Label countryCityLabel = (Label) scene.lookup("#countryCityLabel");
                        Label timeLabel = (Label) scene.lookup("#timeLabel");
                        Label currentWeatherLabel = (Label) scene.lookup("#currentWeatherLabel");

                        ImageView imageView = (ImageView) scene.lookup("#weatherImageView");

                        /**
                         * Modifying the labels
                         */

                        temperatureLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("temp") - 272.15)) + " \u2103");
                        humidityLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("humidity"))) + "%");
                        windSpeedLabel.setText(String.format("%.1f Km/H", (jsonObject.getJSONObject("wind").getDouble("speed") * 3.6)));
                        pressureLabel.setText(String.format("%.1f hPa", (jsonObject.getJSONObject("main").getDouble("pressure"))));
                        currentWeatherLabel.setText(jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
                        countryCityLabel.setText(country.getCode() + ", " + item.getText());

                        /**
                         * Changing the time label
                         */

                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MILLISECOND, -calendar.getTimeZone().getOffset(calendar.getTimeInMillis()));
                        calendar.add(Calendar.SECOND, jsonObject.getInt("timezone"));

                        timeLabel.setText(calendar.getTime().toString());

                        /**
                         * Changing the image
                         */

                        StringBuilder stringBuilder = new StringBuilder(jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon"));
                        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);

                        String url = stringBuilder.toString();
                        url = "file:./src/main/resources/ro/mta/se/lab/img/" + url + ".png";
                        fileManagerController.writeToLog(Settings.loggerStatus, "Image url: " + url, FileManagerController.APPEND, FileManagerController.INFO);
                        imageView.setImage(new Image(url));

                    });
                    cityMenuButton.getItems().add(item);
                    fileManagerController.writeToLog(Settings.loggerStatus,item.getText(), FileManagerController.APPEND, FileManagerController.INFO);
                }
            });
            menuButton.getItems().add(menuItem);
        }
        return true;
    }
}

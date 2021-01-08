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

import java.util.Calendar;
import java.util.List;

public class WeatherViewController {

    public static void initializeMenuButton(Scene scene) {
        List<Country> countryList = Settings.countryList;

        MenuButton menuButton = (MenuButton) scene.lookup("#countryDropBox");

        if (menuButton == null) {
            System.out.println("Menu button is null!");
            return;
        }

        if (countryList == null)
            return;

        for (Country country : countryList) {
            MenuItem menuItem = new MenuItem(country.getCode());
            menuItem.setOnAction(event -> {
                /**
                 * Event listener for menuButton
                 */
                System.out.println("Changed country!");
                menuButton.setText(menuItem.getText());
                MenuButton cityMenuButton = (MenuButton) scene.lookup("#cityDropBox");
                cityMenuButton.getItems().clear();
                cityMenuButton.setText("City");
                for (int j = 0; j < country.getCityList().size(); j++) {
                    MenuItem item = new MenuItem(country.getCityList().get(j).getDenumire());
                    item.setOnAction(e -> {
                        /**
                         * Event listener for menuItem
                         */
                        System.out.println("Changed city!");
                        cityMenuButton.setText(item.getText());
                        WeatherApiController weatherApiController = new WeatherApiController();
                        JSONObject jsonObject = weatherApiController.getWeather(item.getText());

                        Label temperatureLabel = (Label) scene.lookup("#temperatureLabel");
                        Label humidityLabel = (Label) scene.lookup("#humidityLabel");
                        Label windSpeedLabel = (Label) scene.lookup("#windSpeedLabel");
                        Label pressureLabel = (Label) scene.lookup("#pressureLabel");
                        Label countryCityLabel = (Label) scene.lookup("#countryCityLabel");
                        Label timeLabel = (Label) scene.lookup("#timeLabel");
                        Label currentWeatherLabel = (Label) scene.lookup("#currentWeatherLabel");

                        ImageView imageView = (ImageView) scene.lookup("#weatherImageView");

                        temperatureLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("temp") - 272.15)) + " \u2103");
                        humidityLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("humidity"))) + "%");
                        windSpeedLabel.setText(String.format("%.1f Km/H", (jsonObject.getJSONObject("wind").getDouble("speed") * 3.6)));
                        pressureLabel.setText(String.format("%.1f hPa", (jsonObject.getJSONObject("main").getDouble("pressure"))));
                        currentWeatherLabel.setText(jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
                        countryCityLabel.setText(country.getCode() + ", " + item.getText());

                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MILLISECOND, -calendar.getTimeZone().getOffset(calendar.getTimeInMillis()));
                        calendar.add(Calendar.SECOND, jsonObject.getInt("timezone"));

                        timeLabel.setText(calendar.getTime().toString());

                        StringBuilder stringBuilder = new StringBuilder(jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon"));
                        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);

                        String url = stringBuilder.toString();
                        url = "file:./src/main/resources/ro/mta/se/lab/img/" + url + ".png";
                        System.out.println("Image url: "+url);
                        imageView.setImage(new Image(url));

                    });
                    cityMenuButton.getItems().add(item);
                    System.out.println(item.getText());
                }
            }); // add functionality
            menuButton.getItems().add(menuItem);
        }
    }
}

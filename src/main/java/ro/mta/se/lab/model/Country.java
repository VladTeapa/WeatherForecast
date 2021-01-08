package ro.mta.se.lab.model;

import java.util.List;

/**
 * @author VladTeapa
 * Class that has a number of cities
 */
public class Country {

    /**
     * The first param is a list of cities for a country and the code identifies the country
     */

    private List<City> cityList;
    private String code;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Country() {
    }

    public Country(List<City> cityList, String code) {
        this.cityList = cityList;
        this.code = code;
    }
}

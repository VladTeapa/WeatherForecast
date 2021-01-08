package ro.mta.se.lab.model;

/**
 * @author VladTeapa
 * Class that defines a city
 */
public class City {

    /**
     * These params define the data that interests us for a city
     */

    private double longitude;
    private double latitude;
    private int id;
    private String denumire;

    public City() {
    }

    public City(double longitude, double latitude, int id, String denumire) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
        this.denumire = denumire;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}

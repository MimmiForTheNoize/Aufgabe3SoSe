package org.lecture;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class Winds {
    private int id;
    private String speed;
    private String date;
    private String station;
    private Direction direction;//maybe enum???
    private String weather;
    private float temp;

/*

    public Winds(int id,String date,String time,String station,Direction direction,String weather,float temp) {
        this.id = id;
        this.speed = speed;
        this.time = time;
        this.station = station;
        this.direction = direction;
        this.weather = weather;
        this.temp = temp;
    }
 */

    public void setId(int id) {
        this.id = id;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }


    public void setStation(String station) {
        this.station = station;
    }

    public String getStation() {
        return station;
    }

    public int getId() {
        return id;
    }

    public String getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getWeather() {
        return weather;
    }

    public float getTemp() {
        return temp;
    }

    public String getDate() {
        return date;
    }


    //Knoten (km/h*0,53996) und Beaufort ((Knoten + 5)/5) unter Beachtung der Skala
    public int knots(String speed) {
        float kmh = Float.parseFloat(speed);
        int knot = (int) (kmh*0.53996);

        return knot;
    }

    public int beaufort(String speed) {
        float kmh = Float.parseFloat(speed);

        int knot = (int) (kmh*0.53996);
        int beau = ((knot +5)/5);

        return beau;
    }

}
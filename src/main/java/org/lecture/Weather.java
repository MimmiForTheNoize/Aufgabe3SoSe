package org.lecture;

import lombok.Getter;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the representation of a weather entry
 * There are mandatory and optional values:
 * id (mandatory)
 * speed (mandatory/user input necessary)
 * date (mandatory)
 * station (mandatory/user input necessary)
 * direction (optional/user input necessary)
 * weather (optional/user input necessary)
 * temp (optional/user input necessary)
 */
@Getter
public class Weather {
    private final int id;
    private final String speed;
    private final String date;
    private final String station;
    private final Direction direction;
    private final String weather;
    private final float temp;
    private final Double knots;
    private final Double beaufort;

    /**
     *
     * @param id                id of entry
     * @param speed             wind speed
     * @param date              date of entry
     * @param station           weather station
     * @param direction         wind direction
     * @param weather           weather description
     * @param temp              temperature of recording
     */
    private Weather(int id, String speed, String date, String station, Direction direction, String weather, float temp) {
        this.id = id;
        this.speed = speed;
        this.date = date;
        this.station = station;
        this.direction = direction;
        this.weather = weather;
        this.temp = temp;

        String replacedSpeed = speed;
        if (speed.contains(",")) {
            replacedSpeed.replace(",", ".");
        }
        Double converted = Double.valueOf(replacedSpeed);

        this.knots = converted * 0.53996;
        this.beaufort = (knots + 5) / 5;
    }

    /**
     *
     * @return all values in this format
     */

    @Override
    public String toString() {
        return id + " Speed: " + speed + " date: " + date + " station: " + station + " direction: " + direction + " weather:  " + weather + " temperature: " + temp;
    }

    public void getExtreme() {
        String replacedSpeed = speed;
        String attention = "";
        if (speed.contains(",")) {
            replacedSpeed.replace(",", ".");
        }
        Double converted = Double.valueOf(replacedSpeed);
        if (converted < 2.0) {
            System.out.println("calm and windless");
            attention = "calm and windless";
        } else if (converted >= 120) {
            System.out.println("orkan");
            attention = "orkan";
            System.out.println(attention);
        }
    }

    /**
     * Builder for the weather Object
     */

    public static class WeatherBuilder {
        private int id;
        private String speed;
        private String date;
        private String station;
        private Direction direction;
        private String weather;
        private float temp;

        public WeatherBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public WeatherBuilder withSpeed(String speed) {
            this.speed = speed;
            return this;
        }

        public WeatherBuilder withStation(String station) {
            this.station = station;
            return this;
        }

        public WeatherBuilder withDirection(String direction) {
            Direction dir = Direction.valueOf(direction);
            //if value try catch and then default NaN
            this.direction = dir;
            return this;
        }

        public WeatherBuilder withWeather(String weather) {
            this.weather = weather;
            return this;
        }

        public WeatherBuilder withTemp(float temp) {
            this.temp = temp;
            return this;
        }

        public Weather build() {
            if (weather == null) {
                this.weather = "cloudy";
            }
            if (date == null) {
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                this.date = localDateTime.format(format);
            }
            return new Weather(id, speed, date, station, direction, weather, temp);
        }

    }




}

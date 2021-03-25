package org.lecture;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Weather {
        private int id;
        private String speed;
        private String date;
        private String station;
        private Direction direction;
        private String weather;
        private float temp;
        private Double knots;
        private Double beaufort;

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

    public String getExtreme() {
        String replacedSpeed = speed;
        if (speed.contains(",")) {
            replacedSpeed.replace(",", ".");
        }
        Double converted = Double.valueOf(replacedSpeed);
        return converted < 2.0 ? "windstill" : converted >= 120 ? "orkan" : "";
    }

    @Override
    public String toString() {
        // TODO fix to string
        return  id + ". Wetter vom " + date + "blablab";
    }

    public static class WeatherBuilder {
        private int id;
        private String speed;
        private String date;
        private String station;
        private Direction direction;
        private String weather;
        private float temp;

        public WeatherBuilder withId(int id) {
            this.id=id;
            return this;
        }

        public WeatherBuilder withSpeed(String speed) {
            this.speed=speed;
            return this;
        }
        public WeatherBuilder withStation(String station) {
            this.station=station;
            return this;
        }

        public WeatherBuilder withDirection(String direction) {
            Direction dir=Direction.valueOf(direction);
            //if value try catch and then default NaN
            this.direction=dir;
            return this;
        }
        public WeatherBuilder withWeather(String weather) {
            this.weather=weather;
            return this;
        }
        public WeatherBuilder withTemp(float temp) {
            this.temp=temp;
            return this;
        }
        public Weather build() {
            if(weather== null) {
                this.weather="cloudy";
            }
            if (date==null) {
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                this.date = localDateTime.format(format);
            }
            return new Weather(id, speed, date, station, direction, weather, temp);
        }

    }

}

package org.lecture;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherTest {
    Weather actual = new Weather.WeatherBuilder().withId(9).build();

/*
    Weather weather = new Weather.WeatherBuilder()
            .withId(9)
            .withSpeed("5.2")
            .withStation("TestStation")
            .withWeather("snow")
            .withDirection("W")
            .withTemp((float) 15.2)
            .build();
 */

  //
    @Test
    public void buildMethod() {
        Weather weather = new Weather.WeatherBuilder()
                .withId(9)
                .withSpeed("5.2")
                .withStation("TestStation")
                .withWeather("snow")
                .withDirection("W")
                .withTemp((float) 15.2)
                .build();
        Assertions.assertTrue((BooleanSupplier) weather);
    }

    /*
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
     */


    //asserting default Vals
    //void assertEquals("0.0",actual.getSpeed());



}

package org.lecture;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class WeatherDriverTest {

    //public void  something something b
    @Test
    public void readFileFalse() {
        String is = "";
        String checkEmpty = WeatherDriver.readFile(is);
        Assertions.assertFalse(Boolean.parseBoolean(checkEmpty));
    }


    @Test
    public void readFilePath() {
        //checks the content that will be returned
        String test = "src/test/java/resources/TDD.txt";
        String checkThis = WeatherDriver.readFile(test);
        String testStr = "This is a test";
        Assertions.assertEquals(testStr, checkThis);
    }
    @Test
    public void testUserInput() {

  /*
        Weather weather = new Weather.WeatherBuilder()
                .withId(9)
                .withSpeed("2.5")
                .withStation("TestStation")
                .withWeather("testweather")
                .withDirection("W")
                .withTemp((float) 15.2)
                .build();

        String weatherStr = weather.toString();
   */

        //simulate input
        String input =
                "1"
                + "\n"
                + "1"
                + "\n"
                + "1"
                + "\n"
                + "2.5"
                + "\n"
                + "TestStation"
                + "\n"
                +"yes"
                + "\n"
                +"SE"
                + "\n"
                +"yes"
                + "\n"
                +"snow"
                + "\n"
                + "yes"
                + "\n"
                +"3,6"
                + "\n"
                + "1"
                + "\n"
                +"2";

        System.setIn(new ByteArrayInputStream(input.getBytes()));//mock System.in
        Weather.WeatherBuilder weather = null;

        Weather.WeatherBuilder actual = WeatherDriver.userInput(weather, "src/test/java/resources/TDD.txt");

        Assertions.assertTrue((BooleanSupplier) actual);

    }

    @Test
    public void parseJson() {
        String JsonTest = "{\"winds\":[{\"id\":1,\"speed\":\"15.5\",\"date\":\"01.01.2020 23:00:00\",\"station\":\"firstStation\",\"direction\":\"N\",\"weather\":\"sunny\",\"temp\":-5.0,\"knots\":8.36938,\"beaufort\":2.673876}]}";
        List<Weather> weatherList = new ArrayList<>();
        boolean convert = WeatherDriver.parseJson(JsonTest);
        Assertions.assertTrue(convert);
    }


    @Test
    public void insertFile() throws IOException {
        String fileWriteStr =  "{\"winds\":[{\"id\":1,\"speed\":\"15.5\",\"date\":\"01.01.2020 23:00:00\",\"station\":\"firstStation\",\"direction\":\"N\",\"weather\":\"sunny\",\"temp\":-5.0,\"knots\":8.36938,\"beaufort\":2.673876}]}";
        String path = "src/test/java/resources/TDD.txt";


        FileWriter file = new FileWriter("src/test/java/resources/TDD.txt", false);

    }

/*
 public static void addToFile() {
        Gson gson = new Gson();
        try {
            //addtoFile
            FileWriter file = new FileWriter("src/main/java/resources/wind.json", false);
            String output = gson.toJson(weatherList, new TypeToken<List<Weather>>() {
            }.getType());

            String start = "{\"winds\":";
            String end = "}";
            String toWrite= start + output + end;
            int i = 1;
            file.write(toWrite);

            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 */


    @Test
    void main() {
    }

    @Test
    void userInput() {
    }

    @Test
    void readFile() {
    }

    @Test
    void addToFile() {
    }
}

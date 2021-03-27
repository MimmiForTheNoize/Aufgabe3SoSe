package org.lecture;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Driver class lets the user select if she/he wants to start the program
 * It explains the process then lets the user input mandatory and optional values
 * The user may input the values in the console or load a file with them in it.
 * At the end it lets the user decide if he wants to see the output.
 * And it lets the user decide if she/he wants to save it in the file or not.
 * @name Lisa Allacher
 * @version 2.0
 */


public class WeatherDriver {

    private static final List<Weather> weatherList = new ArrayList<>();

    public static void main(String[] args) {
        String path = "src/main/java/resources/wind.json";
        String content = readFile(path);
        parseJson(content);
        Weather.WeatherBuilder wb = null;

        /**
         * -------------------------Start-------------------------------------------------
         *
         */
        //Function for User Input
        Weather.WeatherBuilder test = userInput(wb, path);
        System.exit(0);

        /**
         * -------------------------End-------------------------------------------------
         *
         */


    }

    /**
     *
     * @param wb = weather Object
     * @param path = path to file
     * @return wb Object
     */
    public static Weather.WeatherBuilder userInput(Weather.WeatherBuilder wb, String path) {
        System.out.println("This is a program where you can add your wind speeds into a data base");
        System.out.println("""
                -----------------------------------------------------------
                Continue: Enter 1
                Exit: Enter 2
                -----------------------------------------------------------
                """);
        Scanner sc = new Scanner(System.in);
        String startAll = sc.nextLine();

        if (startAll.equals("1")) {

            System.out.println("""
                    -----------------------------------------------------------
                    You can add multiple wind entries in one sitting.
                    Just enter a number of how many you would like to add:
                                        
                    You must enter the following values:
                    Speed and Station.
                                        
                    You may also enter the Direction, the weather and the temperature.
                    -----------------------------------------------------------
                    """);


            System.out.println("*****************************************");
            System.out.println("""
                    Would you like to add data from file or via Console?
                    Enter 1 for Console
                    Enter 2 for File
                    """);
            String checkWay = sc.nextLine();
            if (checkWay.equals("2")) {
                System.out.println("Please enter path: ");
                String newPath = sc.nextLine();
                String newContent = readFile(newPath);
                parseJson(newContent);
            } else if (checkWay.equals("1")) {
                System.out.println("How many entries would you like to add?");
                String loop = sc.nextLine();
                int loops = Integer.parseInt(loop);


                for (int i = 0; i < loops; i++) {
                    wb = new Weather.WeatherBuilder();
                    System.out.println("Entry: " + i);
                    System.out.println("Please enter speed: ");
                    wb.withSpeed(sc.nextLine());


                    System.out.println("Please enter station: ");
                    wb.withStation(sc.nextLine());
                    System.out.println("Would you like to enter a direction? Enter yes or no");
                    String check = sc.nextLine();
                    if (check.equals("yes")) {
                        System.out.println("Please enter Direction");
                        wb.withDirection(sc.nextLine());
                    }

                    System.out.println("Would you like to enter a weather? Enter yes or no");
                    check = sc.nextLine();
                    if (check.equals("yes")) {
                        System.out.println("Please enter weather");
                        wb.withWeather(sc.nextLine());
                    }
                    System.out.println("Would you like to enter a temperature? Enter yes or no");
                    check = sc.nextLine();
                    if (check.equals("yes")) {
                        System.out.println("Please enter temperature");
                        String temp = sc.nextLine();
                        if (temp.contains(",")) {
                            temp = temp.replace(",", ".");
                        }
                        wb.withTemp(Float.parseFloat(temp));
                    }
                    Weather weather = wb.build();
                    weatherList.add(weather);
                }
            }
        }

        System.out.println("""
                -----------------------------------------------------------
                Would you like to check entries?
                Enter 1 for yes and 2 for no
                -----------------------------------------------------------
                """);

        String showEntries = sc.nextLine();
        if (showEntries.equals("1")) {
            for (Weather data : weatherList) {
                System.out.println(data.toString());
            }
            System.out.println("""
                    -----------------------------------------------------------
                    Would you like to write entries back to file?
                    Enter 1 for yes and 2 for no
                    -----------------------------------------------------------
                            """);
            String ansewrWriteToFile = sc.nextLine();
            if (ansewrWriteToFile.equals("1")) {
                String strPath = path;
                addToFile(strPath);
            }
        }
        //returns the new Object wb
        return wb;
    }

    /**
     *
     * @param path = path to file
     * @return returns the Content of file as a String
     */
    public static String readFile(String path) {
        Path convertedPath = Paths.get(path);
        String content = null;
        try {
            //read json file
            content = Files.readString(convertedPath);
        } catch (IOException e) {
            System.out.println("Error! Try again.");
        }
        System.out.println(content);
        return content;
    }

    /**
     *
     * @param content String with content from file
     * @return returns true or false if the size of the array has changed
     */
    public static boolean parseJson(String content) {
        //create JSON object
        Gson gson = new Gson();
        JsonObject jsob = gson.fromJson(content, JsonObject.class);
        JsonArray jsonArray = gson.fromJson(jsob.get("winds"), JsonArray.class);
        int outsideIterator = 0;
        //weatherlist = Array with entries
        int sizeOutside = weatherList.size();

        //transform userInput into weather Object
        for (int i = 0; i < jsonArray.size(); i++) {
            int size = weatherList.size();
            JsonObject windJSON = (JsonObject) jsonArray.get(i);
            String windObjectJson = windJSON.toString();

            Weather weather = gson.fromJson(windObjectJson, Weather.WeatherBuilder.class).withId(size + 1).build();

            weatherList.add(weather);
            outsideIterator++;
        }
        if(weatherList.size() == sizeOutside+outsideIterator) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param strPath = path
     * reads entries into file
     */
    public static void addToFile(String strPath) {
        Gson gson = new Gson();
        try {
            //addtoFile (overwriting)
            FileWriter file = new FileWriter(strPath, false);
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


}

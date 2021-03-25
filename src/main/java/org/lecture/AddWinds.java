package org.lecture;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.google.gson.Gson;

import static java.lang.Float.parseFloat;

public class AddWinds {

    private static JSONObject parseJson() {

        JSONObject allInObj = null;
        try {
            //create JSON object
            allInObj = (JSONObject) new JSONParser().parse(new FileReader("src/main/java/resources/wind.json"));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return allInObj;
    }

    /**
     * -------------------------User Input start---------------------------------------------
     *
     * @param len
     * @param index
     */
    public static ArrayList<Input> userInput(int len, int index) {
        ArrayList<Input> allEntries = new ArrayList<Input>();
        //User Input
        Input newEntry = new Input();

        System.out.println("Please enter your new entry:");
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter the speed (mandatory)");
        String speed = userIn.next();
        float parsedSpeed = 0;
        String reg = "^\\d+\\.\\d+|\\d+\\,\\d|\\d+$";
        if (speed.matches(reg)) {
            if (speed.contains(",")) {
                speed = speed.replace(",", ".");
                parsedSpeed = parseFloat(speed);
            } else {
                parsedSpeed = parseFloat(speed);
            }
        } else {
            System.out.println("uhoh, something went wrong please try again");
            System.exit(1);
        }

        newEntry.setUserSpeed(parsedSpeed);

        LocalDateTime myTime = LocalDateTime.now();
        //from w3school on formatting Date
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDate = myTime.format(myFormatObj);
        newEntry.setUserDate(formattedDate);

        System.out.println("Please enter the name of the station (mandatory):");
        String station = userIn.next();
        newEntry.setUserStation(station);

        //id aus JSONObj inkrementiert durch die length
        int idLen = len + index;
        if (idLen > 0) {
            idLen++;
            newEntry.setUserId(idLen);
        } else {
            System.out.println("uhoh, something went wrong please try again");
            System.exit(2);
        }

        Direction direction = Direction.NaN;
        System.out.println("Would you like to enter a direction? Enter yes or no");
        String check = userIn.next();
        if (check.equals("yes")) {
            System.out.println("Please enter Direction");
            direction = Direction.valueOf(userIn.next());
        } else {
            direction = Direction.NaN;
        }
        newEntry.setUserDirection(direction);

        System.out.println("Would you like to enter a weather? Enter yes or no");
        check = userIn.next();
        String weather = "";
        if (check.equals("yes")) {
            System.out.println("Please enter weather");
            weather = userIn.next();
        } else {
            weather = "cloudy";
        }
        newEntry.setUserWeather(weather);

        System.out.println("Would you like to enter a temperature? Enter yes or no");
        check = userIn.next();
        float temp = 0;
        if (check.equals("yes")) {
            System.out.println("Please enter temperature");
            temp = userIn.nextFloat();
        } else {
            temp = 0;
        }
        //userIn.close();

        newEntry.setUserTemp(temp);

        //Store Input in Arraylist
        allEntries.add(newEntry);

        /**
         * -------------------------User Input End---------------------------------------------
         */

        return allEntries;
    }

    /**
     * -------------------------Output Objects from existing File Start--------------------
     *
     * @param entries
     * @param len
     */

    public static Collection<JSONObject> outputAll(JSONObject entries, int len) {
        String sampleEntry = "";
        Collection<JSONObject> items = new ArrayList<JSONObject>();

        //iteratre through existing entries
        //turn into objects
        //give len and entries with him and return collection
        for (int i = 1; i <= len; i++) {
            sampleEntry = "wind-" + i;
            JSONObject windJSON = (JSONObject) entries.get(sampleEntry);
            items.add(windJSON);
            String newJSONstr = windJSON.toJSONString();
            Gson gson = new Gson();
            Winds windig = gson.fromJson(newJSONstr, Winds.class);
            System.out.println("--------------------------");
            System.out.println(sampleEntry);

            Optional.SecBuilder option = new Optional.SecBuilder().direction(windig.getDirection())
                    .weather(windig.getWeather())
                    .temp(windig.getTemp());

            Mandatory.Builder mandatory = new Mandatory.Builder().id(windig.getId())
                    .speed(windig.getSpeed())
                    .date(windig.getDate())
                    .station(windig.getStation());
            //print it
            System.out.println("ID: " + mandatory.getId() + " | km/h:" + mandatory.getSpeed() + " | time: " + mandatory.getDate());
            System.out.println("Station: " + mandatory.getStation() + " | direction: " + option.getDirection() + " | weather: " + option.getWeather() + " | temperature: " + option.getTemp());

            //hier die knoten und die beaufort ausgeben
/*

            try {

                String kmh = mandatory.getSpeed();
                float knot;
                float kmhFl = Float.parseFloat(kmh);
                knot = (int) (kmhFl * 0.53996);
                System.out.println("Die Knoten sind: " + knot);
                int beau = (int) ((knot + 5) / 5);
                System.out.println("Beaufort Skala: " + beau);

            } catch (Exception e) {
                e.printStackTrace();
            }
 */


        }
        return items;
    }
    /**
     * -------------------------Output Objects from existing File End---------------------------------------------
     */

    /**
     * -------------------------Output User Input Start---------------------------------------------
     *
     * @param entries
     * @param items
     * @param len
     */

    public static void outPutUser(ArrayList<Input> entries, Collection<JSONObject> items, int len) {
        //Useroutput
        String name = "wind-";
        ArrayList<Input> allEntries = entries;

        for (int i = 0; i < allEntries.size(); i++) {
            System.out.println("--------------------------");
            System.out.println(name + len);
            //Print userInput
            System.out.println("ID: " + allEntries.get(i).getUserId() + " | km/h:" + allEntries.get(i).getUserSpeed() + " | time: " + allEntries.get(i).getUserDate());
            System.out.println("Station: " + allEntries.get(i).getUserStation() + " | direction: " + allEntries.get(i).getUserDirection() + " | weather: " + allEntries.get(i).getUserWeather() + " | temperature: " + allEntries.get(i).getUserTemp());


            try {
                Gson inputGson = new Gson();
                String inputJson = inputGson.toJson(allEntries.get(i));
                JSONParser parser = new JSONParser();
                JSONObject entryJson = (JSONObject) parser.parse(inputJson);
                items.add(entryJson);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        Gson allObj = new Gson();
        String allObjStr = allObj.toJson(items);
        try {
            //addtoFile
            Iterator<JSONObject> iterator = items.iterator();
            FileWriter file = new FileWriter("src/main/java/resources/wind.json", false);
            String start = "{";
            String end = "}";
            int i = 1;
            file.write(start);
            while (iterator.hasNext()) {
                file.write("\"wind-" + i + "\"" + ":");
                iterator.next().writeJSONString(file);

                if (i == items.size()) {
                    file.write(end);
                } else {
                    file.write(",");
                }
                i++;
            }

            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * -------------------------Output User Input Start---------------------------------------------
     */


    public static void main(String[] args) throws ParseException {

        //get the json
        //we have a json with all the winds called entries
        JSONObject entries = parseJson();
        //Size of json object for iteration
        //auch für ID dann relevant
        int len = entries.size(); //needs to be returned
        Collection<JSONObject> items = new ArrayList<JSONObject>();
        ArrayList<Input> allEntries = new ArrayList<Input>();

        /**
         * -------------------------Start-------------------------------------------------
         *
         */

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

            //Array of User Input

            System.out.println("*****************************************");
            System.out.println("How many entries would you like to add?");
           String loop = sc.nextLine();
            int loops = Integer.parseInt(loop);

            for (int i = 0; i < loops; i++) {
                System.out.println("Entry: " + i);
                allEntries = userInput(len, i);
            }
        }

        System.out.println("""
                -----------------------------------------------------------
                Would you like to check entries?
                Enter 1 for yes and 2 for no
                -----------------------------------------------------------
                """);

        try {
            int showEntries=0;
            showEntries = sc.nextInt();
            if (showEntries==1) {
                items = outputAll(entries, len);

                System.out.println(items);
                outPutUser(allEntries, items, len);
            } else {
                System.exit(0);
            }

        }   catch (InputMismatchException ex) {
            ex.printStackTrace();

        }

        /**
         * -------------------------End-------------------------------------------------
         *
         */
    }
}

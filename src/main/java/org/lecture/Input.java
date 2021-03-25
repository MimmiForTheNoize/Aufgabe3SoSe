package org.lecture;

public class Input {
    /*
    der user muss auf jeden Fall folgendes eingeben:
    speed
    date
    time
    station

    folgendes ist optional:
    direction
    weather - default cloudy
    temp

     */

    private float userSpeed;
    private String userDate;
    private String userStation;
    private Direction userDirection;//maybe enum???
    private String userWeather;
    private float userTemp;
    private int userId;

    public float getUserSpeed() {
        return userSpeed;
    }

    public void setUserSpeed(float userSpeed) {
        this.userSpeed = userSpeed;
    }

    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public String getUserStation() {
        return userStation;
    }

    public void setUserStation(String userStation) {
        this.userStation = userStation;
    }

    public Direction getUserDirection() {
        return userDirection;
    }

    public void setUserDirection(Direction userDirection) {
        this.userDirection = userDirection;
    }

    public String getUserWeather() {
        return userWeather;
    }

    public void setUserWeather(String userWeather) {
        this.userWeather = userWeather;
    }

    public float getUserTemp() {
        return userTemp;
    }

    public void setUserTemp(float userTemp) {
        this.userTemp = userTemp;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }




    //hier kommen die methoden für Knots und für Beaufort hin


}

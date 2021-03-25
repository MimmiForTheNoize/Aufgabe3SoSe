package org.lecture;

public class Optional {
    private final Direction direction;// enum
    private final String weather;
    private final float temp;

    private  Optional(SecBuilder secBuilder) {
        this.direction = secBuilder.direction;
        this.weather = secBuilder.weather;
        this.temp = secBuilder.temp;

    }

    public static class SecBuilder {
        private Direction direction;
        private String weather;
        private float temp;

        public SecBuilder direction(Direction id) {
            this.direction = direction;
            return this;
        }

        public SecBuilder weather(String weather) {
            this.weather = weather;
            return this;
        }

        public SecBuilder temp(float temp) {
            this.temp = temp;
            return this;
        }

        //build and return a optional object
        public Optional build() {
            return  new Optional(this);
        }


        //getter here


        public Direction getDirection() {
            return direction;
        }

        public String getWeather() {
            return weather;
        }

        public float getTemp() {
            return temp;
        }
    }

}

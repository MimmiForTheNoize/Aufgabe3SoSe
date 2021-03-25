package org.lecture;

public class Mandatory {

    private final int id;
    private final String speed;
    private final String date;
    private final String station;
    private final Optional optional;
    private int knot;

    private Mandatory(Builder builder) {
        this.id = builder.id;
        this.speed = builder.speed;
        this.date = builder.date;
        this.station = builder.station;
        this.optional = builder.optional;

    }



    public String getSpeed() {
        return speed;
    }

    public String getDate() {
        return date;
    }

    public String getStation() {
        return station;
    }

    public Optional getOptional() {
        return optional;
    }



    public static class Builder {
        private int id;
        private String speed;
        private String date;
        private String station;
        private Optional optional;

        public Builder id(final int id) {
            this.id = id;
            return this;
        }
        public Builder speed(final String speed) {
            this.speed = speed;
            return this;
        }

        public Builder date(final String date) {
            this.date = date;
            return this;
        }

        public Builder station(String station) {
            this.station = station;
            return this;
        }

        public Builder optional(final Optional optional) {
            this.optional = optional;
            return this;
        }

        //build and return a mandatory object
        //public new Mandatory(this);
        public Mandatory build() {
            return new Mandatory(this);
        }

        public int getId() {
            this.id = id;
            return id;
        }

        public String getSpeed() {
            this.speed = speed;
            return speed;
        }

        public String getDate() {
            this.date = date;
            return date;
        }

        public String getStation() {
            this.station = station;
            return station;
        }
    }


    //Knoten (km/h*0,53996) und Beaufort ((Knoten + 5)/5) unter Beachtung der Skala
    public static int knots(String speed) {
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

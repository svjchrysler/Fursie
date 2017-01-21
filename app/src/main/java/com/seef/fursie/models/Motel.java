package com.seef.fursie.models;

import java.util.List;

/**
 * Created by jhonsalguero on 1/20/17.
 */

public class Motel {
    private String Name;
    private float Latitude;
    private float Longitude;
    private float Score;

    public Motel() {}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }
}

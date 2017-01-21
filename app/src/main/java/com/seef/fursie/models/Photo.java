package com.seef.fursie.models;

/**
 * Created by jhonsalguero on 1/20/17.
 */

public class Photo {
    private Motel Motel;
    private String Name;

    public Photo() { }

    public com.seef.fursie.models.Motel getMotel() {
        return Motel;
    }

    public void setMotel(com.seef.fursie.models.Motel motel) {
        Motel = motel;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

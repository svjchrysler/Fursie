package com.seef.fursie.models;

import java.util.Date;

/**
 * Created by jhonsalguero on 1/20/17.
 */

public class Commentary {
    private String Commentary;
    private Motel Motel;
    private User User;
    private Date Date;

    public String getCommentary() {
        return Commentary;
    }

    public void setCommentary(String commentary) {
        Commentary = commentary;
    }

    public com.seef.fursie.models.Motel getMotel() {
        return Motel;
    }

    public void setMotel(com.seef.fursie.models.Motel motel) {
        Motel = motel;
    }

    public com.seef.fursie.models.User getUser() {
        return User;
    }

    public void setUser(com.seef.fursie.models.User user) {
        User = user;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}

package com.seef.fursie.models;

/**
 * Created by jhonsalguero on 1/20/17.
 */

public class User {
    private int Id;
    private String Name;
    private String Email;
    private String Username;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}

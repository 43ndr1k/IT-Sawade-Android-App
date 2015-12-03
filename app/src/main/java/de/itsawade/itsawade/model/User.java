package de.itsawade.itsawade.model;

/**
 * Created by hendrik on 01.12.15.
 */
public class User {

    int id;
    String username, email,first_name, last_name;

    public User(String last_name, int id, String username, String email, String first_name) {
        this.last_name = last_name;
        this.id = id;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}

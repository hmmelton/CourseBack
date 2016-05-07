package com.hmmelton.courseback.models;

/**
 * Created by harrisonmelton on 5/7/16.
 * Each instance of this class holds information of one user.
 */
public class User {

    private String id; // user ID
    private String name; // user's full name
    private String email; // user's email address

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * This method returns the user's ID.
     * @return user's id
     */
    public String getId() {
        return id;
    }

    /**
     * This method returns the user's name.
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the user's email address.
     * @return
     */
    public String getEmail() {
        return email;
    }
}

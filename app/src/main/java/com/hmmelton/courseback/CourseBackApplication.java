package com.hmmelton.courseback;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by harrison on 7/12/15.
 */
public class CourseBackApplication extends Application {

    private static CourseBackApplication instance; // global context
    private static String fbId; // user's Facebook ID number
    private static Firebase firebase; // instance of database

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this); // initialize Firebase
        // connect to Firebase
        firebase = new Firebase("https://courseback.firebaseio.com/");

        instance = this;
    }

    /**
     * This method returns the application's global context.
     * @return global context
     */
    public static CourseBackApplication getInstance() {
        return instance;
    }

    /**
     * This method returns the application's global database instance.
     * @return instance of database
     */
    public static Firebase getDatabase() {
        return firebase;
    }

    /**
     * This method returns the user's Facebook ID number.
     * @return Facebook ID number
     */
    public static String getFacebookId() {
        return fbId;
    }

    /**
     * This method sets the user's Facebook ID number for future reference.
     * @param id user's Facebook ID number
     */
    public static void setFacebookId(String id) {
        fbId = id;
    }
}

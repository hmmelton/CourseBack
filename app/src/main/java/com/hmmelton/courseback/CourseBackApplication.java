package com.hmmelton.courseback;

import android.app.Application;

import com.firebase.client.Firebase;
import com.hmmelton.courseback.models.User;

/**
 * Created by harrison on 7/12/15.
 * This Application file is used to store information that can be accessed by all of the app's
 * files.
 */
public class CourseBackApplication extends Application {

    private static CourseBackApplication instance; // global context
    private static String fbId; // user's Facebook ID number
    private static Firebase firebase; // instance of database
    private static User user;

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
     * This method returns the global User object, which represents the current user.
     * @return currently instantiated User object
     */
    public static User getUser() {
        return user;
    }

    /**
     * This method sets the application's global User object.
     * @param newUser User object being set
     */
    public static void setUser(User newUser) {
        user = newUser;
    }
}

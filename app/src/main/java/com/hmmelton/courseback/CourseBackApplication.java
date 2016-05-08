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

    public static User getUser() {
        return user;
    }

    public static void setUser(String id, String name, String email) {
        user = new User(id, name, email);
    }

    /**
     * This method is used to log the user out of the application.
     */
    public static void clearUser() {
        user = null;
    }
}

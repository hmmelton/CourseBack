package com.hmmelton.courseback;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by harrison on 7/12/15.
 */
public class CourseBackApplication extends Application {

    private static CourseBackApplication instance;
    private static String fbId;
    private static Firebase firebase;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this); // initialize Firebase
        // connect to Firebase
        firebase = new Firebase("https://courseback.firebaseio.com/");

        instance = this;
    }

    public static CourseBackApplication getInstance() {
        return instance;
    }

    public static String getFacebookId() {
        return fbId;
    }

    public static void setFacebookId(String id) {
        fbId = id;
    }
}

package com.hmmelton.courseback.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.hmmelton.courseback.CourseBackApplication;
import com.hmmelton.courseback.SignInActivity;
import com.hmmelton.courseback.models.User;

/**
 * Created by harrisonmelton on 9/14/15.
 * This class is used to check the user's login credentials.
 */
public class Authentication {

    // Used to store user login information after app has been exited
    private static final String PREFS_FILE = "UserPreferences";
    private static final String USER_ID = "com.courseback.user_id";
    private static final String USER_NAME = "com.courseback.user_name";
    private static final String USER_EMAIL = "com.courseback.user_email";

    private static final Context CONTEXT =
            CourseBackApplication.getInstance().getApplicationContext();

    private static final String TAG = "Authentication.java";

    /**
     * This method checks whether or not the user is signed in.  If the user is not signed in, s/he
     * will be directed to the sign in page.
     */
    public static void isUserSignedIn(Activity activity) {
        // if user is not signed in, redirect to login page
        if (!areCredentialsStored()) {
            Intent intent = new Intent(activity, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
            activity.finish(); // prevents user from skipping login by hitting "back" button
        }

    }

    /**
     * This method logs the user out of the system.
     */
    public static void unauthorize() {
        CourseBackApplication.setUser(null);
        setUser(null, null, null);
    }

    /**
     * This method is used to save user login information to local storage.
     * @param id user's unique ID number
     * @param name user's name
     * @param email user's email address
     */
    public static void setUser(String id, String name, String email) {
        // Instantiate global User object, so information does not have to be drawn from local
        // storage each time the app needs user info.
        CourseBackApplication.setUser(new User(id, name, email));

        SharedPreferences preferences = CONTEXT.getSharedPreferences(PREFS_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();

        // Set all the user's login info
        editor.putString(USER_ID, id);
        editor.putString(USER_NAME, name);
        editor.putString(USER_EMAIL, email);

        // Commit information to local storage
        editor.commit();
    }

    private static boolean areCredentialsStored() {
        SharedPreferences preferences = CONTEXT.getSharedPreferences(PREFS_FILE, 0);

        String id = preferences.getString(USER_ID, null);
        String name = preferences.getString(USER_NAME, null);
        String email = preferences.getString(USER_EMAIL, null);

        if (id == null || name == null || email == null) {
            // User has missing or incomplete credentials
            return false;
        }

        // User has already logged in.
        return true;
    }

}

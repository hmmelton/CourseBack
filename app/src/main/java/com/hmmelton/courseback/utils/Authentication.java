package com.hmmelton.courseback.utils;

import android.app.Activity;
import android.content.Intent;

import com.hmmelton.courseback.CourseBackApplication;
import com.hmmelton.courseback.SignInActivity;
import com.hmmelton.courseback.models.User;

/**
 * Created by harrisonmelton on 9/14/15.
 * This class is used to check the user's login credentials.
 */
public class Authentication {

    private static final String TAG = "Authentication.java";

    /**
     * This method checks whether or not the user is signed in.  If the user is not signed in, s/he
     * will be directed to the sign in page.
     */
    public static void isUserSignedIn(Activity activity) {
        User user = CourseBackApplication.getUser();
        // if user is not signed in, redirect to login page
        if (user == null) {
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
        CourseBackApplication.clearUser();
    }
}

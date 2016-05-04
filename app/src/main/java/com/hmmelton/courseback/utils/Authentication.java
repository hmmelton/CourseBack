package com.hmmelton.courseback.utils;

import android.app.Activity;
import android.content.Intent;

import com.hmmelton.courseback.SignInActivity;
import com.parse.ParseUser;

/**
 * Created by harrisonmelton on 9/14/15.
 */
public class Authentication {

    /**
     * This method checks whether or not the user is signed in.  If the user is not signed in, s/he
     * will be directed to the sign in page.
     */
    public static void isUserSignedIn(Activity activity) {
        if (ParseUser.getCurrentUser() == null) {
            Intent intent = new Intent(activity, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            activity.startActivity(intent);
            activity.finish(); // removes activity from backstack
            activity.overridePendingTransition(0,0); // used to prevent animation between activities
        }
    }
}

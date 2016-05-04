package com.hmmelton.courseback;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by harrison on 7/12/15.
 */
public class TextRackApplication extends Application {

    private static TextRackApplication instance;
    private static String fbId;

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
        ParseFacebookUtils.initialize(this);

        instance = this;
    }

    public static TextRackApplication getInstance() {
        return instance;
    }

    public static String getFacebookId() {
        return fbId;
    }

    public static void setFacebookId(String id) {
        fbId = id;
    }
}

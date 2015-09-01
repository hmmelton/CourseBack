package com.hmmelton.textrack;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hmmelton.textrack.utils.SignInTitleAnimation;
import com.parse.ParseFacebookUtils;

import java.util.Arrays;


public class SignInActivity extends AppCompatActivity {

    private final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        FacebookSdk.sdkInitialize(this);
        CallbackManager callbackManager = CallbackManager.Factory.create();

        fadeInTitle();

        // FB sign in button
        LoginButton signInBtn = (LoginButton) findViewById(R.id.facebook_login_button);
        setImageSize(signInBtn);
        signInBtn.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));
        signInBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, AccessToken.getCurrentAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.e(TAG, "onError");
            }
        });
        /*
        ParseFacebookUtils.logInWithReadPermissionsInBackground(SignInActivity.this,
                Arrays.asList("public_profile", "user_friends", "email"),
                (user, e) -> {

                    if (user == null) {
                        Toast.makeText(SignInActivity.this,
                                getString(R.string.null_user), Toast.LENGTH_SHORT).show();
                    } else {
                        // navigate to MainActivity
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method fades in the app's title.
     */
    private void fadeInTitle() {
        TextView title = (TextView) findViewById(R.id.login_title);
        SignInTitleAnimation fadeIn = new SignInTitleAnimation(title);

        fadeIn.setDuration(3000);
        title.startAnimation(fadeIn);
    }

    /**
     * This method scales the image on the Facebook login button.
     * @param button button whose image is scaled
     */
    private void setImageSize(LoginButton button) {
        float fbIconScale = 1.45F;
        Drawable drawable = getResources().getDrawable(com.facebook.R.drawable.com_facebook_button_icon);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*fbIconScale),
                (int)(drawable.getIntrinsicHeight()*fbIconScale));
        button.setCompoundDrawables(drawable, null, null, null);
    }
}

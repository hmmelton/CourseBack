package com.hmmelton.textrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.hmmelton.textrack.utils.SignInTitleAnimation;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONException;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = "SignInActivity";

    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        FacebookSdk.sdkInitialize(this);

        fadeInTitle();

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        ((jsonObject, graphResponse) ->
                            ParseFacebookUtils.logInInBackground(loginResult.getAccessToken(), ((user, e) -> {
                                try {
                                    TextRackApplication.setFacebookId(jsonObject.getString("id"));
                                    user.put("name", jsonObject.getString("name"));
                                    user.setEmail(jsonObject.getString("email"));
                                    logIn();
                                } catch (JSONException e1) {
                                    Toast.makeText(SignInActivity.this,
                                            getString(R.string.sign_in_error), Toast.LENGTH_SHORT)
                                            .show();
                                    // Although there was an error, the user has already signed in.
                                    // S/he therefore must be signed out to avoid authentication
                                    // errors.
                                    ParseUser.logOut();
                                }
                            }))
                        )
                );

                // specify which fields are to be accessed
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.e(TAG, "error", e);
            }
        });

        // FB sign in button
        Button signInBtn = (Button) findViewById(R.id.facebook_login_button);
        signInBtn.setOnClickListener(v ->
                    LoginManager.getInstance().logInWithReadPermissions(this,
                            Arrays.asList("public_profile", "user_friends", "email"))
        );
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
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
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

        fadeIn.setDuration(2000);
        title.startAnimation(fadeIn);
    }

    /**
     * This method navigates the user to the MainActivity and ends the current activity.
     */
    private void logIn() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}

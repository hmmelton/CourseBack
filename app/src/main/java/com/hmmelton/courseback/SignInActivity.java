package com.hmmelton.courseback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firebase.client.Firebase;
import com.hmmelton.courseback.models.User;
import com.hmmelton.courseback.utils.SignInTitleAnimation;

import org.json.JSONException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = "SignInActivity";
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        FacebookSdk.sdkInitialize(this);

        // Set OnClickListener for Facebook button to get user's Facebook information
        findViewById(R.id.facebook_login_button).setOnClickListener(v ->
                    LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this,
                            Arrays.asList("public_profile", "email")));

        initFacebookLogin();
        fadeInTitle();

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
        // required by Facebook SDK
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

    private void initFacebookLogin() {
        mCallbackManager = CallbackManager.Factory.create();
        // Register callback for later use
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // ID of the user currently trying to log in
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                (jsonObject, response) -> {
                                    try {
                                        String id = jsonObject.getString("id");
                                        String name = jsonObject.getString("name");
                                        String email = jsonObject.getString("email");
                                        // Sets global user
                                        CourseBackApplication.setUser(id, name, email);
                                        logIn();
                                    } catch (JSONException e) {
                                        Log.e(TAG, e.toString());
                                        e.printStackTrace();
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
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
        User user = CourseBackApplication.getUser(); // current user
        // reference to current user in database
        Firebase userRef = CourseBackApplication.getDatabase().child("user").child(user.getId());

        // set values for user
        Map<String, String> payload = new HashMap<>();
        payload.put("fullName", user.getName());
        payload.put("email", user.getEmail());

        // set user and create callback
        userRef.setValue(payload, (firebaseError, firebase) -> {
            if (firebaseError ==  null) {
                // login successful!
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            } else {
                // let user know login did not work
                Toast.makeText(this, getResources().getString(R.string.sign_in_error),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}

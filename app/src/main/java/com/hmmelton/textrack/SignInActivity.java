package com.hmmelton.textrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hmmelton.textrack.utils.SignInTitleAnimation;
import com.parse.ParseFacebookUtils;

import java.util.Arrays;


public class SignInActivity extends AppCompatActivity {

    private final int ANIM_DURATION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        fadeInTitle();

        // FB sign in button
        Button signInBtn = (Button) findViewById(R.id.facebook_login_button);
        signInBtn.setOnClickListener(v -> {

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
                        });
        });
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
}

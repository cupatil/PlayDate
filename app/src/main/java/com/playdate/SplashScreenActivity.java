package com.playdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.toolbox.Volley;
import com.playdate.LoginModule.LoginActivity;
import com.playdate.MainModule.MainActivity;
import com.playdate.Utils.AppConstant;
import com.playdate.Utils.PreferencesManager;
import com.playdate.Utils.ProjectUtilities;

public class SplashScreenActivity extends AppCompatActivity {
    PreferencesManager mPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        //Initialize instance of preference manager
        PreferencesManager.initializeInstance(getApplicationContext());
        ProjectUtilities.mVolleyQueue = Volley.newRequestQueue(getApplicationContext());

        mPreferencesManager = PreferencesManager.getInstance();
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {

                } finally {

                    if (mPreferencesManager.getBooleanValue(AppConstant.SHARED_PREFERENCE_IS_LOGIN)) {
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        timer.start();
    }
}

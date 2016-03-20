package br.com.vanderson.stackoverflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import br.com.vanderson.stackoverflow.app.ActivityApp;

public class SplashScreenActivity extends ActivityApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        hideToolBar();


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                finish();

                Intent intent = new Intent();
                intent.setClass(SplashScreenActivity.this, ListStackActivity.class);
                startActivity(intent);
            }
        }, 3000);


    }
}

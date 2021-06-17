package com.itstimetosnuff.jellyfishsurvival;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity {

    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timerTask.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
            }
        };
        new Timer().schedule(timerTask, 3000);
    }
}
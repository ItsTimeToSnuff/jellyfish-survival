package com.itstimetosnuff.jellyfishsurvival;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        pref = getSharedPreferences("jellyprefs", Context.MODE_PRIVATE);
        TextView score = findViewById(R.id.record_menu_score_value);
        score.setText(String.format(getString(R.string.record_menu_score_value), pref.getFloat("highScore", 0)));
        TextView bubbles = findViewById(R.id.record_menu_bubble_value);
        bubbles.setText(String.valueOf(pref.getInt("bubblesCounter", 0)));
        TextView game = findViewById(R.id.record_menu_game_value);
        game.setText(String.valueOf(pref.getInt("timesPlayed", 0)));
        findViewById(R.id.record_menu_clear_btn).setOnClickListener(v -> {
            clearStatistics();
            score.setText(String.format(getString(R.string.record_menu_score_value), pref.getFloat("highScore", 0)));
            bubbles.setText(String.valueOf(pref.getInt("bubblesCounter", 0)));
            game.setText(String.valueOf(pref.getInt("timesPlayed", 0)));
        });
        findViewById(R.id.record_menu_back_btn).setOnClickListener(v -> finish());
    }

    public void clearStatistics() {
        pref.edit()
                .putInt("bubbles", 0)
                .putFloat("highScore", 0)
                .putInt("timesPlayed", 0)
                .putInt("brightness", 400)
                .putInt("extralives", 0)
                .putInt("brightCost", 0)
                .putInt("livesCost", 25)
                .putBoolean("endlessMode", false)
                .putInt("headStart", 0)
                .putInt("lasers", 0)
                .putInt("headStartCost", 50)
                .putInt("lasersCost", 350)
                .putInt("bubblesCounter", 0)
                .apply();
    }
}

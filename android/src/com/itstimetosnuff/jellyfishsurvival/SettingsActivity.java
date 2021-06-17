package com.itstimetosnuff.jellyfishsurvival;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences pref = getSharedPreferences("jellyprefs", Context.MODE_PRIVATE);
        ImageButton music = findViewById(R.id.settings_menu_music_btn);
        boolean isMusic = pref.getBoolean("musicOn", true);
        if (isMusic) {
            music.setImageResource(R.drawable.btn_on);
        } else {
            music.setImageResource(R.drawable.btn_off);
        }
        music.setOnClickListener(v -> {
            boolean isMusicOn = pref.getBoolean("musicOn", false);
            if (isMusicOn) {
                pref.edit().putBoolean("musicOn", false).apply();
                music.setImageResource(R.drawable.btn_off);
            } else {
                pref.edit().putBoolean("musicOn", true).apply();
                music.setImageResource(R.drawable.btn_on);
            }
        });
        ImageButton sound = findViewById(R.id.settings_menu_sound_btn);
        boolean isSound = pref.getBoolean("soundOn", true);
        if (isSound) {
            sound.setImageResource(R.drawable.btn_on);
        } else {
            sound.setImageResource(R.drawable.btn_off);
        }
        sound.setOnClickListener(v -> {
            boolean isSoundOn = pref.getBoolean("soundOn", false);
            if (isSoundOn) {
                pref.edit().putBoolean("soundOn", false).apply();
                sound.setImageResource(R.drawable.btn_off);
            } else {
                pref.edit().putBoolean("soundOn", true).apply();
                sound.setImageResource(R.drawable.btn_on);
            }
        });
        findViewById(R.id.settings_menu_back_btn).setOnClickListener(v -> finish());
    }
}

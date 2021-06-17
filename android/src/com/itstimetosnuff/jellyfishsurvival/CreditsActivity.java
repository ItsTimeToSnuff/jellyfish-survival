package com.itstimetosnuff.jellyfishsurvival;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        findViewById(R.id.credits_menu_back_btn).setOnClickListener(v -> finish());
    }
}

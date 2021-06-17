package com.itstimetosnuff.jellyfishsurvival;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        TextView appName = findViewById(R.id.credits_menu_name);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_app_name_text);
        appName.setAnimation(animation);
        appName.startAnimation(animation);
        findViewById(R.id.button_play).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AndroidLauncher.class)));
        findViewById(R.id.button_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        findViewById(R.id.button_record).setOnClickListener(v -> startActivity(new Intent(this, RecordActivity.class)));
        findViewById(R.id.button_credits).setOnClickListener(v -> startActivity(new Intent(this, CreditsActivity.class)));
        findViewById(R.id.button_rateus).setOnClickListener(v -> {
            Intent rateUs = new Intent(Intent.ACTION_VIEW);
            rateUs.setData(Uri.parse(getString(R.string.app_play_link)));
            startActivity(rateUs);
        });
        findViewById(R.id.button_share).setOnClickListener(v -> {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT,
                    getString(R.string.share_text)
                            + "\n\n"
                            + getString(R.string.app_name)
                            + "\n\n"
                            + getString(R.string.app_play_link)
                            + getPackageName());
            share.setType("text/plain");
            startActivity(share);
        });
        findViewById(R.id.button_help).setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ getString(R.string.email_address)});
            email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_title));
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, getString(R.string.email_chooser_text)));
        });
    }
}
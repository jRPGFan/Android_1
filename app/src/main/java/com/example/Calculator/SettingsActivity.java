package com.example.Calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    SwitchMaterial switchTheme;
    SwitchMaterial switchMode;
    SwitchMaterial switchBottomSettings;
    ImageView previewView;
    MaterialButton mbAccept;
    MaterialButton mbCancel;

    private static final String PREFERENCES = "preferences.xml";
    private static final String NIGHT_MODE_PREFERENCE = "isNightMode";
    private static final String CALCULATOR_EXTENDED = "isExtended";
    private static final String SHOW_BOTTOM_SETTINGS = "hasBottomSettings";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.settings_layout);

        switchTheme = findViewById(R.id.darkLightModeSwitcherSetting);
        switchMode = findViewById(R.id.simpleExtendedModeSwitcherSetting);
        switchBottomSettings = findViewById(R.id.bottomSettingsSwitch);
        previewView = findViewById(R.id.layoutPreview);
        mbAccept = findViewById(R.id.settingsAccept);
        mbCancel = findViewById(R.id.settingsCancel);

        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        switchTheme.setChecked(sharedPreferences.getBoolean(NIGHT_MODE_PREFERENCE, true));
        switchMode.setChecked(sharedPreferences.getBoolean(CALCULATOR_EXTENDED, false));
        switchBottomSettings.setChecked(sharedPreferences.getBoolean(SHOW_BOTTOM_SETTINGS, true));
        switchPreview();

        switchTheme.setOnClickListener(v -> switchPreview());
        switchMode.setOnClickListener(v -> switchPreview());
        switchBottomSettings.setOnClickListener(v -> switchPreview());

        mbAccept.setOnClickListener(v -> {
            setPreferences();
        });

        mbCancel.setOnClickListener(v -> {
            openLauncher();
        });
    }

    private void switchPreview() {
        if (switchTheme.isChecked()) { //Dark Theme
            if (switchMode.isChecked()) //Dark + Extended
                if (switchBottomSettings.isChecked()) //Dark + Extended + Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_extended_dark));
                else //Dark + Extended + No Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_extended_dark_no_setttings));
            else //Dark + Simple
                if (switchBottomSettings.isChecked()) //Dark + Simple + Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_simple_dark));
                else //Dark + Simple + No Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_simple_dark_no_setttings));
        }

        else { //Light Theme
            if (switchMode.isChecked()) //Light + Extended
                if (switchBottomSettings.isChecked()) //Light + Extended + Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_extended_light));
                else //Light + Extended + No Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_extended_light_no_setttings));
            else //Light + Simple
                if (switchBottomSettings.isChecked()) //Light + Simple + Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_simple_light));
                else //Light + Simple + No Bottom Settings
                    previewView.setImageDrawable(getResources().getDrawable(R.drawable.screenshot_simple_light_no_setttings));
        }
    }

    private void setPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NIGHT_MODE_PREFERENCE, switchTheme.isChecked()).apply();
        editor.putBoolean(CALCULATOR_EXTENDED, switchMode.isChecked()).apply();
        editor.putBoolean(SHOW_BOTTOM_SETTINGS, switchBottomSettings.isChecked()).apply();
        openLauncher();
    }

    private void openLauncher() {
        Intent openLauncher = new Intent(SettingsActivity.this, LauncherActivity.class);
        startActivity(openLauncher);
        finish();
    }
}

package com.example.Calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {
    private static final String PREFERENCES = "preferences.xml";
    private static final String CALCULATOR_EXTENDED = "isExtended";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(CALCULATOR_EXTENDED, false)) {
            Intent launchActivityIntent = new Intent(LauncherActivity.this,
                    Calculator_Activity_Extended.class);
            startActivity(launchActivityIntent);
            finish();
        } else {
            Intent launchActivityIntent = new Intent(LauncherActivity.this,
                    Calculator_Activity_Simple.class);
            startActivity(launchActivityIntent);
            finish();
        }
    }
}

package com.example.homework_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSwitchActivity = findViewById(R.id.btnSwitchLayout);
        btnSwitchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSecondActivity();
            }
        });
    }

    private void switchToSecondActivity(){
        Intent switchToSecondActivityIntent = new Intent(this, SecondActivity.class);
        startActivity(switchToSecondActivityIntent);
    }
}

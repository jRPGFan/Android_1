package com.example.Calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    float temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_simple);


        TextView txtView = findViewById(R.id.textView2);
        Button btn0 = findViewById(R.id.button_0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtView.getText().toString().length() > 1 || !txtView.getText().toString().equals("0"))
                    txtView.append("0");
            }
        });

        Button btn1 = findViewById(R.id.button_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("1");
            }
        });

        Button btn2 = findViewById(R.id.button_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("2");
            }
        });

        Button btn3 = findViewById(R.id.button_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("3");
            }
        });

        Button btn4 = findViewById(R.id.button_4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("4");
            }
        });

        Button btn5 = findViewById(R.id.button_5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("5");
            }
        });

        Button btn6 = findViewById(R.id.button_6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("6");
            }
        });

        Button btn7 = findViewById(R.id.button_7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("7");
            }
        });

        Button btn8 = findViewById(R.id.button_8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("8");
            }
        });

        Button btn9 = findViewById(R.id.button_9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append("9");
            }
        });

        Button btnDecimal = findViewById(R.id.button_decimal);
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.append(".");
            }
        });

        Button btnClear = findViewById(R.id.button_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText("0");
            }
        });
//        Button btnPlus = findViewById(R.id.button_plus);
//        btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                temp+= Float.parseFloat(txtView.getText().toString());
//                txtView.setText("0");
//            }
//        });
//
//        Button btnMinus = findViewById(R.id.button_minus);
//        btn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                txtView.append("0");
//            }
//        });


//
//        Button btnSwitchActivity = findViewById(R.id.btnSwitchLayout);
//        btnSwitchActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToSecondActivity();
//            }
//        });
    }

//    private void switchToSecondActivity(){
//        Intent switchToSecondActivityIntent = new Intent(this, SecondActivity.class);
//        startActivity(switchToSecondActivityIntent);
//    }
}

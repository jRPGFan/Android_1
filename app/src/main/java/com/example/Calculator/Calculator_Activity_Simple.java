package com.example.Calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

public class Calculator_Activity_Simple extends AppCompatActivity {

    MaterialTextView fullStatementTextView;
    MaterialTextView resultTextView;

    MaterialButton btn0;
    MaterialButton btn1;
    MaterialButton btn2;
    MaterialButton btn3;
    MaterialButton btn4;
    MaterialButton btn5;
    MaterialButton btn6;
    MaterialButton btn7;
    MaterialButton btn8;
    MaterialButton btn9;

    MaterialButton btnAdd;
    MaterialButton btnSubtract;
    MaterialButton btnMultiply;
    MaterialButton btnDivide;
    MaterialButton btnDecimal;
    MaterialButton btnBackspace;
    MaterialButton btnClear;
    MaterialButton btnEquals;

    MaterialButton btnExtend;
    SwitchMaterial switchTheme;
    ImageView settingsIV;

    double result = 0;
    double currentNumber;
    boolean clearResultAfterOperation = false;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char EQUALS = '=';
    private static final char CLEAR = 'C';
    private static final char IDLE = '~';
    private char CURRENT_ACTION;

    private static final String PREFERENCES = "preferences.xml";
    private static final String NIGHT_MODE_PREFERENCE = "isNightMode";
    private static final String CALCULATOR_EXTENDED = "isExtended";
    private static final String SHOW_BOTTOM_SETTINGS = "hasBottomSettings";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        checkNightMode();
        saveCalculatorExtendedMode(false);
        setContentView(R.layout.activity_calculator_simple);
        initControls();
        initControlListeners();
        showBottomSettings(sharedPreferences.getBoolean(SHOW_BOTTOM_SETTINGS, true));
    }

    private void initControls(){
        fullStatementTextView = findViewById(R.id.tvFullStatement);
        fullStatementTextView.setMovementMethod(new ScrollingMovementMethod());
        resultTextView = findViewById(R.id.tvResult);
        btn0 = findViewById(R.id.button_0);
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn6 = findViewById(R.id.button_6);
        btn7 = findViewById(R.id.button_7);
        btn8 = findViewById(R.id.button_8);
        btn9 = findViewById(R.id.button_9);

        btnAdd = findViewById(R.id.button_plus);
        btnSubtract = findViewById(R.id.button_minus);
        btnMultiply = findViewById(R.id.button_multiply);
        btnDivide = findViewById(R.id.button_divide);
        btnDecimal = findViewById(R.id.button_decimal);
        btnEquals = findViewById(R.id.button_equals);
        btnBackspace = findViewById(R.id.button_backspace);
        btnClear = findViewById(R.id.button_clear);
        btnExtend = findViewById(R.id.button_extend);
        switchTheme = findViewById(R.id.darkLightModeSwitcher);
        switchTheme.setChecked(sharedPreferences.getBoolean(NIGHT_MODE_PREFERENCE, false));
        settingsIV = findViewById(R.id.settingsImageViewButton);
    }

    private void initControlListeners(){
        btn0.setOnClickListener(numericKeysClickListener);
        btn1.setOnClickListener(numericKeysClickListener);
        btn2.setOnClickListener(numericKeysClickListener);
        btn3.setOnClickListener(numericKeysClickListener);
        btn4.setOnClickListener(numericKeysClickListener);
        btn5.setOnClickListener(numericKeysClickListener);
        btn6.setOnClickListener(numericKeysClickListener);
        btn7.setOnClickListener(numericKeysClickListener);
        btn8.setOnClickListener(numericKeysClickListener);
        btn9.setOnClickListener(numericKeysClickListener);
        btnDecimal.setOnClickListener(numericKeysClickListener);

        btnAdd.setOnClickListener(actionKeysClickListener);
        btnSubtract.setOnClickListener(actionKeysClickListener);
        btnMultiply.setOnClickListener(actionKeysClickListener);
        btnDivide.setOnClickListener(actionKeysClickListener);
        btnEquals.setOnClickListener(actionKeysClickListener);
        btnBackspace.setOnClickListener(actionKeysClickListener);
        btnClear.setOnClickListener(actionKeysClickListener);

        btnExtend.setOnClickListener(v -> switchToExtendedActivity());
        switchTheme.setOnClickListener(v -> switchTheme());
//        switchTheme.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
//            //                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            //                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//
//            saveNightModeState(isChecked);
//            checkNightMode();
//            recreate();
//        });

        settingsIV.setOnClickListener(v -> {
            Intent openSettings = new Intent(Calculator_Activity_Simple.this,
                    SettingsActivity.class);
            startActivity(openSettings);
            finish();
        });
    }

    @SuppressLint("NonConstantResourceId")
    private final View.OnClickListener numericKeysClickListener = (view) -> {
        if (CURRENT_ACTION == EQUALS) {
            fullStatementTextView.setText("");
            resultTextView.setText("");
            CURRENT_ACTION = IDLE;
        }

        if (clearResultAfterOperation) {
            resultTextView.setText("");
            clearResultAfterOperation = false;
        }

        switch (view.getId()) {
            case R.id.button_0: {
                if (!resultTextView.getText().toString().equals("0"))
                    resultTextView.append("0");
                break;
            }
            case R.id.button_1: {
                resultTextView.append("1");
                break;
            }
            case R.id.button_2: {
                resultTextView.append("2");
                break;
            }
            case R.id.button_3: {
                resultTextView.append("3");
                break;
            }
            case R.id.button_4: {
                resultTextView.append("4");
                break;
            }
            case R.id.button_5: {
                resultTextView.append("5");
                break;
            }
            case R.id.button_6: {
                resultTextView.append("6");
                break;
            }
            case R.id.button_7: {
                resultTextView.append("7");
                break;
            }
            case R.id.button_8: {
                resultTextView.append("8");
                break;
            }
            case R.id.button_9: {
                resultTextView.append("9");
                break;
            }
            case R.id.button_decimal: {
                resultTextView.append(".");
                break;
            }
            default: break;
        }
    };

    @SuppressLint("NonConstantResourceId")
    private final View.OnClickListener actionKeysClickListener = (view) -> {
        switch (view.getId()) {
            case R.id.button_plus: {
                calculate();
                CURRENT_ACTION = ADDITION;
                fullStatementTextView.append(" + ");
                break;
            }
            case R.id.button_minus: {
                calculate();
                CURRENT_ACTION = SUBTRACTION;
                fullStatementTextView.append(" - ");
                break;
            }
            case R.id.button_multiply: {
                calculate();
                CURRENT_ACTION = MULTIPLICATION;
                fullStatementTextView.append(" * ");
                break;
            }
            case R.id.button_divide: {
                calculate();
                CURRENT_ACTION = DIVISION;
                fullStatementTextView.append(" / ");
                break;
            }
            case R.id.button_equals: {
                calculate();
                result = 0;
                CURRENT_ACTION = EQUALS;
                break;
            }

            case R.id.button_backspace: {
                if (resultTextView.getText().length() > 0)
                    resultTextView.setText(resultTextView.getText().subSequence(0,
                            resultTextView.getText().length() - 1));
                break;
            }

            case R.id.button_clear: {
                result = 0;
                CURRENT_ACTION = CLEAR;
                fullStatementTextView.setText("");
                resultTextView.setText("");
                break;
            }
            default: break;
        }
    };

    private void calculate() {
        String appendText = resultTextView.getText().toString();
        if (appendText.endsWith("."))
            appendText = appendText.substring(0, appendText.length() -1);

        if (result != 0) {
            try {
                currentNumber = Double.parseDouble(appendText);
                clearResultAfterOperation = true;

                if (CURRENT_ACTION == ADDITION) {
                    result = result + currentNumber;
                    appendText = checkResultForDouble(currentNumber);
                    fullStatementTextView.append(appendText);
                }

                if (CURRENT_ACTION == SUBTRACTION) {
                    result = result - currentNumber;
                    appendText = checkResultForDouble(currentNumber);
                    fullStatementTextView.append(appendText);
                }

                if (CURRENT_ACTION == MULTIPLICATION) {
                    result = result * currentNumber;
                    appendText = checkResultForDouble(currentNumber);
                    fullStatementTextView.append(appendText);
                }

                if (CURRENT_ACTION == DIVISION) {
                    if (currentNumber == 0) {
                        result = 0;
                        fullStatementTextView.setText("");
                        resultTextView.setText(R.string.division_by_zero);
                    } else {
                        result = result / currentNumber;
                        appendText = checkResultForDouble(currentNumber);
                        fullStatementTextView.append(appendText);
                    }
                }

                if (result == 0 || CURRENT_ACTION == EQUALS) resultTextView.setText("");
                else resultTextView.setText(checkResultForDouble(result));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else
            try {
                result = Double.parseDouble(appendText);
                if (CURRENT_ACTION != EQUALS)
                    fullStatementTextView.append(appendText);
                clearResultAfterOperation = true;
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
    }

    @SuppressLint("DefaultLocale")
    private String checkResultForDouble(double number){
        if (number == (long) number)
            return String.format("%d", (long) number);
        else
            return String.format("%s", number);
    }

    private void switchToExtendedActivity(){
        Intent switchToSecondActivityIntent = new Intent(Calculator_Activity_Simple.this,
                Calculator_Activity_Extended.class);
        startActivity(switchToSecondActivityIntent);
        finish();
    }

    private void switchTheme(){
        if (switchTheme.isChecked()){
            getApplication().setTheme(R.style.Theme_CalculatorDark);
            saveNightModeState(true);
        }

        else {
            getApplication().setTheme(R.style.Theme_Calculator);
            saveNightModeState(false);
        }
        recreate();
//        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
//            case Configuration.UI_MODE_NIGHT_NO:
//                setTheme(R.style.Theme_CalculatorDark);
//                saveNightModeState(true);
//                break;
//            case Configuration.UI_MODE_NIGHT_YES:
//                setTheme(R.style.Theme_Calculator);
//                saveNightModeState(false);
//                break;
//        }
    }

    private void checkNightMode(){
        if (sharedPreferences.getBoolean(NIGHT_MODE_PREFERENCE, false)){
            //switchTheme.setChecked(true);
            setTheme(R.style.Theme_CalculatorDark);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            //switchTheme.setChecked(false);
            setTheme(R.style.Theme_Calculator);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void saveNightModeState(boolean isNightMode){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NIGHT_MODE_PREFERENCE, isNightMode).apply();

    }

    private void saveCalculatorExtendedMode(boolean isExtended) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CALCULATOR_EXTENDED, isExtended).apply();
    }

    private void showBottomSettings(boolean show) {
        if (show) {
            switchTheme.setChecked(sharedPreferences.getBoolean(NIGHT_MODE_PREFERENCE, false));
            switchTheme.setOnClickListener(v -> switchTheme());

            btnExtend.setOnClickListener(v -> {
                Intent switchToSecondActivityIntent = new Intent(Calculator_Activity_Simple.this,
                        Calculator_Activity_Extended.class);
                startActivity(switchToSecondActivityIntent);
                finish();
            });
        }

        else {
            switchTheme.setVisibility(View.GONE);
            btnExtend.setVisibility(View.GONE);
        }
    }
}

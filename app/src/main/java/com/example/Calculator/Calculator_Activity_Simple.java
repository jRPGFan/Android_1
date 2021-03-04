package com.example.Calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator_Activity_Simple extends AppCompatActivity {

    TextView fullStatementTextView;
    TextView resultTextView;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;

    Button btnAdd;
    Button btnSubtract;
    Button btnMultiply;
    Button btnDivide;
    Button btnDecimal;
    Button btnBackspace;
    Button btnClear;
    Button btnEquals;

    Button btnExtend;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_calculator_simple);
        initControls();
        initControlListeners();
    }

    private void initControls(){
        fullStatementTextView = (TextView)findViewById(R.id.tvFullStatement);
        fullStatementTextView.setMovementMethod(new ScrollingMovementMethod());
        resultTextView = (TextView)findViewById(R.id.tvResult);
        btn0 = (Button)findViewById(R.id.button_0);
        btn1 = (Button)findViewById(R.id.button_1);
        btn2 = (Button)findViewById(R.id.button_2);
        btn3 = (Button)findViewById(R.id.button_3);
        btn4 = (Button)findViewById(R.id.button_4);
        btn5 = (Button)findViewById(R.id.button_5);
        btn6 = (Button)findViewById(R.id.button_6);
        btn7 = (Button)findViewById(R.id.button_7);
        btn8 = (Button)findViewById(R.id.button_8);
        btn9 = (Button)findViewById(R.id.button_9);

        btnAdd = (Button)findViewById(R.id.button_plus);
        btnSubtract = (Button)findViewById(R.id.button_minus);
        btnMultiply = (Button)findViewById(R.id.button_multiply);
        btnDivide = (Button)findViewById(R.id.button_divide);
        btnDecimal = (Button)findViewById(R.id.button_decimal);
        btnEquals = (Button)findViewById(R.id.button_equals);
        btnBackspace = (Button)findViewById(R.id.button_backspace);
        btnClear = (Button)findViewById(R.id.button_clear);
        btnExtend = (Button)findViewById(R.id.button_extend);
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
        Intent switchToSecondActivityIntent = new Intent(Calculator_Activity_Simple.this, Calculator_Activity_Extended.class);
        startActivity(switchToSecondActivityIntent);
    }
}

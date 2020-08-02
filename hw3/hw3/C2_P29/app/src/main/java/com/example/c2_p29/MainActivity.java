package com.example.c2_p29;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.graphics.Color;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final private int APPLECALORIES = 95;
    final private int PEARCALORIES = 100;
    final private int BANANACALORIES = 105;
    final private int ORANGECALORIES = 45;


    EditText apples;
    EditText pears;
    EditText bananas;
    EditText oranges;
    TextView output;

    ConstraintLayout layout;

    int fval1;
    int fval2;
    int fval3;
    int fval4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference variables to XML views
        apples = (EditText) findViewById(R.id.ss1);
        pears = (EditText) findViewById(R.id.ss2);
        bananas = (EditText) findViewById(R.id.ss3);
        oranges = (EditText) findViewById(R.id.ss4);
        layout = (ConstraintLayout) findViewById(R.id.layout);

        output = (TextView) findViewById(R.id.output);


        // Setting layout color

        layout.setBackgroundColor(Color.GRAY);

        // Calories Count Event

        apples.addTextChangedListener(watch);
        pears.addTextChangedListener(watch);
        bananas.addTextChangedListener(watch);
        oranges.addTextChangedListener(watch);
    }

    // We use the same TextWatcher for all editText fields
    TextWatcher watch = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            // If the editText field is blank, we assume the serving size is zero
            fval1 = apples.getText().toString().isEmpty() ? 0 : Integer.parseInt(apples.getText().toString());

            fval2 = pears.getText().toString().isEmpty() ? 0 : Integer.parseInt(pears.getText().toString());

            fval3 = bananas.getText().toString().isEmpty() ? 0 : Integer.parseInt(bananas.getText().toString());

            fval4 = oranges.getText().toString().isEmpty() ? 0 : Integer.parseInt(oranges.getText().toString());


            // Display sum of serving sizes scaled by calories
            output.setText(String.valueOf(APPLECALORIES * fval1 + PEARCALORIES * fval2 + BANANACALORIES * fval3 + ORANGECALORIES * fval4));


        }
    };
}

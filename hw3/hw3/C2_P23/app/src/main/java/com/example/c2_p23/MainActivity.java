package com.example.c2_p23;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView curClr;
    private Button BClr;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // int keeps track of current color
        color = 0;

        // Initialize light to be red
        curClr = findViewById(R.id.curClr);
        curClr.setBackgroundColor(Color.RED);
        curClr.setTextColor(Color.BLACK);
        curClr.setText("RED");
        curClr.setGravity(Gravity.CENTER);

        BClr = findViewById((R.id.BClr));

        // Listener cycles through three colors
        BClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color == 2) {
                    color = 0;
                    curClr.setText("RED");
                    curClr.setBackgroundColor(Color.RED);
                }
                else if (color == 0) {
                    color = 1;
                    curClr.setText("YELLOW");
                    curClr.setBackgroundColor(Color.YELLOW);
                }
                else if (color == 1) {
                    color = 2;
                    curClr.setText("GREEN");
                    curClr.setBackgroundColor(Color.GREEN);
                }

            }
        });
    }


}
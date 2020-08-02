package com.example.w3_p3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText answer;
    private TextView topArg;
    private TextView botArg;
    private Button restartGame;
    private Button buttonR;
    private Button submitB;
    private Button interMessage;
    private int topR;
    private int botR;
    private int counterCorrect;
    private int totalCounter;

    private boolean gameOver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        buttonR = (Button) findViewById(R.id.btnRand);
        submitB = (Button) findViewById(R.id.btnsubmitans);
        answer = (EditText) findViewById(R.id.etAns);
        topArg = (TextView) findViewById(R.id.randNum1);
        botArg = (TextView) findViewById(R.id.randNum2);
        restartGame = (Button) findViewById(R.id.btnRestart);
        interMessage = (Button) findViewById(R.id.btnMessage);


        // The first time the activity is started, welcome the user and generate a problem
        if(savedInstanceState == null) {
            generate_problem();
            Toast.makeText(getApplicationContext(), String.format("Welcome %s", getIntent().getExtras().getString("username")), Toast.LENGTH_SHORT).show();
        }

        // Let user check score
        interMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your current score is " + counterCorrect + "/10", Toast.LENGTH_SHORT).show();
            }
        });

        // This button resets the game
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterCorrect = 0;
                totalCounter = 0;
                generate_problem();
            }
        });

        submitB.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please type your answer!", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(answer.getText().toString()) == topR / botR) {
                    counterCorrect++;
                    totalCounter ++;
                    generate_problem();
                    Toast.makeText(getApplicationContext(), "Your current score is " + counterCorrect + "/10", Toast.LENGTH_SHORT).show();
                }
                else{
                    totalCounter ++;
                    generate_problem();
                    Toast.makeText(getApplicationContext(), "Your current score is " + counterCorrect + "/10", Toast.LENGTH_SHORT).show();
                }
            }
        }));

        // Added separate reset button that activates when game is complete for clarity for user
        restartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOver = false;
                submitB.setEnabled(true);
                totalCounter = 0;
                counterCorrect = 0;
                generate_problem();
                restartGame.setVisibility(View.INVISIBLE);
            }
        });

    }

    // Save current problem and stats if activity is destroyed by System
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("topR", topR);
        outState.putInt("botR", botR);
        outState.putInt("counterCorrect", counterCorrect);
        outState.putInt("totalCounter", totalCounter);
        outState.putBoolean("gameOver", gameOver);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        topR = savedInstanceState.getInt("topR");
        botR = savedInstanceState.getInt("botR");
        topArg.setText(String.format("%d", topR));
        botArg.setText(String.format("%d", botR));
        counterCorrect = savedInstanceState.getInt("counterCorrect");
        totalCounter = savedInstanceState.getInt("totalCounter");
        gameOver = savedInstanceState.getBoolean("gameOver");

        // If the game is over we show the restart button and disable submit
        if(gameOver) {
            restartGame.setVisibility(View.VISIBLE);
            submitB.setEnabled(false);
        }
    }


    // Generate a simple integer division problem, stop after 10 have been generated in one game
    private void generate_problem() {
        answer.setText(null);
        if(totalCounter < 10) {
            botR = (int) ( Math.random() * 10) + 1;
            topR = (int) (Math.random() * 11) * botR;
            topArg.setText(String.format("%s", topR));
            botArg.setText(String.format("%s", botR));
        }
        else {
            Toast.makeText(getApplicationContext(), "Your total score is " + counterCorrect + "/10", Toast.LENGTH_SHORT).show();
            gameOver = true;
            restartGame.setVisibility(View.VISIBLE);
            submitB.setEnabled(false);
        }
    }

}
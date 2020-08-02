package com.example.w3_p3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private  EditText Password;
    private TextView info;
    private Button Login;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize view references
        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPass);
        info = (TextView)findViewById(R.id.tvInc);
        Login = (Button)findViewById(R.id.btnSub);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());

            }
        });

    }

    // Verify entered username/password
    private void validate(String userName, String userPassword) {
        if((userName.equals("haveaniceday")) && (userPassword.equals ("591"))) {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            intent.putExtra("username", userName);
            startActivity(intent);
        }

        // User has 16 attempts to login
        else {
            counter++;

            info.setText(String.format("Number of attempted logins: %d \nLogin Failed!", counter));

            if (counter > 15) {
                Login.setEnabled(false);
                Login.setVisibility(View.INVISIBLE);
            }

        }
    }
}
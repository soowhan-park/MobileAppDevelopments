package com.example.w3_p2;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    SeekBar skC;
    SeekBar skF;
    TextView txtC;
    TextView txtF;
    TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skC = findViewById(R.id.skC);
        skF = findViewById(R.id.skF);

        txtC = findViewById(R.id.txtC);
        txtF = findViewById(R.id.txtF);
        txtMsg = findViewById(R.id.txtMsg);

        //Initialize views
        //Use current locale for number format
        skC.setProgress(0);
        skF.setProgress(3200);
        txtC.setText(String.format(Locale.getDefault(), "%.2f", 0.00));
        txtF.setText(String.format(Locale.getDefault(), "%.2f", 32.00));
        updateMessage();

        skC.setOnSeekBarChangeListener(listen);

        skF.setOnSeekBarChangeListener(listen);
    }

    SeekBar.OnSeekBarChangeListener listen = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            // Use this switch so we can use one listener for both SeekBars
            switch (seekBar.getId()){

                case R.id.skC:
                    txtC.setText(String.format(Locale.getDefault(), "%.2f", progress / 100.0));
                    skF.setProgress((int) (((progress/100.0)*1.8 + 32) * 100));
                    updateMessage();
                    break;

                case R.id.skF:
                    txtF.setText(String.format(Locale.getDefault(), "%.2f", progress / 100.0));
                    skC.setProgress((int) ((((progress/100.0) - 32) / 1.8) * 100));

                    // I overwrite the text of the Celsius bar's progress to allow negatives
                    txtC.setText(String.format(Locale.getDefault(), "%.2f", (((progress/100.0) - 32) / 1.8)));
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateMessage() {

        // Instead of setting locale to English for all String.format(), I just replace all commas with periods to satisfy Double.parseDouble()
        // Keeps numerical localization without importing NumberFormat
        if(Double.parseDouble(txtC.getText().toString().replace(",", ".")) > 12.0) txtMsg.setText(getResources().getString(R.string.notcold));
        else txtMsg.setText(getResources().getString(R.string.cold));
    }


}
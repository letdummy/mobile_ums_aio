package com.sekalisubmit.timer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private EditText timeInput;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timer);
        timeInput = findViewById(R.id.input_time);
        Button startButton = findViewById(R.id.start_button);
        Button stopButton = findViewById(R.id.stop_button);

        startButton.setOnClickListener(v -> {
            long millisInFuture = Long.parseLong(timeInput.getText().toString()) * 1000;
            countDownTimer = new CountDownTimer(millisInFuture, 1000) {

                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText(getString(R.string.seconds_remaining) + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timerTextView.setText(R.string.ringggg);
                    startService(new Intent(MainActivity.this, SoundService.class));
                }
            }.start();
        });

        stopButton.setOnClickListener(v -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            stopService(new Intent(MainActivity.this, SoundService.class));
            timerTextView.setText(R.string.hook);
        });
    }
}
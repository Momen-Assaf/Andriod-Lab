package edu.birzeit.experiment3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SwitchActivity extends AppCompatActivity {

    private Button switchB;
    private TextView timer;
    private CountDownTimer downTimer;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        switchB = findViewById(R.id.switchB);
        timer = findViewById(R.id.timer);
        setDownTimer();

        switchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopDownTimer();
            }
        });
    }
    private void stopDownTimer(){
        if( downTimer != null){
            downTimer.cancel();
            downTimer = null;
        }
        Intent mainActivity = new Intent(SwitchActivity.this, MainActivity.class);
        SwitchActivity.this.startActivity(mainActivity);
        finish();
    }
    private void setDownTimer(){
        downTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished/1000;
                timer.setText(String.valueOf(seconds));
            }

            @Override
            public void onFinish() {
                stopDownTimer();
            }
        }.start();
    }
}
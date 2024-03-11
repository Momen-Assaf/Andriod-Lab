package edu.birzeit.todo1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int count = 0, totalQuestions = 5;
    private TextView timer;
    private Thread timerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView score = findViewById(R.id.textView1);
        timer = findViewById(R.id.timeView);
        TextView question = findViewById(R.id.questionView);
        Button answer1 = findViewById(R.id.ans1);
        Button answer2 = findViewById(R.id.ans2);
        Button answer3 = findViewById(R.id.ans3);
        Button answer4 = findViewById(R.id.ans4);

    }
    private void startTimerThread() {
        timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < totalQuestions) {
                    for (int i = 10; i > 0; i--) {
                        final int seconds = i;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timer.setText(" " + seconds);
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                }
            }
        });
        timerThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
    }
}
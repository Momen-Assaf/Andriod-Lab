package edu.birzeit.todo1;

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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int score_player = 0, qNum = 1;
    private TextView timer, score, question;
    private Button answer1, answer2, answer3, answer4;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.textView1);
        score.setText(String.valueOf(score_player));

        timer = findViewById(R.id.textView2);
        question = findViewById(R.id.questionView);

        answer1 = findViewById(R.id.ans1);
        answer2 = findViewById(R.id.ans2);
        answer3 = findViewById(R.id.ans3);
        answer4 = findViewById(R.id.ans4);
        setQuestionsAnswers();

    }
    private void startDownTimer(){
        countDownTimer = new CountDownTimer(10000,1000){
            public void onTick(long millisUntilFinished){
                long seconds = millisUntilFinished/1000;
                timer.setText(String.valueOf(seconds));
            }
            public void onFinish(){
                setQuestionsAnswers();
            }
        }.start();
    }
    private void stopDownTimer(){
        answer1.setOnClickListener(null);
        answer2.setOnClickListener(null);
        answer3.setOnClickListener(null);
        answer4.setOnClickListener(null);
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        setQuestionsAnswers();
    }
    private void wrongAnsListen(Button answer){
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopDownTimer();
            }
        });
    }
    private void setQuestionsAnswers(){
        switch (qNum){
            case 1:
                question.setText("Question 1: What is my Name?");
                answer1.setText("Momen");
                answer2.setText("Not Momen");
                answer3.setText("Also not Momen");
                answer4.setText("Also not also Momen");
                startDownTimer();
                qNum++;
                wrongAnsListen(answer2);
                wrongAnsListen(answer3);
                wrongAnsListen(answer4);
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score_player++;
                        score.setText(String.valueOf(score_player));
                        stopDownTimer();
                    }
                });
                break;
            case 2:
                question.setText("Question 2: What is my Univercity id?");
                answer1.setText("not 1191529");
                answer2.setText("1191529");
                answer3.setText("1192222222");
                answer4.setText("idk");
                startDownTimer();
                qNum++;
                wrongAnsListen(answer1);
                wrongAnsListen(answer3);
                wrongAnsListen(answer4);
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score_player++;
                        score.setText(String.valueOf(score_player));
                        stopDownTimer();
                    }
                });
                break;
            case 3:
                question.setText("Question 3: What is the capital of France?");
                answer1.setText("London");
                answer2.setText("Paris");
                answer3.setText("Rome");
                answer4.setText("Berlin");
                startDownTimer();
                qNum++;
                wrongAnsListen(answer1);
                wrongAnsListen(answer3);
                wrongAnsListen(answer4);
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score_player++;
                        score.setText(String.valueOf(score_player));
                        stopDownTimer();
                    }
                });
                break;
            case 4:
                question.setText("Question 4: What is the tallest mountain in the world?");
                answer1.setText("Mount Kilimanjaro");
                answer2.setText("K2");
                answer3.setText("Mount Everest");
                answer4.setText("Mount McKinley");
                startDownTimer();
                qNum++;
                wrongAnsListen(answer1);
                wrongAnsListen(answer2);
                wrongAnsListen(answer4);
                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score_player++;
                        score.setText(String.valueOf(score_player));
                        stopDownTimer();
                    }
                });
                break;
            case 5:
                question.setText("Question 5: Who painted the Mona Lisa?");
                answer1.setText("Leonardo da Vinci");
                answer2.setText("Vincent van Gogh");
                answer3.setText("Pablo Picasso");
                answer4.setText("Michelangelo");
                startDownTimer();
                qNum++;
                wrongAnsListen(answer2);
                wrongAnsListen(answer3);
                wrongAnsListen(answer4);
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score_player++;
                        score.setText(String.valueOf(score_player));
                        stopDownTimer();
                    }
                });
                break;
            default:
                Player player = new Player();
                player.setResult(score_player);
                Player.playerArrayList.add(player);
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
                break;
        }
    }
}
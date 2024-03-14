package edu.birzeit.todo1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        TextView outcome = findViewById(R.id.outcome);
        TextView result = findViewById(R.id.score_result);
        Button reset = findViewById(R.id.reset_button);
        reset.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

        for(Player player : Player.playerArrayList) {
            int res = player.getResult();
            if (res > 3) {
                outcome.setText("You Won!");
                outcome.setTextColor(Color.GREEN);
            }
            else {
                outcome.setText("You Lost!");
                outcome.setTextColor(Color.RED);
            }
            result.setText(res + "/5");
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                ResultActivity.this.startActivity(intent);
                finish();
            }
        });

    }
}
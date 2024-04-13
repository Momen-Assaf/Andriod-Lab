package edu.birzeit.expirement6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView textViewUserName;
    TextView textViewPassword;
    Button buttonLoad;
    Button buttonBackToMainActivity;
    SharedPrefManager sharedPrefManager;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textViewUserName = findViewById(R.id.textView);
        textViewPassword = findViewById(R.id.textView2);
        buttonLoad =  findViewById(R.id.load);
        buttonBackToMainActivity =  findViewById(R.id.goToMain);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        intent = new Intent(SecondActivity.this,MainActivity.class);
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textViewUserName.setText(sharedPrefManager.readString("userName","noValue"));

                textViewPassword.setText(sharedPrefManager.readString("password","noValue"));
            }
        });
        buttonBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });
    }

}
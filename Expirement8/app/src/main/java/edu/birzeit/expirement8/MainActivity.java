package edu.birzeit.expirement8;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    Button button;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgress(false);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);

                connectionAsyncTask.execute("https://mocki.io/v1/5c7b49eb-3301-4105-b965-1680ba327bf9");
            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.layout);
    }
    public void setButtonText(String text) {
        button.setText(text);
    }
    public void fillStudents(List<Student> students) {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout);

        linearLayout.removeAllViews();
        for (int i = 0; i < students.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(students.get(i).toString());
            linearLayout.addView(textView);
        }
    }
    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

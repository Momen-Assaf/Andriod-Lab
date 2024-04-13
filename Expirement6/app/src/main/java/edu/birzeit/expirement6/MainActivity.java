package edu.birzeit.expirement6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextPassword;
    Button buttonSave, buttonTest;
    Button buttonGoToSecondActivity;
    SharedPrefManager sharedPrefManager;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSave = findViewById(R.id.save);
        buttonGoToSecondActivity = findViewById(R.id.goToSec);
        buttonTest = findViewById(R.id.test);
        sharedPrefManager =SharedPrefManager.getInstance(this);
        intent = new Intent(MainActivity.this,SecondActivity.class);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPrefManager.writeString("userName",editTextUserName.getText().toString());

                sharedPrefManager.writeString("password",hashPassword(editTextPassword.getText().toString()));
                Toast.makeText(MainActivity.this, "Values written to shared Preferences",
                        Toast.LENGTH_SHORT).show();
            }
        });
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = sharedPrefManager.readString("userName","noValue");
                String password = sharedPrefManager.readString("password","noValue");

                if( userName.equals(editTextUserName.getText().toString()) && password.equals(hashPassword(editTextPassword.getText().toString()))) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonGoToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });
    }
    private String hashPassword(String password) {
        try {
            // Create MessageDigest instance for hashing
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert byte[] to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            // Get the hashed password as a string
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception
            return null;
        }
    }
}
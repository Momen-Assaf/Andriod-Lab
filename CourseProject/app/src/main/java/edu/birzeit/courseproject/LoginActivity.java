package edu.birzeit.courseproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.birzeit.courseproject.database.AdminDatabaseHelper;
import edu.birzeit.courseproject.database.UserDatabaseHelper;
import edu.birzeit.courseproject.models.Admin;
import edu.birzeit.courseproject.models.User;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private CheckBox cbRememberMe;
    private Button btnLogin;
    private UserDatabaseHelper userDatabaseHelper;
    private AdminDatabaseHelper adminDatabaseHelper;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        btnLogin = findViewById(R.id.btnLogin);

        userDatabaseHelper = new UserDatabaseHelper(this);
        adminDatabaseHelper = new AdminDatabaseHelper(this);

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("rememberMe", false)) {
            etEmail.setText(sharedPreferences.getString("email", ""));
            cbRememberMe.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = hashPassword(etPassword.getText().toString());
                User user = userDatabaseHelper.getUser(email, password);
                if (user != null) {
                    if (cbRememberMe.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putBoolean("rememberMe", true);
                        editor.apply();
                    } else {
                        sharedPreferences.edit().clear().apply();
                    }
                    Intent intent = new Intent(LoginActivity.this, HomeCustomerActivity.class);
                    startActivity(intent);
                } else {
                    Admin admin = adminDatabaseHelper.getAdmin(email,password);
                    if (admin != null) {
                        Log.d("LoginActivity", "Admin found: " + admin.getEmail());
                        if (cbRememberMe.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", email);
                            editor.putBoolean("rememberMe", true);
                            editor.apply();
                        } else {
                            sharedPreferences.edit().clear().apply();
                        }
                        Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

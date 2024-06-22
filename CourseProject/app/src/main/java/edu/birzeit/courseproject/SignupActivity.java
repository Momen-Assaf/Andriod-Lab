package edu.birzeit.courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.birzeit.courseproject.database.DatabaseHelper;
import edu.birzeit.courseproject.models.User;

public class SignupActivity extends AppCompatActivity {
    private EditText etEmail, etPhone, etFirstName, etLastName, etPassword, etConfirmPassword;
    private Spinner spinnerGender;
    private Button btnSignUp;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnSignUp = findViewById(R.id.btnRegister);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    User user = new User();
                    user.setEmail(etEmail.getText().toString());
                    user.setPhone(etPhone.getText().toString());
                    user.setFirstName(etFirstName.getText().toString());
                    user.setLastName(etLastName.getText().toString());
                    user.setGender(spinnerGender.getSelectedItem().toString());
                    user.setPassword(hashPassword(etPassword.getText().toString()));

                    if (databaseHelper.addUser(user)) {
                        Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(etEmail.getText()) || !Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches()) {
            etEmail.setError("Valid email is required");
            return false;
        }

        if (TextUtils.isEmpty(etPhone.getText()) || !etPhone.getText().toString().matches("^05\\d{8}$")) {
            etPhone.setError("Valid phone number is required");
            return false;
        }

        if (TextUtils.isEmpty(etFirstName.getText()) || etFirstName.getText().length() < 3) {
            etFirstName.setError("First name must be at least 3 characters");
            return false;
        }

        if (TextUtils.isEmpty(etLastName.getText()) || etLastName.getText().length() < 3) {
            etLastName.setError("Last name must be at least 3 characters");
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText()) || etPassword.getText().length() < 8 || !etPassword.getText().toString().matches(".*[a-zA-Z].*") || !etPassword.getText().toString().matches(".*\\d.*")) {
            etPassword.setError("Password must be at least 8 characters, including a letter and a number");
            return false;
        }

        if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError("Passwords do not match");
            return false;
        }

        return true;
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

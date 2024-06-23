// ProfileFragment.java
package edu.birzeit.courseproject.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.birzeit.courseproject.R;
import edu.birzeit.courseproject.database.UserDatabaseHelper;
import edu.birzeit.courseproject.models.User;

public class ProfileFragment extends Fragment {

    private EditText etFirstName, etLastName, etPhone, etPassword, etConfirmPassword;
    private Button btnUpdate;
    private UserDatabaseHelper userDatabaseHelper;
    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etPhone = view.findViewById(R.id.etPhone);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        userDatabaseHelper = new UserDatabaseHelper(getContext());
        SharedPreferences prefs = getActivity().getSharedPreferences("loginPrefs", getContext().MODE_PRIVATE);
        String email = prefs.getString("email", null);
        if (email != null) {
            currentUser = userDatabaseHelper.getUserByEmail(email);
            loadUserData();
        }

        btnUpdate.setOnClickListener(v -> {
            if (validateInput()) {
                currentUser.setFirstName(etFirstName.getText().toString());
                currentUser.setLastName(etLastName.getText().toString());
                currentUser.setPhone(etPhone.getText().toString());
                currentUser.setPassword(hashPassword(etPassword.getText().toString()));

                boolean isUpdated = userDatabaseHelper.updateUser(currentUser);
                if (isUpdated) {
                    Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void loadUserData() {
        etFirstName.setText(currentUser.getFirstName());
        etLastName.setText(currentUser.getLastName());
        etPhone.setText(currentUser.getPhone());

    }

    private boolean validateInput() {
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

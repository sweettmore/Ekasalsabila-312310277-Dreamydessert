package com.example.dreamy_dessert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dreamy_dessert.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText passwordEditText, confirmPasswordEditText;
    private ImageView eyeIconPassword, eyeIconConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Set up the UI elements
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        eyeIconPassword = findViewById(R.id.eye_icon_password);
        eyeIconConfirmPassword = findViewById(R.id.eye_icon_confirm_password);

        // Set up the Sign In TextView click listener
        TextView signInText = findViewById(R.id.sign_in_text);
        signInText.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Set up the Register button click listener
        Button registerButton = findViewById(R.id.register_button); // Make sure the ID matches your layout
        registerButton.setOnClickListener(v -> {
            if (validateInputs()) { // Validate inputs
                Intent intent = new Intent(RegisterActivity.this, FillProfilActivity.class);
                startActivity(intent); // Navigate to FillProfileActivity
            }
        });

        // Set up the password eye icon click listeners
        eyeIconPassword.setOnClickListener(v -> togglePasswordVisibility(passwordEditText, eyeIconPassword));
        eyeIconConfirmPassword.setOnClickListener(v -> togglePasswordVisibility(confirmPasswordEditText, eyeIconConfirmPassword));
    }

    // Method to toggle the visibility of the password
    private void togglePasswordVisibility(EditText passwordField, ImageView eyeIcon) {
        if (passwordField.getInputType() == 129) {  // Password is hidden (input type 129 = password)
            passwordField.setInputType(1);  // Change to text (input type 1 = normal text)
            eyeIcon.setImageResource(R.drawable.icon_moon_eye);  // Set eye icon to "open"
        } else {
            passwordField.setInputType(129);  // Change to password
            eyeIcon.setImageResource(R.drawable.tabler_eye_off);  // Set eye icon to "closed"
        }

        // Move the cursor to the end of the text after changing the input type
        passwordField.setSelection(passwordField.getText().length());
    }

    // Example method for input validation (you can adjust it based on your needs)
    private boolean validateInputs() {
        if (passwordEditText.getText().toString().isEmpty() || confirmPasswordEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
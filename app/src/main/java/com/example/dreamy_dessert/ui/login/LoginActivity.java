package com.example.dreamy_dessert.ui.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy_dessert.R;
import com.example.dreamy_dessert.RegisterActivity;
import com.example.dreamy_dessert.NavigasiActivity; // Import NavigasiActivity
import com.example.dreamy_dessert.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private boolean isPasswordVisible = false; // Track password visibility state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate layout using ViewBinding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize ViewModel
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        // Reference UI components
        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final ImageView passwordToggle = binding.passwordToggle; // Eye icon
        final Button loginButton = binding.buttonSignIn;
        final ProgressBar loadingProgressBar = binding.loading;
        final TextView registerTextView = binding.registerLink;

        // Observe LoginFormState for form validation
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(true); // Enable login button even if fields are empty
            }
        });

        // Observe LoginResult for login outcomes
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    // Remove the welcome Toast
                    // Navigate to NavigasiActivity after successful login
                    Intent intent = new Intent(LoginActivity.this, NavigasiActivity.class);
                    startActivity(intent); // Navigate to NavigasiActivity
                    finish(); // Close LoginActivity
                }
            }
        });

        // Add TextWatcher for input validation
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(
                            usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        // Toggle password visibility
        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.tabler_eye_off); // Set to closed eye icon
                } else {
                    // Show password
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.icon_moon_eye); // Set to open eye icon
                }
                isPasswordVisible = !isPasswordVisible;
                // Move cursor to the end
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        // Login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        // Navigate to RegisterActivity when register text clicked
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}

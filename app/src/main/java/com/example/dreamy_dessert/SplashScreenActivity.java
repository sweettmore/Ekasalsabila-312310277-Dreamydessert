package com.example.dreamy_dessert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dreamy_dessert.ui.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 3000; // Durasi splash screen dalam milidetik (3 detik)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Atur padding untuk elemen utama berdasarkan insets (untuk mendukung layar penuh)
        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handler untuk berpindah ke MainActivity setelah durasi tertentu
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Tutup SplashScreenActivity agar tidak bisa kembali ke layar ini
        }, 3000);
    }
}

package com.example.dreamy_dessert;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigasiActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private final Fragment homeFragment = new HomeFragment();
    private final Fragment orderFragment = new OrderFragment();
    private final Fragment deliveryFragment = new DeliveryFragment();
    private final Fragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigasi);

        // Mengatur Edge-to-Edge tampilan
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);  // Mengganti setOnItemSelectedListener menjadi setOnNavigationItemSelectedListener

        // Set default fragment saat pertama kali
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        // Pilih fragment berdasarkan menu yang dipilih menggunakan if-else
        if (item.getItemId() == R.id.home) {
            selectedFragment = homeFragment;
        } else if (item.getItemId() == R.id.order) {
            selectedFragment = orderFragment;
        } else if (item.getItemId() == R.id.delivery) {
            selectedFragment = deliveryFragment;
        } else if (item.getItemId() == R.id.account) {
            selectedFragment = accountFragment;
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();
            return true;
        }

        return false;
    }
}

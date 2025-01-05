package com.example.dreamy_dessert;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class FillProfilActivity extends AppCompatActivity {

    private EditText inputName, inputEmail, inputBirthday, inputMobile;
    private RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_profil);

        // Edge-to-Edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi tombol "Back"
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            // Kembali ke halaman sebelumnya
            onBackPressed();
        });

        // Inisialisasi EditText
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        inputBirthday = findViewById(R.id.input_birthday);
        inputMobile = findViewById(R.id.input_mobile);
        genderGroup = findViewById(R.id.radio_group_gender);

        // Nonaktifkan input manual untuk tanggal lahir
        inputBirthday.setInputType(InputType.TYPE_NULL);

        // Set onClick untuk menampilkan kalender
        inputBirthday.setOnClickListener(v -> showDatePickerDialog());

        // Inisialisasi tombol "Continue"
        Button nextButton = findViewById(R.id.button_continue);
        nextButton.setOnClickListener(v -> {
            if (validateInputs()) {
                // Jika validasi berhasil, navigasi ke NavigasiActivity
                Intent intent = new Intent(FillProfilActivity.this, NavigasiActivity.class);
                startActivity(intent); // Mulai NavigasiActivity
                finish(); // Optional: Tutup FillProfilActivity agar pengguna tidak bisa kembali ke halaman ini
            }
        });
    }

    /**
     * Menampilkan dialog DatePicker untuk memilih tanggal.
     */
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format tanggal yang dipilih dan masukkan ke EditText
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    inputBirthday.setText(selectedDate);
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

    /**
     * Memvalidasi semua input pengguna sebelum melanjutkan.
     */
    private boolean validateInputs() {
        if (inputName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (inputEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your email.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (inputBirthday.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please select your birthday.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (inputMobile.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your mobile number.", Toast.LENGTH_SHORT).show();
            return false;
        }

        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select your gender.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

package com.rs.kencana.view.login;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.rs.kencana.R;
import com.rs.kencana.database.model.Pegawai;
import com.rs.kencana.view.MainActivity;
import com.rs.kencana.view.MainAdminActivity;
import com.rs.kencana.view.daftar.DaftarActivity;
import com.rs.kencana.view.dialog.DialogPesan;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_DAFTAR = 101;

    private EditText editId;
    private EditText editPassword;
    private Button buttonDaftar;
    private Button buttonMasuk;

    private LoginActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = findViewById(R.id.activity_login_user_id_text);
        editPassword = findViewById(R.id.activity_login_password_text);
        buttonDaftar = findViewById(R.id.activity_login_button_daftar);
        buttonMasuk = findViewById(R.id.activity_login_button_masuk);

        viewModel = ViewModelProviders.of(this).get(LoginActivityViewModel.class);

        buttonDaftar.setOnClickListener(v -> {
            Intent intent = DaftarActivity.newIntenct(getBaseContext());
            startActivityForResult(intent, REQUEST_DAFTAR);
        });

        buttonMasuk.setOnClickListener(v -> {
            if(editId.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty()) {
                DialogPesan.newInstance("Login Gagal", "Field Harus Diisi")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            String id = editId.getText().toString();
            String password = editPassword.getText().toString();

            Pegawai pegawai = viewModel.getPegawai(id, password);
            if(pegawai == null) {
                DialogPesan.newInstance("Login Gagal", "ID dan Password Tidak Sesuai")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(pegawai.isAksesCuti()) {
                Intent intent = MainAdminActivity.newIntent(getBaseContext());
                startActivity(intent);
            }else {
                Intent intent = MainActivity.newIntent(getBaseContext(), id);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_DAFTAR) {
            Toast.makeText(getBaseContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.rs.kencana.view.daftar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.rs.kencana.R;
import com.rs.kencana.database.model.Jabatan;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.Pegawai;
import com.rs.kencana.view.dialog.DialogPesan;

public class DaftarActivity extends AppCompatActivity {


    private EditText editId;
    private EditText editNama;
    private EditText editPassword;
    private EditText editEmail;
    private EditText editAlamat;
    private EditText editNoHP;
    private Spinner spinnerJenisKelamin;
    private Spinner spinnerPosisi;
    private Button buttonSimpan;

    private DaftarActivityViewModel viewModel;

    public static Intent newIntenct(Context context) {
        Intent intent = new Intent(context, DaftarActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);


        editId = findViewById(R.id.daftar_id_editText);
        editNama = findViewById(R.id.daftar_nama_pegawai_editText);
        editPassword = findViewById(R.id.daftar_password_pegawai_editText);
        editEmail = findViewById(R.id.daftar_email_editText);
        editAlamat = findViewById(R.id.daftar_alamat_editText);
        spinnerJenisKelamin = findViewById(R.id.daftar_jenis_kelamin);
        spinnerPosisi = findViewById(R.id.daftar_posisi);
        editNoHP  = findViewById(R.id.daftar_nohp_editText);
        buttonSimpan = findViewById(R.id.daftar_simpan_btn);

        viewModel = ViewModelProviders.of(this).get(DaftarActivityViewModel.class);

        viewModel.getAllJabatan().observe(this, list -> {
            ArrayAdapter<Jabatan> adapter = new ArrayAdapter(this, R.layout.spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerPosisi.setAdapter(adapter);
        });

        String[] listJenisKelamin = {"Laki-Laki","Perempuan"};
//        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listJenisKelamin);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.spinner_item, listJenisKelamin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(adapter);

        buttonSimpan.setOnClickListener(v -> {
            if(editId.getText().toString().isEmpty() || editNama.getText().toString().isEmpty()
                    || editPassword.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty() || editAlamat.getText().toString().isEmpty() || editNoHP.getText().toString().isEmpty() ) {
                DialogPesan.newInstance("Daftar Gagal", "Field Harus Diisi")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            Jabatan jabatan = (Jabatan) spinnerPosisi.getSelectedItem();
            String jenisKelamin = (String) spinnerJenisKelamin.getSelectedItem();

            String id = editId.getText().toString();
            String nama = editNama.getText().toString();
            String password = editPassword.getText().toString();
            String email = editEmail.getText().toString();
            String alamat = editAlamat.getText().toString();
            String noHP = editNoHP.getText().toString();

            Pegawai pegawai = new Pegawai(id, nama, alamat, password, jenisKelamin, jabatan.getId(), email, noHP);
            int hasil = viewModel.daftarPegawai(pegawai);
            if(hasil != 1) {
                DialogPesan.newInstance("Daftar Gagal", "ID Pegawai Sudah Terdaftar")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }
            JumlahCuti jumlahCuti = new JumlahCuti(pegawai.getId());
            viewModel.tambahJumlahCuti(jumlahCuti);
            setResult(Activity.RESULT_OK);
            finish();

        });



    }
}

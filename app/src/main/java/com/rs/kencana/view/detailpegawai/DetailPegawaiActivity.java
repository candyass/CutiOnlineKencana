package com.rs.kencana.view.detailpegawai;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.rs.kencana.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailPegawaiActivity extends AppCompatActivity {

    private TextView textId;
    private TextView textNama;
    private TextView textAlamat;
    private TextView textEmail;
    private TextView textJenisKelamin;
    private TextView textNoHp;
    private TextView textPosisi;
    private TextView textSisaCuti;
    private CircleImageView photo;

    private DetailPegawaiActivityViewModel viewModel;
    private static final String EXTRA_ID = "com.rs.kencana.detailpegawaiactivity.extra.id";

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, DetailPegawaiActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profil);

        textId = findViewById(R.id.fragment_profil_id_text);
        textNama = findViewById(R.id.fragment_profil_nama_text);
        textAlamat = findViewById(R.id.fragment_profil_alamat_text);
        textEmail = findViewById(R.id.fragment_profil_email_text);
        textJenisKelamin = findViewById(R.id.fragment_profil_jenis_kelamin_text);
        textNoHp = findViewById(R.id.fragment_profil_no_hp_text);
        textPosisi = findViewById(R.id.fragment_profil_posisi_text);
        photo = findViewById(R.id.fragment_profil_photo);
        textSisaCuti = findViewById(R.id.fragment_profil_sisa_cuti_text);

        String idPegawai = getIntent().getStringExtra(EXTRA_ID);

        viewModel = ViewModelProviders.of(this).get(DetailPegawaiActivityViewModel.class);
        viewModel.getProfilPegawai(idPegawai).observe(this, profil -> {
            textId.setText(profil.getId());
            textNama.setText(profil.getNama());
            textJenisKelamin.setText(profil.getJenisKelamin());
            textEmail.setText(profil.getEmail());
            textAlamat.setText(profil.getAlamat());
            textNoHp.setText(profil.getNoHP());
            textPosisi.setText(profil.getDeskripsi());
            textSisaCuti.setText(String.valueOf(profil.getJumlahCuti()) + " Pengajuan");
            if(profil.getJenisKelamin().equalsIgnoreCase("perempuan")) {
                photo.setImageDrawable(getResources().getDrawable(R.drawable.icon_wanita));
            }
        });
    }
}

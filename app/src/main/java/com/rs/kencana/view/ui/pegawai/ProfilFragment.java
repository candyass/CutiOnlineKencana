package com.rs.kencana.view.ui.pegawai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.rs.kencana.R;
import com.rs.kencana.view.MainActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilFragment extends Fragment {


    private TextView textId;
    private TextView textNama;
    private TextView textAlamat;
    private TextView textEmail;
    private TextView textJenisKelamin;
    private TextView textNoHp;
    private TextView textPosisi;
    private TextView textSisaCuti;
    private CircleImageView photo;

    private ProfilFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        textId = view.findViewById(R.id.fragment_profil_id_text);
        textNama = view.findViewById(R.id.fragment_profil_nama_text);
        textAlamat = view.findViewById(R.id.fragment_profil_alamat_text);
        textEmail = view.findViewById(R.id.fragment_profil_email_text);
        textJenisKelamin = view.findViewById(R.id.fragment_profil_jenis_kelamin_text);
        textNoHp = view.findViewById(R.id.fragment_profil_no_hp_text);
        textPosisi = view.findViewById(R.id.fragment_profil_posisi_text);
        photo = view.findViewById(R.id.fragment_profil_photo);
        textSisaCuti = view.findViewById(R.id.fragment_profil_sisa_cuti_text);

        viewModel = ViewModelProviders.of(this).get(ProfilFragmentViewModel.class);

        MainActivity activity = (MainActivity) getActivity();
        String pegawaiId = activity.getIdPegawai();
        viewModel.getProfilPegawai(pegawaiId).observe(this, profil -> {
            textId.setText(profil.getId());
            textNama.setText(profil.getNama());
            textJenisKelamin.setText(profil.getJenisKelamin());
            textEmail.setText(profil.getEmail());
            textAlamat.setText(profil.getAlamat());
            textNoHp.setText(profil.getNoHP());
            textPosisi.setText(profil.getDeskripsi());
            if(profil.getJenisKelamin().equalsIgnoreCase("perempuan")) {
                photo.setImageDrawable(getResources().getDrawable(R.drawable.icon_wanita));
            }
        });

        viewModel.getJumlahCuti(pegawaiId).observe(this, jumlahCuti -> {
            textSisaCuti.setText(String.valueOf(jumlahCuti.getJumlahCuti()));
        });


        return view;
    }
}

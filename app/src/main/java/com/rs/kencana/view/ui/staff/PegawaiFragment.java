package com.rs.kencana.view.ui.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rs.kencana.R;
import com.rs.kencana.database.model.PegawaiProfil;
import com.rs.kencana.view.detailpegawai.DetailPegawaiActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

public class PegawaiFragment extends Fragment {

    private ViewSwitcher viewSwitcher;
    private TextView textTitle;
    private TextView textHeader;
    private RecyclerView recyclerView;
    private PegawaiFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_list, container, false);
        viewSwitcher = view.findViewById(R.id.content_list_switcher);
        textTitle  = view.findViewById(R.id.content_list_text);
        textHeader = view.findViewById(R.id.content_list_header);
        recyclerView = view.findViewById(R.id.content_list_stub);

        textTitle.setText("Daftar Pegawai Belum Tersedia");
        textHeader.setText("Daftar Pegawai");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = ViewModelProviders.of(this).get(PegawaiFragmentViewModel.class);

        viewModel.getAllPegawaiProfil(false).observe(this, list -> {
            if(list != null) {
                if(list.size() > 0) {
                    recyclerView.setAdapter(new PegawaiAdapter(list));
                    tampilkanList(true);
                }
            }
        });

        return view;
    }


    private void tampilkanList(boolean value) {
        if(value) {
            if(viewSwitcher.getNextView().getId() == R.id.content_list_item) {
                viewSwitcher.showNext();
            }
        }
    }

    class PegawaiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView foto;
        private TextView textNama;
        private TextView textId;
        private TextView textJenisKelamin;
        private TextView textPosisi;
        private TextView textJumlahCuti;

        private PegawaiProfil profil;

        public PegawaiHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.list_pegawai_foto);
            textNama = itemView.findViewById(R.id.list_pegawai_nama_text);
            textId = itemView.findViewById(R.id.list_pegawai_id_text);
            textJenisKelamin = itemView.findViewById(R.id.list_pegawai_jenis_kelamin_text);
            textPosisi = itemView.findViewById(R.id.list_pegawai_posisi_text);
            textJumlahCuti = itemView.findViewById(R.id.list_pegawai_jumlah_cuti_text);
            itemView.setOnClickListener(this);
        }

        public void bindItem(PegawaiProfil profil) {
            this.profil = profil;
            if(this.profil.getJenisKelamin().equalsIgnoreCase("perempuan")) {
                foto.setImageResource(R.drawable.icon_wanita);
            }
            textNama.setText(this.profil.getNama());
            textId.setText(this.profil.getId());
            textPosisi.setText(this.profil.getDeskripsi());
            textJenisKelamin.setText(profil.getJenisKelamin());
            textJumlahCuti.setText(String.valueOf(profil.getJumlahCuti()) + " Pengajuan");
        }

        @Override
        public void onClick(View v) {
            Intent intent = DetailPegawaiActivity.newIntent(getContext(), profil.getId());
            startActivity(intent);
        }
    }

    class PegawaiAdapter extends  RecyclerView.Adapter<PegawaiHolder> {

        private List<PegawaiProfil> list;

        public PegawaiAdapter(List<PegawaiProfil> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public PegawaiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = getLayoutInflater().inflate(R.layout.list_pegawai, parent, false);
            return new PegawaiHolder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull PegawaiHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

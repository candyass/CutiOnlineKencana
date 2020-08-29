package com.rs.kencana.view.ui.pegawai;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rs.kencana.R;
import com.rs.kencana.database.model.QueryCuti;
import com.rs.kencana.util.MyLogger;
import com.rs.kencana.view.MainActivity;
import com.rs.kencana.view.dialog.DialogPesan;
import com.rs.kencana.view.pengajuan.PengajuanCutiActivity;

import java.text.DateFormat;
import java.util.List;

public class PengajuanCutiFragment extends Fragment {

    private ViewSwitcher viewSwitcher;
    private TextView textTitle;
    private TextView textHeader;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private int totalCuti = -1;

    private PengajuanCutiFragmentViewModel viewModel;
    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengajuan_cuti, container, false);

        viewSwitcher = view.findViewById(R.id.content_list_switcher);
        textTitle  = view.findViewById(R.id.content_list_text);
        textHeader = view.findViewById(R.id.content_list_header);
        recyclerView = view.findViewById(R.id.content_list_stub);
        fab = view.findViewById(R.id.fragment_pengajuan_cuti_fab);

        viewModel = ViewModelProviders.of(this).get(PengajuanCutiFragmentViewModel.class);


        textTitle.setText("Belum ada pengajuan cuti");
        textHeader.setText("Daftar Pengajuan");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity activity = (MainActivity) getActivity();
        viewModel.getQueryCutiPegawaiDiproses(activity.getIdPegawai()).observe(this, query -> {
            if(query != null) {
                if(query.size() > 0) {
                    recyclerView.setAdapter(new CutiAdapter(query));
                    tampilkanList(true);
                }
            }
        });

        fab.setOnClickListener(v -> {
            MyLogger.logPesan("Total Cuti : " + totalCuti);
            if(totalCuti > 0) {
                Intent intent = PengajuanCutiActivity.newIntent(getContext(), activity.getIdPegawai());
                startActivity(intent);
            }else {
                DialogPesan.newInstance("Pengajuan Gagal", "Sisa Cuti Anda Habis")
                        .show(getFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
            }
        });

        viewModel.getJumlahCuti(activity.getIdPegawai()).observe(this, jumlahCuti -> {
            totalCuti = jumlahCuti.getJumlahCuti();
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

    class CutiHolder extends RecyclerView.ViewHolder {

        private TextView textNama;
        private TextView textTanggalCutiText;
        private TextView textTanggalAkhirCutiText;
        private TextView textKeterangan;
        private TextView textStatusKeterangan;
        private ImageView ttdPegawai;
        private ImageView ttdAdmin;
        private TextView textTTDPegawai;
        private TextView textTTDAdmin;

        public CutiHolder(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.list_pengajuan_cuti_nama_text);
            textTanggalCutiText = itemView.findViewById(R.id.list_pengajuan_cuti_tanggal_cuti_text);
            textTanggalAkhirCutiText = itemView.findViewById(R.id.list_pengajuan_cuti_tanggal_akhir_cuti_text);
            textKeterangan = itemView.findViewById(R.id.list_pengajuan_cuti_keterangan_text);
            textStatusKeterangan = itemView.findViewById(R.id.list_pengajuan_cuti_status_text);
            ttdPegawai = itemView.findViewById(R.id.list_pengajuan_cuti_ttd_pegawai);
            ttdAdmin = itemView.findViewById(R.id.list_pengajuan_cuti_ttd_admin);
            textTTDPegawai = itemView.findViewById(R.id.list_pengajuan_cuti_ttd_pegawai_text);
            textTTDAdmin = itemView.findViewById(R.id.list_pengajuan_cuti_ttd_admin_text);
        }

        public void bindItem(QueryCuti query) {
            textNama.setText(query.getNama());
            textTanggalCutiText.setText(dateFormat.format(query.getTanggalCuti()));
            textTanggalAkhirCutiText.setText(dateFormat.format(query.getTanggalAkhirCuti()));
            textKeterangan.setText(query.getKeteranganCuti());
            textStatusKeterangan.setText(query.getStatusKeterangan());
            if(query.getTtdPegawai() != null) {
                ttdPegawai.setImageBitmap(query.getTtdPegawai());
            }else {
                ttdPegawai.setVisibility(View.GONE);
                textTTDPegawai.setVisibility(View.GONE);
            }
            if(query.getTtdAdmin() != null) {
                ttdAdmin.setImageBitmap(query.getTtdAdmin());
            }else {
                ttdAdmin.setVisibility(View.GONE);
                textTTDAdmin.setVisibility(View.GONE);
            }
        }
    }

    class CutiAdapter extends RecyclerView.Adapter<CutiHolder> {

        private List<QueryCuti> list;

        public CutiAdapter(List<QueryCuti> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public CutiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = getLayoutInflater().inflate(R.layout.list_pengajuan_cuti, parent, false);
            return new CutiHolder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull CutiHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

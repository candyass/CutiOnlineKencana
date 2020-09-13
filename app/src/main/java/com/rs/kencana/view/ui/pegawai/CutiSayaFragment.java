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
import com.rs.kencana.R;
import com.rs.kencana.database.model.QueryCuti;
import com.rs.kencana.util.MyLogger;
import com.rs.kencana.view.MainActivity;
import com.rs.kencana.view.detailcuti.DetailCutiActivity;

import java.text.DateFormat;
import java.util.List;

public class CutiSayaFragment extends Fragment {

    private ViewSwitcher viewSwitcher;
    private TextView textTitle;
    private TextView textHeader;
    private RecyclerView recyclerView;
    private TextView textJumlahCuti;

    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    private CutiSayaFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuti_saya, container, false);


        viewSwitcher = view.findViewById(R.id.fragment_cuti_saya_switcher);
        textTitle  = view.findViewById(R.id.fragment_cuti_saya_text);
        textHeader = view.findViewById(R.id.fragment_cuti_saya_header);
        recyclerView = view.findViewById(R.id.fragment_cuti_saya_stub);
        textJumlahCuti = view.findViewById(R.id.fragment_cuti_saya_jumlah_cuti);

        textTitle.setText("Cuti Belum Tersedia");
        textHeader.setText("Cuti Saya");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = ViewModelProviders.of(this).get(CutiSayaFragmentViewModel.class);

        MainActivity activity = (MainActivity) getActivity();
        viewModel.getQueryCutiPegawaiTerproses(activity.getIdPegawai()).observe(this, query -> {
            if(query != null) {
                if(query.size() > 0) {
                    recyclerView.setAdapter(new CutiAdapter(query));
                    tampilkanList(true);
                }
            }
        });

        viewModel.getJumlahCuti(activity.getIdPegawai()).observe(this, jumlahCuti -> {
            textJumlahCuti.setText("Sisa Cuti " + jumlahCuti.getJumlahCuti());
        });

        return view;
    }

    private void tampilkanList(boolean value) {
        if(value) {
            if(viewSwitcher.getNextView().getId() == R.id.fragment_cuti_saya_item) {
                viewSwitcher.showNext();
            }
        }
    }




    class CutiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textNama;
        private TextView textTanggalCutiText;
        private TextView textTanggalAkhirCutiText;
        private TextView textKeterangan;
        private TextView textStatusKeterangan;
        private ImageView ttdPegawai;
        private ImageView ttdAdmin;
        private TextView textTTDPegawai;
        private TextView textTTDAdmin;

        private QueryCuti queryCuti;

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
            itemView.setOnClickListener(this);
        }

        public void bindItem(QueryCuti query) {
            queryCuti = query;
            textNama.setText(queryCuti.getNama());
            textTanggalCutiText.setText(dateFormat.format(queryCuti.getTanggalCuti()));
            textTanggalAkhirCutiText.setText(dateFormat.format(queryCuti.getTanggalAkhirCuti()));
            textKeterangan.setText(queryCuti.getKeteranganCuti());
            textStatusKeterangan.setText(queryCuti.getStatusKeterangan());
            if(query.getTtdPegawai() != null) {
                ttdPegawai.setImageBitmap(queryCuti.getTtdPegawai());
            }else {
                ttdPegawai.setVisibility(View.GONE);
                textTTDPegawai.setVisibility(View.GONE);
            }
            if(query.getTtdAdmin() != null) {
                ttdAdmin.setImageBitmap(queryCuti.getTtdAdmin());
            }else {
                ttdAdmin.setVisibility(View.GONE);
                textTTDAdmin.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            if(queryCuti.getStatus() == 1) {
                Intent intent = DetailCutiActivity.newIntent(getContext(), queryCuti.getIdCuti());
                startActivity(intent);
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


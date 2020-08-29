package com.rs.kencana.view.pengajuan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import com.rs.kencana.R;
import com.rs.kencana.database.model.Cuti;
import com.rs.kencana.event.TanggalEvent;
import com.rs.kencana.view.dialog.DialogPesan;
import com.rs.kencana.view.dialog.TanggalDialog;
import com.rs.kencana.view.tandatangan.TandaTanganActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.util.Date;

public class PengajuanCutiActivity extends AppCompatActivity  {

    private TextView textId;
    private TextView textTanggalDari;
    private TextView textTanggalKe;
    private EditText editKeterangan;
    private Button btnAjukan;

    private Date tanggalDari;
    private Date tanggalKe;
    private String idPegawai;

    private PengajuanCutiActivityViewModel viewModel;

    private String DIALOG_TTD_TAG = "com.rs.kencana.tangatangandialog.tag";
    private static String EXTRA_ID = "com.rs.kencana.pengaduancutiactivity.extra.id";
    private static final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
    private static final int REQUEST_TTD = 200;


    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, PengajuanCutiActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_cuti);

        textId = findViewById(R.id.activity_pengajuan_id_text);
        textTanggalDari = findViewById(R.id.activity_pengajuan_tanggal_dari_text);
        textTanggalKe = findViewById(R.id.activity_pengajuan_tanggal_ke_text);
        editKeterangan = findViewById(R.id.activity_pengajuan_keterangan_editText);
        btnAjukan = findViewById(R.id.activity_pengajuan_btn_ajukan);

        idPegawai = getIntent().getStringExtra(EXTRA_ID);
        textId.setText(idPegawai);
        viewModel = ViewModelProviders.of(this).get(PengajuanCutiActivityViewModel.class);


        btnAjukan.setOnClickListener(v -> {

            if(tanggalKe == null || tanggalDari == null) {
                DialogPesan.newInstance("Pengajuan Gagal", "Tanggal Cuti Belum Diisi")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(tanggalDari.before(new Date())) {
                DialogPesan.newInstance("Pengajuan Gagal", "Tanggal Cuti Tidak Valid")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(tanggalKe.before(tanggalDari)) {
                DialogPesan.newInstance("Pengajuan Gagal", "Tanggal Cuti Tidak Valid")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(editKeterangan.getText().toString().isEmpty()) {
                DialogPesan.newInstance("Pengajuan Gagal", "Harap Berikan Alasan Cuti Anda")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }



            Intent intent = TandaTanganActivity.newIntent(getBaseContext());
            startActivityForResult(intent, REQUEST_TTD);
        });

        textTanggalKe.setOnClickListener(v -> {
            DialogFragment dialog = TanggalDialog.newInstance(2);
            dialog.show(getSupportFragmentManager(), TanggalDialog.DIALOG_TAG);
        });

        textTanggalDari.setOnClickListener(v -> {
            DialogFragment dialog = TanggalDialog.newInstance(1);
            dialog.show(getSupportFragmentManager(), TanggalDialog.DIALOG_TAG);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TanggalEvent event) {
        if(event.getPosisi() == 1) {
            textTanggalDari.setText(format.format(event.getDate()));
            tanggalDari = event.getDate();
        }else if(event.getPosisi() == 2) {
            textTanggalKe.setText(format.format(event.getDate()));
            tanggalKe = event.getDate();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_TTD && resultCode == Activity.RESULT_OK) {
            byte[] byteArray = data.getByteArrayExtra(TandaTanganActivity.EXTRA_TTD);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Cuti cuti = new Cuti(idPegawai, tanggalDari, tanggalKe, editKeterangan.getText().toString(), bmp);
            cuti.setStatus(Cuti.DIPROSES);
            viewModel.simpanCuti(cuti);
            Toast.makeText(getBaseContext(), "Tanda Tangan Sukses", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

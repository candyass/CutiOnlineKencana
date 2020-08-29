package com.rs.kencana.view.tandatanganadmin;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProviders;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.rs.kencana.R;

public class TandaTanganAdminActivity extends AppCompatActivity {


    private static final String CHANNEL_ID = "com.rs.kencana.channel.notif1";
    private static final String CHANNEL_NAME = "channel_01";
    private static final int NOTIF_ID = 100;
    private SignaturePad mSignaturePad;
    private Button mHapusButton;
    private Button mSimpanButton;

    private TandaTanganAdminActivityViewModel viewModel;
    private long cutiId;
    private String idPegawai;
    private String namaPegawai;


    private static String EXTRA_ID = "com.rs.kencana.tandatanganadminactivity.extra.id";
    private static String EXTRA_ID_PEGAWAI = "com.rs.kencana.tandatanganadminactivity.extra.idpegawai";
    private static String EXTRA_NAMA = "com.rs.kencana.tandatanganadminactivity.extra.namapegawai";


    public static Intent newIntent(Context context, long id, String idPegawai, String nama) {
        Intent intent = new Intent(context, TandaTanganAdminActivity.class);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_ID_PEGAWAI, idPegawai);
        intent.putExtra(EXTRA_NAMA, nama);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tanda_tangan);


        mSignaturePad = findViewById(R.id.dialog_tanda_tangan_pad);
        mHapusButton = findViewById(R.id.dialog_tanda_tangan_hapus_button);
        mSimpanButton = findViewById(R.id.dialog_tanda_tangan_simpan_button);

        mSimpanButton.setText("Terima");
        mHapusButton.setText("Tolak");

        cutiId = getIntent().getLongExtra(EXTRA_ID, -1);
        idPegawai = getIntent().getStringExtra(EXTRA_ID_PEGAWAI);
        namaPegawai = getIntent().getStringExtra(EXTRA_NAMA);


        viewModel = ViewModelProviders.of(this).get(TandaTanganAdminActivityViewModel.class);

        mSignaturePad.setPenColor(R.color.colorPrimary);
        mHapusButton.setEnabled(false);
        mSimpanButton.setEnabled(false);

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                mHapusButton.setEnabled(true);
                mSimpanButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mHapusButton.setEnabled(false);
                mSimpanButton.setEnabled(false);
            }
        });

        mSimpanButton.setOnClickListener(v -> {
            Bitmap bmp = mSignaturePad.getSignatureBitmap();
            viewModel.konfirmasiCuti(cutiId, bmp, 1, "Diterima");
            viewModel.approveCuti(idPegawai);
            generateNotification("Cuti Diterima", "Pengajuan Cuti " + namaPegawai + " Diterima");
            finish();
        });

        mHapusButton.setOnClickListener(v -> {
            Bitmap bmp = mSignaturePad.getSignatureBitmap();
            viewModel.konfirmasiCuti(cutiId, bmp, 2, "Ditolak");
            generateNotification("Cuti Ditolak", "Pengajuan Cuti " + namaPegawai + " Ditolak");
            finish();
        });


    }

    private  void generateNotification(String judul, String deskripsi) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(judul)
                .setContentText(deskripsi)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        createNotificationChannel();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIF_ID, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String description = "nothing";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

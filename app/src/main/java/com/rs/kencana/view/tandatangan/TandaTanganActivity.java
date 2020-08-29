package com.rs.kencana.view.tandatangan;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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

import java.io.ByteArrayOutputStream;

public class TandaTanganActivity extends AppCompatActivity {



    private static final String CHANNEL_ID = "com.rs.kencana.channel.notif2";
    private static final String CHANNEL_NAME = "channel_02";
    private static final int NOTIF_ID = 101;

    private SignaturePad mSignaturePad;
    private Button mHapusButton;
    private Button mSimpanButton;

    private TandaTanganActivityViewModel mViewModel;

    public static String EXTRA_TTD = "com.rs.kencana.tandatanganactivity.extra.ttd";


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TandaTanganActivity.class);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tanda_tangan);
        mViewModel = ViewModelProviders.of(this).get(TandaTanganActivityViewModel.class);

        mSignaturePad = findViewById(R.id.dialog_tanda_tangan_pad);
        mHapusButton = findViewById(R.id.dialog_tanda_tangan_hapus_button);
        mSimpanButton = findViewById(R.id.dialog_tanda_tangan_simpan_button);

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
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent intent = getIntent().putExtra(EXTRA_TTD, byteArray);

            setResult(Activity.RESULT_OK, intent);
            generateNotification("Pengajuan Cuti Masuk", "Pengajuan Baru Diterima" );
            finish();
        });

        mHapusButton.setOnClickListener(v -> {
            mSignaturePad.clear();
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

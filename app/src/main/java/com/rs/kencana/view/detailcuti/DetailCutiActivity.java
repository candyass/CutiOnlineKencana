package com.rs.kencana.view.detailcuti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import com.rs.kencana.R;
import com.rs.kencana.util.MyLogger;

import java.io.*;
import java.text.DateFormat;

public class DetailCutiActivity extends AppCompatActivity {


    private TextView textNama;
    private TextView textTanggalCutiText;
    private TextView textTanggalAkhirCutiText;
    private TextView textKeterangan;
    private ImageView ttdPegawai;
    private ImageView ttdAdmin;
    private TextView textTTDPegawai;
    private TextView textTTDAdmin;
    private Button cetakButton;
    private ConstraintLayout root;

    private DetailCutiActivityViewModel viewModel;
    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    private static final String EXTRA_ID_CUTI = "com.rs.kencana.detailcutiactivity.extra.id";

    public static Intent newIntent(Context context, long idCuti) {
        Intent intent = new Intent(context, DetailCutiActivity.class);
        intent.putExtra(EXTRA_ID_CUTI, idCuti);
        return intent;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cuti);

        viewModel = ViewModelProviders.of(this).get(DetailCutiActivityViewModel.class);

        root = findViewById(R.id.detail_cuti_root);
        textNama = findViewById(R.id.detail_cuti_list_pengajuan_cuti_nama_text);
        textTanggalCutiText = findViewById(R.id.detail_cuti_list_pengajuan_cuti_tanggal_cuti_text);
        textTanggalAkhirCutiText = findViewById(R.id.detail_cuti_list_pengajuan_cuti_tanggal_akhir_cuti_text);
        textKeterangan = findViewById(R.id.detail_cuti_list_pengajuan_cuti_keterangan_text);
        ttdPegawai = findViewById(R.id.detail_cuti_list_pengajuan_cuti_ttd_pegawai);
        ttdAdmin = findViewById(R.id.detail_cuti_list_pengajuan_cuti_ttd_admin);
        textTTDPegawai = findViewById(R.id.detail_cuti_list_pengajuan_cuti_ttd_pegawai_text);
        textTTDAdmin = findViewById(R.id.detail_cuti_list_pengajuan_cuti_ttd_admin_text);
        cetakButton = findViewById(R.id.detail_cuti_cetak_btn);


        long idCuti = getIntent().getLongExtra(EXTRA_ID_CUTI, -1);

        if(idCuti != -1) {
            viewModel.getDetailQueryCutiPegawai(idCuti).observe(this, query -> {
                textNama.setText(query.getNama());
                textTanggalCutiText.setText(dateFormat.format(query.getTanggalCuti()));
                textTanggalAkhirCutiText.setText(dateFormat.format(query.getTanggalAkhirCuti()));
                textKeterangan.setText(query.getKeteranganCuti());
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
            });
        }

        cetakButton.setOnClickListener(v -> {
            File file = generatePdf(root, getBaseContext());
            if(file == null) {
                MyLogger.logPesan("File Is Null");
            }else {
                MyLogger.logPesan("File Is Not Null");
                Toast.makeText(getBaseContext(), "tersimpan di " + file.getName(), Toast.LENGTH_SHORT).show();
            }
            finish();
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static File generate(View view, Context context)  {
        PrintAttributes.Builder printAttrsBuilder = new PrintAttributes.Builder();
        printAttrsBuilder.setMediaSize(PrintAttributes.MediaSize.ISO_A4);
        printAttrsBuilder.setMinMargins(new PrintAttributes.Margins(5, 5, 5, 5));

        PrintedPdfDocument document = new PrintedPdfDocument(context, printAttrsBuilder.build());

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(150, 150, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        view.draw(page.getCanvas());
        document.finishPage(page);
        File result = null;
        try {
            result = File.createTempFile("contoh", ".pdf", context.getFilesDir());
            document.writeTo(new BufferedOutputStream(new FileOutputStream(result)));
        } catch (FileNotFoundException e) {
            MyLogger.logPesan(e.getMessage());
        } catch (IOException e) {
            MyLogger.logPesan(e.getMessage());
        }finally {
            document.close();
        }
        MyLogger.logPesan("File Berhasil Dibuat di " + result.getAbsolutePath());
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static File generatePdf(View view, Context context) {
        PrintAttributes printAttrs = new PrintAttributes.Builder().
                setColorMode(PrintAttributes.COLOR_MODE_COLOR).
                setMediaSize(PrintAttributes.MediaSize.ISO_A4).
//                setResolution(new PrintAttributes.Resolution("zooey", PRINT_SERVICE, 450, 700)).
                setMinMargins(PrintAttributes.Margins.NO_MARGINS).
                build();

        PdfDocument document = new PrintedPdfDocument(context, printAttrs);
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(view.getWidth(), view.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        if (page != null) {
            MyLogger.logPesan("Page Is Not Null");
            view.layout(0, 0, view.getWidth(),
                    view.getHeight());
            view.draw(page.getCanvas());
            // Move the canvas for the next view.
            page.getCanvas().translate(0, view.getHeight());
        }else {
            MyLogger.logPesan("Page Is Null");
        }


        document.finishPage(page);

        File result = null;
        try {
            result = File.createTempFile("contoh", ".pdf", context.getFilesDir());
            document.writeTo(new BufferedOutputStream(new FileOutputStream(result)));
        } catch (FileNotFoundException e) {
            MyLogger.logPesan(e.getMessage());
        } catch (IOException e) {
            MyLogger.logPesan(e.getMessage());
        }finally {
            document.close();
        }
        MyLogger.logPesan("File Berhasil Dibuat di " + result.getAbsolutePath());
        return result;
    }
}





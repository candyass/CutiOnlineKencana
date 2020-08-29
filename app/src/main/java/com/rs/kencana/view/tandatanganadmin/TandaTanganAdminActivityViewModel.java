package com.rs.kencana.view.tandatanganadmin;

import android.app.Application;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.rs.kencana.MyApplication;

public class TandaTanganAdminActivityViewModel extends AndroidViewModel {
    public TandaTanganAdminActivityViewModel(@NonNull Application application) {
        super(application);
    }

    void konfirmasiCuti(long id, Bitmap ttd, int status, String statusKeterangan) {
        MyApplication app = (MyApplication) getApplication();
        app.konfirmasiCuti(id, ttd, status, statusKeterangan);
    }

    void approveCuti(String idPegawai) {
        MyApplication app = (MyApplication) getApplication();
        app.approveCuti(idPegawai);
    }
}

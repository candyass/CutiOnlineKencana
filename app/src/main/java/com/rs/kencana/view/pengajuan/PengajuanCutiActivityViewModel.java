package com.rs.kencana.view.pengajuan;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.Cuti;

public class PengajuanCutiActivityViewModel extends AndroidViewModel {

    public PengajuanCutiActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void simpanCuti(Cuti cuti) {
        MyApplication app = (MyApplication) getApplication();
        app.simpanCuti(cuti);
    }
}

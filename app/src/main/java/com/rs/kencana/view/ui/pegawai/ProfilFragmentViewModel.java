package com.rs.kencana.view.ui.pegawai;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.PegawaiProfil;

public class ProfilFragmentViewModel extends AndroidViewModel {

    public ProfilFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PegawaiProfil> getProfilPegawai(String id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getProfilPegawai(id);
    }

    public LiveData<JumlahCuti> getJumlahCuti(String pegawaiId) {
        MyApplication app = (MyApplication) getApplication();
        return app.getJumlahCuti(pegawaiId);
    }
}

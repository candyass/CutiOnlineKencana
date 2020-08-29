package com.rs.kencana.view.ui.pegawai;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.QueryCuti;

import java.util.List;

public class CutiSayaFragmentViewModel extends AndroidViewModel {

    public CutiSayaFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<QueryCuti>> getQueryCutiPegawaiTerproses(String id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getQueryCutiPegawaiTerproses(id);
    }

    public LiveData<JumlahCuti> getJumlahCuti(String pegawaiId) {
        MyApplication app = (MyApplication) getApplication();
        return app.getJumlahCuti(pegawaiId);
    }
}

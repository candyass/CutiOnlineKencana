package com.rs.kencana.view.ui.pegawai;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.QueryCuti;

import java.util.List;

public class PengajuanCutiFragmentViewModel extends AndroidViewModel {

    public PengajuanCutiFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<QueryCuti>> getQueryCutiPegawaiDiproses(String id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getQueryCutiPegawaiDiproses(id);
    }

    public LiveData<JumlahCuti> getJumlahCuti(String idPegwai) {
        MyApplication app = (MyApplication) getApplication();
        return app.getJumlahCuti(idPegwai);
    }


}

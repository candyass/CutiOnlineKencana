package com.rs.kencana.view.ui.staff;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.PegawaiProfil;

import java.util.List;

public class PegawaiFragmentViewModel extends AndroidViewModel {

    public PegawaiFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<PegawaiProfil>> getAllPegawaiProfil(boolean value) {
        MyApplication app = (MyApplication) getApplication();
        return app.getAllPegawaiProfil(value);
    }
}

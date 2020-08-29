package com.rs.kencana.view.login;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.Pegawai;

public class LoginActivityViewModel extends AndroidViewModel {

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public Pegawai getPegawai(String id, String password) {
        MyApplication app = (MyApplication) getApplication();
        return app.getPegawai(id, password);
    }
}

package com.rs.kencana.view.detailcuti;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.QueryCuti;

public class DetailCutiActivityViewModel extends AndroidViewModel {

    public DetailCutiActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<QueryCuti> getDetailQueryCutiPegawai(long idCuti) {
        MyApplication app =  getApplication();
        return app.getDetailQueryCutiPegawai(idCuti);
    }
}

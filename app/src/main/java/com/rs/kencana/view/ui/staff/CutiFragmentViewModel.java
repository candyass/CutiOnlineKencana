package com.rs.kencana.view.ui.staff;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.QueryCuti;

import java.util.List;

public class CutiFragmentViewModel extends AndroidViewModel {

    public CutiFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<QueryCuti>> getAllQueryCuti() {
        MyApplication app = (MyApplication) getApplication();
        return app.getAllQueryCuti();
    }
}

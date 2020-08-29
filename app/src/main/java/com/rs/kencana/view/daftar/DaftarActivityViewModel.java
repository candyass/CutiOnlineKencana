package com.rs.kencana.view.daftar;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rs.kencana.MyApplication;
import com.rs.kencana.database.model.Jabatan;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.Pegawai;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DaftarActivityViewModel extends AndroidViewModel {

    public DaftarActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Jabatan>> getAllJabatan() {
        MyApplication app = (MyApplication) getApplication();
        return app.getAllJabatan();
    }

    public int daftarPegawai(Pegawai pegawai) {
        MyApplication app = (MyApplication) getApplication();
        try {
            return app.daftarPegawai(pegawai);
        } catch (ExecutionException e) {
            return -1;
        } catch (InterruptedException e) {
            return -1;
        }
    }

    public void tambahJumlahCuti(JumlahCuti jumlahCuti) {
        MyApplication app = (MyApplication) getApplication();
        app.tambahJumlahCuti(jumlahCuti);
    }
}

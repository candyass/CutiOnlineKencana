package com.rs.kencana;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.rs.kencana.database.LocalDatabase;
import com.rs.kencana.database.dao.JabatanDao;
import com.rs.kencana.database.model.*;
import com.rs.kencana.util.DataUtil;
import com.rs.kencana.util.MyLogger;

import java.util.List;
import java.util.concurrent.*;

public class MyApplication extends Application {

    private LocalDatabase database;
    private ExecutorService executor;

    private static final String PREF_NAME = "com.rs.kencana.mypref";
    private static final String KEY_SETUP_DATA = "com.rs.kencana.key.setupdata";
    private static final String DATABASE_NAME = "cutidb";

    @Override
    public void onCreate() {
        super.onCreate();
        executor = Executors.newSingleThreadExecutor();
        database = Room.databaseBuilder(this, LocalDatabase.class, DATABASE_NAME).build();
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isDataSetup =  preferences.getBoolean(KEY_SETUP_DATA, false);
        if(!isDataSetup) {
            executor.execute(() -> database.getJabatanDao().tambahJabatan(DataUtil.getAllJabatan()));
            executor.execute(() -> database.getPegawaiDao().tambahPegawai(DataUtil.getAksesCutiPegawai()));
            executor.execute(() -> database.getPegawaiDao().insertJumlahCuti(DataUtil.getJumlahCuti()));
            preferences.edit().putBoolean(KEY_SETUP_DATA, true).commit();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        database = null;
        executor.shutdown();
    }


    public Pegawai getPegawai(String id, String password)  {
        Pegawai pegawai = null;
        Callable<Pegawai> callable = new Callable<Pegawai>() {
            @Override
            public Pegawai call() throws Exception {
                return database.getPegawaiDao().getPegawaiId(id, password);
            }
        };
        try {
            pegawai = executor.submit(callable).get();
        }catch (Exception e) {
            MyLogger.logPesan("ID Tidak Ditemukan");
        }
        return pegawai;
    }

    public LiveData<List<Jabatan>> getAllJabatan() {
        return database.getJabatanDao().getAllJabatan();
    }

    public int daftarPegawai(Pegawai pegawai) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            try {
                database.getPegawaiDao().daftarPegawai(pegawai);
                return 1;
            }catch (Exception e) {
                return -1;
            }
        };
        Future<Integer> future = executor.submit(callable);
        return future.get();
    }

    public LiveData<PegawaiProfil> getProfilPegawai(String id) {
        return database.getPegawaiDao().getProfilPegawai(id);
    }

    public void simpanCuti(Cuti cuti) {
        executor.execute(() -> {
            database.getCutiDao().simpanCuti(cuti);
        });
    }

    public LiveData<List<QueryCuti>> getQueryCutiPegawaiTerproses(String id) {
        return database.getCutiDao().getQueryCutiPegawaiTerproses(id);
    }

    public LiveData<List<QueryCuti>> getAllQueryCuti() {
        return database.getCutiDao().getAllQueryCuti();
    }

    public LiveData<List<QueryCuti>> getQueryCutiPegawaiDiproses(String id) {
        return database.getCutiDao().getQueryCutiPegawaiDiproses(id);
    }

    public void konfirmasiCuti(long id, Bitmap ttd, int status, String statusKeterangan) {
        executor.execute(() -> {
            database.getCutiDao().konfirmasiCuti(id, ttd, status, statusKeterangan, true);
        });
    }

    public LiveData<List<PegawaiProfil>> getAllPegawaiProfil(boolean value) {
        return database.getPegawaiDao().getAllPegawaiProfil(value);
    }

    public void tambahJumlahCuti(JumlahCuti jumlahCuti) {
        executor.execute(() -> {
            database.getPegawaiDao().tambahJumlahCuti(jumlahCuti);
        });
    }

    public LiveData<JumlahCuti> getJumlahCuti(String pegawaiId) {
        return database.getPegawaiDao().getJumlahCuti(pegawaiId);
    }

    public void approveCuti(String pegawaiId) {
        executor.execute(() -> {
            database.getCutiDao().approveCuti(pegawaiId);
        });
    }

}

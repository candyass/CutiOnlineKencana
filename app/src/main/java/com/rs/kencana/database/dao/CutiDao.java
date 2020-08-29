package com.rs.kencana.database.dao;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.rs.kencana.database.model.Cuti;
import com.rs.kencana.database.model.QueryCuti;

import java.util.List;

@Dao
public interface CutiDao {

    @Insert
    void simpanCuti(Cuti cuti);

    @Query("SELECT c.idCuti, c.tanggalCuti, c.tanggalAkhirCuti, c.keteranganCuti, c.diProses, c.idPegawai, " +
            "c.ttdPegawai, c.ttdAdmin, c.status, c.statusKeterangan, p.nama " +
            "FROM Cuti c JOIN Pegawai p ON c.idPegawai = p.id WHERE p.id =:id AND status <> 0")
    LiveData<List<QueryCuti>> getQueryCutiPegawaiTerproses(String id);


    @Query("SELECT c.idCuti, c.tanggalCuti, c.tanggalAkhirCuti, c.keteranganCuti, c.diProses, c.idPegawai, " +
            "c.ttdPegawai, c.ttdAdmin, c.status, c.statusKeterangan, p.nama " +
            "FROM Cuti c JOIN Pegawai p ON c.idPegawai = p.id ORDER BY p.nama, tanggalCuti DESC")
    LiveData<List<QueryCuti>> getAllQueryCuti();

    @Query("SELECT c.idCuti, c.tanggalCuti, c.tanggalAkhirCuti, c.keteranganCuti, c.diProses, c.idPegawai, " +
            "c.ttdPegawai, c.ttdAdmin, c.status, c.statusKeterangan, p.nama " +
            "FROM Cuti c JOIN Pegawai p ON c.idPegawai = p.id WHERE p.id =:id AND status = 0")
    LiveData<List<QueryCuti>> getQueryCutiPegawaiDiproses(String id);

    @Query("UPDATE Cuti SET ttdAdmin =:ttd, status =:status, statusKeterangan =:statusKeterangan, diProses =:diProses WHERE idCuti =:id")
    void konfirmasiCuti(long id, Bitmap ttd, int status, String statusKeterangan, boolean diProses);

    @Query("UPDATE JumlahCuti SET jumlahCuti = jumlahCuti-1 WHERE pegawaiId =:pegawaiId")
    void approveCuti(String pegawaiId);


}

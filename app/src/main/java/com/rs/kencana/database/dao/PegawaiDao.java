package com.rs.kencana.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.Pegawai;
import com.rs.kencana.database.model.PegawaiProfil;

import java.util.List;

@Dao
public abstract class PegawaiDao {

    @Insert
    public  abstract  void daftarPegawai(Pegawai pegawai);

    @Insert
    public  abstract  void tambahPegawai(List<Pegawai> pegawai);

    @Insert
    public abstract void tambahJumlahCuti(JumlahCuti jumlahCuti);

    @Query("SELECT * FROM JumlahCuti WHERE pegawaiId =:pegawaiId")
    public abstract LiveData<JumlahCuti> getJumlahCuti(String pegawaiId);

    @Insert
    public abstract void insertJumlahCuti(List<JumlahCuti> list);

    @Query("SELECT * FROM Pegawai WHERE id =:pegawaiId AND password =:password")
    public  abstract  Pegawai getPegawaiId(String pegawaiId, String password);

    @Query("SELECT * FROM Pegawai WHERE id =:id")
    public  abstract  LiveData<Pegawai> getPegawaiById(String id);

    @Query("SELECT p.id, nama, alamat, email, jenisKelamin, j.deskripsi, noHP, t.jumlahCuti FROM Pegawai p JOIN Jabatan j ON p.idJabatan = j.id JOIN JumlahCuti t ON p.id = t.pegawaiId WHERE p.id =:id")
    public  abstract  LiveData<PegawaiProfil> getProfilPegawai(String id);

    @Query("SELECT p.id, nama, alamat, email, jenisKelamin, j.deskripsi, noHP, t.jumlahCuti FROM Pegawai p JOIN Jabatan j ON p.idJabatan = j.id JOIN JumlahCuti t ON p.id = t.pegawaiId WHERE aksesCuti =:value")
    public abstract LiveData<List<PegawaiProfil>> getAllPegawaiProfil(boolean value);
}

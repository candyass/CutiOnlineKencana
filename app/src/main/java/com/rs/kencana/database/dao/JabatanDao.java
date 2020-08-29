package com.rs.kencana.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.rs.kencana.database.model.Jabatan;

import java.util.List;

@Dao
public interface JabatanDao {

    @Insert
    void tambahJabatan(List<Jabatan> list);

    @Query("SELECT * FROM jabatan WHERE id <> 9")
    LiveData<List<Jabatan>> getAllJabatan();
}

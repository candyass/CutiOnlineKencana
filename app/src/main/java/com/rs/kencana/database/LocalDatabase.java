package com.rs.kencana.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.rs.kencana.database.converter.BitmapConverter;
import com.rs.kencana.database.converter.DateConverter;
import com.rs.kencana.database.dao.CutiDao;
import com.rs.kencana.database.dao.JabatanDao;
import com.rs.kencana.database.dao.PegawaiDao;
import com.rs.kencana.database.model.Cuti;
import com.rs.kencana.database.model.Jabatan;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.Pegawai;

@Database(entities = {Pegawai.class, Jabatan.class, Cuti.class, JumlahCuti.class}, version = 1)
@TypeConverters({DateConverter.class, BitmapConverter.class})
public abstract class LocalDatabase  extends RoomDatabase {

    public abstract PegawaiDao getPegawaiDao();
    public abstract CutiDao getCutiDao();
    public abstract JabatanDao getJabatanDao();

}

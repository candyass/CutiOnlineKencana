package com.rs.kencana.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Pegawai.class, parentColumns = "id", childColumns = "pegawaiId"))
public class JumlahCuti {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String pegawaiId;
    private int jumlahCuti;


    public JumlahCuti() {

    }

    @Ignore
    public JumlahCuti(String pegawaiId) {
        this.pegawaiId = pegawaiId;
        jumlahCuti = 3;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPegawaiId() {
        return pegawaiId;
    }

    public void setPegawaiId(String pegawaiId) {
        this.pegawaiId = pegawaiId;
    }

    public int getJumlahCuti() {
        return jumlahCuti;
    }

    public void setJumlahCuti(int jumlahCuti) {
        this.jumlahCuti = jumlahCuti;
    }
}

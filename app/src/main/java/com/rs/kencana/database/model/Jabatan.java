package com.rs.kencana.database.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Jabatan {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String deskripsi;

    public Jabatan() {

    }

    @Ignore
    public Jabatan(long id, String deskripsi) {
        this.id = id;
        this.deskripsi = deskripsi;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @Override
    public String toString() {
        return deskripsi;
    }
}

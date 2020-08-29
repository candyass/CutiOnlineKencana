package com.rs.kencana.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Jabatan.class, parentColumns = "id", childColumns = "idJabatan"))
public class Pegawai {

    @PrimaryKey
    @NonNull
    private String id;
    private String nama;
    private String alamat;
    private String email;
    private String password;
    private String jenisKelamin;
    private byte[] foto;
    private long idJabatan;
    private boolean aksesCuti;
    private String noHP;

    public Pegawai() {

    }

    public Pegawai(String id, String nama, String alamat, String password, String jenisKelamin, long idJabatan, String email, String noHP) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.password = password;
        this.jenisKelamin = jenisKelamin;
        this.idJabatan = idJabatan;
        this.email = email;
        this.noHP = noHP;
    }

    public Pegawai(String id, String nama, String alamat, String password, String jenisKelamin, long idJabatan, String email, boolean aksesCuti) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.password = password;
        this.jenisKelamin = jenisKelamin;
        this.idJabatan = idJabatan;
        this.email = email;
        this.aksesCuti = aksesCuti;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public long getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(long idJabatan) {
        this.idJabatan = idJabatan;
    }

    public boolean isAksesCuti() {
        return aksesCuti;
    }

    public void setAksesCuti(boolean aksesCuti) {
        this.aksesCuti = aksesCuti;
    }


    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }
}

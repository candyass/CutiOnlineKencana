package com.rs.kencana.database.model;

public class PegawaiProfil {

    private String id;
    private String nama;
    private String alamat;
    private String email;
    private String jenisKelamin;
    private String deskripsi;
    private String noHP;
    private int jumlahCuti;


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

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public int getJumlahCuti() {
        return jumlahCuti;
    }

    public void setJumlahCuti(int jumlahCuti) {
        this.jumlahCuti = jumlahCuti;
    }
}

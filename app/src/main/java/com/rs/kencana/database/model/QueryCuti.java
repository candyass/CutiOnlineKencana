package com.rs.kencana.database.model;

import android.graphics.Bitmap;

import java.util.Date;

public class QueryCuti {

    private long idCuti;
    private String idPegawai;
    private String nama;
    private Date tanggalCuti;
    private Date tanggalAkhirCuti;
    private String keteranganCuti;
    private Bitmap ttdPegawai;
    private Bitmap ttdAdmin;
    private int status;
    private boolean diProses;
    private String statusKeterangan;


    public long getIdCuti() {
        return idCuti;
    }

    public void setIdCuti(long idCuti) {
        this.idCuti = idCuti;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggalCuti() {
        return tanggalCuti;
    }

    public void setTanggalCuti(Date tanggalCuti) {
        this.tanggalCuti = tanggalCuti;
    }

    public Date getTanggalAkhirCuti() {
        return tanggalAkhirCuti;
    }

    public void setTanggalAkhirCuti(Date tanggalAkhirCuti) {
        this.tanggalAkhirCuti = tanggalAkhirCuti;
    }

    public String getKeteranganCuti() {
        return keteranganCuti;
    }

    public void setKeteranganCuti(String keteranganCuti) {
        this.keteranganCuti = keteranganCuti;
    }

    public Bitmap getTtdPegawai() {
        return ttdPegawai;
    }

    public void setTtdPegawai(Bitmap ttdPegawai) {
        this.ttdPegawai = ttdPegawai;
    }

    public Bitmap getTtdAdmin() {
        return ttdAdmin;
    }

    public void setTtdAdmin(Bitmap ttdAdmin) {
        this.ttdAdmin = ttdAdmin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusKeterangan() {
        return statusKeterangan;
    }

    public void setStatusKeterangan(String statusKeterangan) {
        this.statusKeterangan = statusKeterangan;
    }


    public boolean isDiProses() {
        return diProses;
    }

    public void setDiProses(boolean diProses) {
        this.diProses = diProses;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }
}

package com.rs.kencana.database.model;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Pegawai.class, parentColumns = "id", childColumns = "idPegawai"))
public class Cuti {

    public static final int DIPROSES = 0;
    public static final int DITERIMA = 1;
    public static final int DITOLAK = 2;


    @PrimaryKey(autoGenerate = true)
    private long idCuti;
    private String idPegawai;
    private Date tanggalCuti;
    private Date tanggalAkhirCuti;
    private int status;
    private String statusKeterangan;
    @NonNull
    private Bitmap ttdPegawai;
    private Bitmap ttdAdmin;
    private boolean diProses;
    private String keteranganCuti;


    public Cuti(String idPegawai, Date tanggalCuti, Date tanggalAkhirCuti, String keteranganCuti, Bitmap ttdPegawai) {
        this.idPegawai = idPegawai;
        this.tanggalCuti = tanggalCuti;
        this.tanggalAkhirCuti = tanggalAkhirCuti;
        this.keteranganCuti = keteranganCuti;
        this.ttdPegawai = ttdPegawai;
    }



    public long getIdCuti() {
        return idCuti;
    }

    public void setIdCuti(long idCuti) {
        this.idCuti = idCuti;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
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

    public boolean isDiProses() {
        return diProses;
    }

    public void setDiProses(boolean diProses) {
        this.diProses = diProses;
    }


    public String getKeteranganCuti() {
        return keteranganCuti;
    }

    public void setKeteranganCuti(String keteranganCuti) {
        this.keteranganCuti = keteranganCuti;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusKeterangan() {
        if(status == DIPROSES) {
            statusKeterangan = "Diproses";
        }else if(status == DITERIMA) {
            statusKeterangan = "Diterima";
        }else if(status == DITOLAK) {
            statusKeterangan = "Ditolak";
        }
        return statusKeterangan;
    }

    public void setStatusKeterangan(String statusKeterangan) {
        this.statusKeterangan = statusKeterangan;
    }

}

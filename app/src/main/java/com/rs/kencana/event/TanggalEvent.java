package com.rs.kencana.event;

import java.util.Date;

public class TanggalEvent {
    private int posisi;
    private Date date;


    public TanggalEvent() {

    }

    public TanggalEvent(int posisi, Date date) {
        this.posisi = posisi;
        this.date = date;
    }

    public int getPosisi() {
        return posisi;
    }

    public void setPosisi(int posisi) {
        this.posisi = posisi;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

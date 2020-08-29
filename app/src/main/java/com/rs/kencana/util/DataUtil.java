package com.rs.kencana.util;

import com.rs.kencana.database.model.Jabatan;
import com.rs.kencana.database.model.JumlahCuti;
import com.rs.kencana.database.model.Pegawai;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static List<Jabatan> getAllJabatan() {
        List<Jabatan> list = new ArrayList<>();
        list.add(new Jabatan(1, "Dokter Umum"));
        list.add(new Jabatan(2, "Dokter Spesialis"));
        list.add(new Jabatan(3, "Mantri"));
        list.add(new Jabatan(4, "Perawat"));
        list.add(new Jabatan(5, "Staff Administrasi"));
        list.add(new Jabatan(6, "Staff Medis"));
        list.add(new Jabatan(7, "Staff Farmasi"));
        list.add(new Jabatan(8, "Staff Kebersihat"));
        list.add(new Jabatan(9, "HRD"));
        return list;
    }


    public static List<Pegawai> getAksesCutiPegawai() {
        List<Pegawai> list = new ArrayList<>();
        list.add(new Pegawai("111111","Staff HRD 1", "", "unbaja1", "Laki-Laki",9, "staffrskencana1@gamail.com", true));
        list.add(new Pegawai("123456","Sigit", "Kasemen", "serang", "Laki-Laki",2, "moh.sigit60@gmail.com", "087789898"));
        return list;
    }

    public static List<JumlahCuti> getJumlahCuti() {
        List<JumlahCuti> list = new ArrayList<>();
        list.add(new JumlahCuti("123456"));
        return list;
    }
}

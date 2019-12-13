package com.PersonalLancer.mybookingfieldtrip.LihatJadwal;

import com.google.gson.annotations.SerializedName;

public class ModelJadwalKegiatan {

    @SerializedName("id_transaksi")
    private String id_transaksi;

    @SerializedName("nama_sekolah")
    private String nama_sekolah;

    @SerializedName("tanggal_booking")
    private String tanggal_booking;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getTanggal_booking() {
        return tanggal_booking;
    }

    public void setTanggal_booking(String tanggal_booking) {
        this.tanggal_booking = tanggal_booking;
    }
}

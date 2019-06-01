package com.universedeveloper.mybookingfieldtrip.Api;

import com.google.gson.annotations.SerializedName;

public class ModelTransaksiUser {

    @SerializedName("id_transaksi") //ini yg ada di json
    private String id_transaksi;

    @SerializedName("id") //ini yg ada di json
    private String id;

    @SerializedName("nama_lengkap") //ini yg ada di json
    private String nama_lengkap;

    @SerializedName("nomor_hp") //ini yg ada di json
    private String nomor_hp;

    @SerializedName("jumlah_siswa") //ini yg ada di json
    private String jumlah_siswa;

    @SerializedName("total_harga") //ini yg ada di json
    private String total_harga;

    public String getId_transaksi() { return id_transaksi; }

    public void setId_transaksi(String id_transaksi) { this.id_transaksi = id_transaksi; }

    public String getId_user() { return id; }

    public void setId_user(String id) { this.id = id; }

    public String getNama_user() {
        return nama_lengkap;
    }

    public void setNama_user(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public void setNomor_hp(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }

    public String getJumlah_siswa() {
        return jumlah_siswa;
    }

    public void setJumlah_siswa(String jumlah_siswa) {
        this.jumlah_siswa = jumlah_siswa;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }
}

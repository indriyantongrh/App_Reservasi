package com.PersonalLancer.mybookingfieldtrip.KonfirmasiPembayaran;

import com.google.gson.annotations.SerializedName;

public class ModelKonfirmasiPembayaran {

    @SerializedName("id_transaksi") //ini yg ada di json
    private String id_transaksi;
    @SerializedName("id") //ini yg ada di json
    private String id;
    @SerializedName("nama_lengkap") //ini yg ada di json
    private String nama_lengkap;
    @SerializedName("nomor_hp") //ini yg ada di json
    private String nomor_hp;
    @SerializedName("nomor_kegiatan") //ini yg ada di json
    private String nomor_kegiatan;
    @SerializedName("nama_sekolah") //ini yg ada di json
    private String nama_sekolah;
    @SerializedName("tanggal_booking") //ini yg ada di json
    private String tanggal_booking;
    @SerializedName("jumlah_siswa") //ini yg ada di json
    private String jumlah_siswa;
    @SerializedName("jumlah_guru") //ini yg ada di json
    private String jumlah_guru;
    @SerializedName("jumlah_supir") //ini yg ada di json
    private String jumlah_supir;
    @SerializedName("total_harga") //ini yg ada di json
    private String total_harga;
    @SerializedName("total_bayar") //ini yg ada di json
    private String total_bayar;
    @SerializedName("keterangan") //ini yg ada di json
    private String keterangan;
    @SerializedName("id_paket") //ini yg ada di json
    private String id_paket;
    @SerializedName("jenis_paket") //ini yg ada di json
    private String jenis_paket;
    @SerializedName("bukti_transfer") //ini yg ada di json
    private String bukti_transfer;
    @SerializedName("status_transaksi") //ini yg ada di json
    private String status_transaksi;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public void setNomor_hp(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }

    public String getNomor_kegiatan() {
        return nomor_kegiatan;
    }

    public void setNomor_kegiatan(String nomor_kegiatan) {
        this.nomor_kegiatan = nomor_kegiatan;
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

    public String getJumlah_siswa() {
        return jumlah_siswa;
    }

    public void setJumlah_siswa(String jumlah_siswa) {
        this.jumlah_siswa = jumlah_siswa;
    }

    public String getJumlah_guru() {
        return jumlah_guru;
    }

    public void setJumlah_guru(String jumlah_guru) {
        this.jumlah_guru = jumlah_guru;
    }

    public String getJumlah_supir() {
        return jumlah_supir;
    }

    public void setJumlah_supir(String jumlah_supir) {
        this.jumlah_supir = jumlah_supir;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public void setTotal_bayar(String total_bayar) {
        this.total_bayar = total_bayar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getId_paket() {
        return id_paket;
    }

    public void setId_paket(String id_paket) {
        this.id_paket = id_paket;
    }

    public String getJenis_paket() {
        return jenis_paket;
    }

    public void setJenis_paket(String jenis_paket) {
        this.jenis_paket = jenis_paket;
    }

    public String getBukti_transfer() {
        return bukti_transfer;
    }

    public void setBukti_transfer(String bukti_transfer) {
        this.bukti_transfer = bukti_transfer;
    }

    public String getStatus_transaksi() {
        return status_transaksi;
    }

    public void setStatus_transaksi(String status_transaksi) {
        this.status_transaksi = status_transaksi;
    }
}

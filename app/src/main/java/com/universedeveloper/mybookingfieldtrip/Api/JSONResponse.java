package com.universedeveloper.mybookingfieldtrip.Api;

import com.universedeveloper.mybookingfieldtrip.KonfirmasiPembayaran.ModelKonfirmasiPembayaran;

public class JSONResponse {
    private ModelMenu[] menu;

    public ModelMenu[] getMenu() {
        return menu;
    }

    private ModelMenu[] menumakanan;

    public ModelMenu[] getMenuMakanan() {
        return menumakanan;
    }

    private ModelProfilUser[] datauser;

    public ModelProfilUser[] getDatauser() { return datauser; }

    private ModelTransaksiUser[] datatransaksi;

    public ModelTransaksiUser[] getTransaksi() { return datatransaksi; }

    private ModelTanggal[] data_tanggal;

    public ModelTanggal[] getData_tanggal() { return data_tanggal; }

    private ModelKonfirmasiPembayaran[] listpesanan;

    public ModelKonfirmasiPembayaran[] getStatusPesanan() {
        return listpesanan;
    }

}
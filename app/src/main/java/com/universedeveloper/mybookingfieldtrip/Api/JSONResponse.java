package com.universedeveloper.mybookingfieldtrip.Api;

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
}
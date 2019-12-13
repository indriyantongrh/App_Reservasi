package com.PersonalLancer.mybookingfieldtrip.Api;

import com.google.gson.annotations.SerializedName;

public class ModelProfilUser {
    @SerializedName("id") //ini yg ada di json
    private String id;

    @SerializedName("nama_lengkap") //ini yg ada di json
    private String nama_lengkap;

    @SerializedName("email") //ini yg ada di json
    private String email;

    @SerializedName("password") //ini yg ada di json
    private String password;

    @SerializedName("nomor_hp") //ini yg ada di json
    private String nomor_hp;



    public String getId_user() { return id; }

    public void setId_user(String id) { this.id = id; }

    public String getNama_user() {
        return nama_lengkap;
    }

    public void setNama_user(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail_user() {
        return email;
    }

    public void setEmail_user(String email) {
        this.email = email;
    }

    public String getTelepon_user() {
        return nomor_hp;
    }

    public void setTelepon_user(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

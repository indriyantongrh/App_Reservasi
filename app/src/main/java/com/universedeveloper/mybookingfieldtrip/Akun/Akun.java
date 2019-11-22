package com.universedeveloper.mybookingfieldtrip.Akun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.universedeveloper.mybookingfieldtrip.Api.JSONResponse;
import com.universedeveloper.mybookingfieldtrip.Api.ModelProfilUser;
import com.universedeveloper.mybookingfieldtrip.Api.RequestInterface;
import com.universedeveloper.mybookingfieldtrip.LoginRegister.LoginUser;
import com.universedeveloper.mybookingfieldtrip.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Akun extends Fragment {

    String id;
    SharedPreferences sharedpreferences;
    private ArrayList<ModelProfilUser> mArrayListUser;

    public final static String TAG_ID_USER = "id";
    public final static String TAG_ID_EMAIL = "email";

    TextView txt_nama_akun, txt_telepon, txt_email;

    Button btn_logout;

    public static final String BASE_URL = "http://universedeveloper.com/gudangandroid/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_akun, container, false);


        txt_nama_akun = view.findViewById(R.id.txt_nama_akun);
        txt_telepon = view.findViewById(R.id.txt_telepon);
        txt_email = view.findViewById(R.id.txt_email);


        sharedpreferences = getActivity().getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", "0");

        ambilProfilUser();

        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                sharedpreferences = getActivity().getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginUser.session_status, false);
                editor.putString(TAG_ID_USER, null);
                editor.putString(TAG_ID_EMAIL, null);
                editor.commit();
                editor.apply();

                Intent intent = new Intent(getActivity(), LoginUser.class);
                startActivity(intent);


            }


        });


        return view;
    }


    public void ambilProfilUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getProfilUser(id);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                mArrayListUser = new ArrayList<>(Arrays.asList(jsonResponse.getDatauser()));
                String nama_lengkap = mArrayListUser.get(0).getNama_user();
                String nomor_hp = mArrayListUser.get(0).getTelepon_user();
                String email = mArrayListUser.get(0).getEmail_user();

                txt_nama_akun.setText(nama_lengkap);
                txt_telepon.setText(nomor_hp);
                txt_email.setText(email);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}

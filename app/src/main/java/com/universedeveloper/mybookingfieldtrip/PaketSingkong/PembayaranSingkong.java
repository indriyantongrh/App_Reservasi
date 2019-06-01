package com.universedeveloper.mybookingfieldtrip.PaketSingkong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.universedeveloper.mybookingfieldtrip.Api.JSONResponse;
import com.universedeveloper.mybookingfieldtrip.Api.ModelProfilUser;
import com.universedeveloper.mybookingfieldtrip.Api.ModelTransaksiUser;
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

public class PembayaranSingkong extends AppCompatActivity {

    Intent intent;
    public final static String TAG_ID_USER = "id";
    public final static String TAG_ID_TRANSAKSI = "id_transaksi";
    public final static String TAG_ID_EMAIL = "email";

    TextView txt_jumlahsiswa, txt_totalharga;
    Button btnkonfirmadmin, btnkembaliberanda;

    String id, id_transaksi;
    SharedPreferences sharedpreferences;
    private ArrayList<ModelTransaksiUser> mArrayListTransaksi;
    String tag_json_obj = "json_obj_req";
    int success;

    private static final String TAG = PembayaranSingkong.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public static final String BASE_URL = "http://universedeveloper.com/gudangandroid/";
    private static final String url_ambil_id_transaksi = "http://universedeveloper.com/gudangandroid/mykopi/user/cek_id_transaksi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_singkong);

        sharedpreferences = getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", "0");
        Toast.makeText(this, "ini id ke-"+ id, Toast.LENGTH_SHORT).show();

        id_transaksi = sharedpreferences.getString("id_transaksi", "0");
        Toast.makeText(this, "ini id tansaksi ke-"+ id_transaksi, Toast.LENGTH_SHORT).show();

        txt_jumlahsiswa = findViewById(R.id.txt_jumlahsiswa);
        txt_totalharga = findViewById(R.id.txt_totalharga);

        btnkonfirmadmin = findViewById(R.id.btnkonfirmadmin);
        btnkembaliberanda = findViewById(R.id.btnkembaliberanda);

        ambilTransaksiUser();
    }


    public void ambilTransaksiUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getTransaksi(id_transaksi);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                mArrayListTransaksi = new ArrayList<>(Arrays.asList(jsonResponse.getTransaksi()));
                String jumlah_siswa = mArrayListTransaksi.get(0).getJumlah_siswa();
                String total_harga = mArrayListTransaksi.get(0).getTotal_harga();


                txt_jumlahsiswa.setText(jumlah_siswa);
                txt_totalharga.setText(total_harga);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }


}

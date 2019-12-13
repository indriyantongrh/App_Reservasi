package com.PersonalLancer.mybookingfieldtrip.LihatJadwal;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.PersonalLancer.mybookingfieldtrip.Api.JSONResponse;
import com.PersonalLancer.mybookingfieldtrip.Api.RequestInterface;
import com.PersonalLancer.mybookingfieldtrip.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.PersonalLancer.mybookingfieldtrip.BuildConfig.BASE_URL;

public class ListLihatJadwal extends AppCompatActivity {

    RecyclerView listjadwalkegiatan;
    private ArrayList<ModelJadwalKegiatan> mArrayList;
    AdapterJadwalKegiatan adapterJadwalKegiatan;
    SharedPreferences sharedpreferences;
    String id, id_transaksi;

    SwipeRefreshLayout swipeRefresh;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lihat_jadwal);




        listjadwalkegiatan = (RecyclerView) findViewById(R.id.listjadwalkegiatan);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        progressBar = findViewById(R.id.progressBar);

        swipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                loadJSON();
                swipeRefresh.setRefreshing(false);
            }
        });




       initViews();
       loadJSON();

    }


    private void initViews(){

        ///list_lowonganpelamar.setAdapter(adapterRecyclerViewLowonganPelamar);
        listjadwalkegiatan.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listjadwalkegiatan.setLayoutManager(layoutManager);


    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build())
                .build();
        progressBar.setVisibility(android.view.View.VISIBLE);
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJadwalKegiatan();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                progressBar.setVisibility(android.view.View.INVISIBLE);
                swipeRefresh.setRefreshing(false);
                mArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getJadwalKegiatan()));
                //mAdapter = new AdapterPencarianMenu(mArrayList);
                adapterJadwalKegiatan = new AdapterJadwalKegiatan(getApplicationContext(),mArrayList);
                listjadwalkegiatan.setAdapter(adapterJadwalKegiatan);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }



}

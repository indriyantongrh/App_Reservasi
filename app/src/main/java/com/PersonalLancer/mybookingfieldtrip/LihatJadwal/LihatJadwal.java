package com.PersonalLancer.mybookingfieldtrip.LihatJadwal;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.PersonalLancer.mybookingfieldtrip.Api.JSONResponse;
import com.PersonalLancer.mybookingfieldtrip.Api.ModelTanggal;
import com.PersonalLancer.mybookingfieldtrip.Api.RequestInterface;
import com.PersonalLancer.mybookingfieldtrip.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.PersonalLancer.mybookingfieldtrip.Utility.JSONParser;

import org.json.JSONArray;

public class LihatJadwal extends AppCompatActivity {
    TextView textView;

    String tanggal_booking, nama_sekolah, id_transaksi,datedisplay;
    SharedPreferences sharedpreferences;
    private ArrayList<ModelTanggal> mArrayListTanggal;
    String tag_json_obj = "json_obj_req";
    JSONArray string_json = null;
    JSONParser jsonParser = new JSONParser();
    int success;

    private static  final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    public static final String BASE_URL = "http://universedeveloper.com/gudangandroid/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);

        MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        textView = findViewById(R.id.textView);

//// coding lawas
      materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2018, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
             ////   String datedisplay = onDateSelected(date.getDate().get);
              ////  textView.setText(datedisplay);

               /// AmbilTanggal();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RequestInterface request = retrofit.create(RequestInterface.class);
                Call<JSONResponse> call = request.getData_tanggal(tanggal_booking);
                call.enqueue(new Callback<JSONResponse>() {
                    @Override
                    public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                        JSONResponse jsonResponse = response.body();

                        mArrayListTanggal = new ArrayList<>(Arrays.asList(jsonResponse.getData_tanggal()));
                        String tanggal_booking = mArrayListTanggal.get(0).getTanggal_booking();
                        String nama_sekolah = mArrayListTanggal.get(0).getNama_sekolah();
                        ///  String nomor_hp = mArrayListUser.get(0).getTelepon_user();
                        Toast.makeText(LihatJadwal.this, "Jadawal dari " + nama_sekolah  + tanggal_booking,Toast.LENGTH_SHORT).show();
                        textView.setText(nama_sekolah);
                        ////Toast.makeText(LihatJadwal.this, "Reservasi dari " + nama_sekolah ,Toast.LENGTH_SHORT).show();
                        ////  txtnomorhp.setText(nomor_hp);

                    }

                    @Override
                    public void onFailure(Call<JSONResponse> call, Throwable t) {
                        Log.d("Error",t.getMessage());
                    }
                });
                ////textView.setText(FORMATTER.format(MaterialCalendarView.getTanggal_booking()));

            }
        });






    }

   public void AmbilTanggal(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getData_tanggal(tanggal_booking);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                mArrayListTanggal = new ArrayList<>(Arrays.asList(jsonResponse.getData_tanggal()));
                String nama_sekolah = mArrayListTanggal.get(0).getNama_sekolah();
                ///  String nomor_hp = mArrayListUser.get(0).getTelepon_user();


                textView.setText(nama_sekolah);
                ////  txtnomorhp.setText(nomor_hp);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

}


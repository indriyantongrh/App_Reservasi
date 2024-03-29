package com.PersonalLancer.mybookingfieldtrip.BookingHari;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.PersonalLancer.mybookingfieldtrip.LoginRegister.LoginUser;
import com.PersonalLancer.mybookingfieldtrip.PaketSingkong.PaketSingkong;
import com.PersonalLancer.mybookingfieldtrip.PaketSusu.PaketSusu;
import com.PersonalLancer.mybookingfieldtrip.R;

public class ListPaket extends AppCompatActivity {

    String id;
    CardView btn_paketsingkong, btn_paketkeliling, btn_paketsapi,btn_paketlivein ;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_paket);

        setActionBarTitle("List paket kegiatan");
      //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
      ///  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", "0");
        Toast.makeText(this, "ini id ke-"+ id, Toast.LENGTH_SHORT).show();

        btn_paketsingkong = findViewById(R.id.btn_paketsingkong);
        btn_paketsingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaketSingkong.class);
                startActivity(intent);
            }

        });

        btn_paketsapi = findViewById(R.id.btn_paketsapi);
        btn_paketsapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaketSusu.class);
                startActivity(intent);
            }

        });


        btn_paketlivein = findViewById(R.id.btn_paketlivein);
        btn_paketlivein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Fitur Paket Jadi Anak Desa Wisata Segera Hadir", Toast.LENGTH_SHORT).show();
            }

        });


        btn_paketkeliling = findViewById(R.id.btn_paketkeliling);
        btn_paketkeliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Fitur Paket Keliling Desa Wisata Segera Hadir", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

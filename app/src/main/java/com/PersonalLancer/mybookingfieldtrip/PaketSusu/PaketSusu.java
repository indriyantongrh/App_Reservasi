package com.PersonalLancer.mybookingfieldtrip.PaketSusu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.PersonalLancer.mybookingfieldtrip.LoginRegister.LoginUser;
import com.PersonalLancer.mybookingfieldtrip.R;

public class PaketSusu extends AppCompatActivity {

    Button btnlanjutkan;

    String id;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket_susu);



        sharedpreferences = getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", "0");
        Toast.makeText(this, "ini id ke-"+ id, Toast.LENGTH_SHORT).show();


        btnlanjutkan = findViewById(R.id.btnlanjutkan);
        btnlanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InputDataCustSusu.class);
                startActivity(intent);

            }

        });

    }

}

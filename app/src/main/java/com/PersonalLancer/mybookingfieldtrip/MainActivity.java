package com.PersonalLancer.mybookingfieldtrip;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.PersonalLancer.mybookingfieldtrip.Beranda.Beranda;
import com.PersonalLancer.mybookingfieldtrip.VideoKegiatan.VideoKegiatan;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();

        Fragment fragment;
        toolbar.setTitle("Beranda");
        fragment = new Beranda();
        loadFragment(fragment);
        //return true;

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_beranda:
                    //toolbar.setTitle("Selamat Datang");
                    fragment = new Beranda();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_ukm:
                    toolbar.setTitle("Belanja");
                    fragment = new VideoKegiatan();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        // bundle.putString("kode", kode);
        //bundle.putString("key", key);
        fragment.setArguments(bundle);
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();


    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}

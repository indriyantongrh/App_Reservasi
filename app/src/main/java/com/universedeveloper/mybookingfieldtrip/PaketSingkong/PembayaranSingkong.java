package com.universedeveloper.mybookingfieldtrip.PaketSingkong;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.universedeveloper.mybookingfieldtrip.Api.JSONResponse;
import com.universedeveloper.mybookingfieldtrip.Api.ModelProfilUser;
import com.universedeveloper.mybookingfieldtrip.Api.ModelTransaksiUser;
import com.universedeveloper.mybookingfieldtrip.Api.RequestInterface;
import com.universedeveloper.mybookingfieldtrip.BookingHari.ListPaket;
import com.universedeveloper.mybookingfieldtrip.LoginRegister.LoginUser;
import com.universedeveloper.mybookingfieldtrip.PaketSusu.InputDataCustSusu;
import com.universedeveloper.mybookingfieldtrip.R;
import com.universedeveloper.mybookingfieldtrip.Utility.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    TextView txt_jumlahsiswa, txt_totalharga, txtnomorkegiatan;
    Button btnkonfirmadmin, btnkembaliberanda;

    String id, id_transaksi;
    SharedPreferences sharedpreferences;
    private ArrayList<ModelTransaksiUser> mArrayListTransaksi;
    String tag_json_obj = "json_obj_req";
    int success;

    String String_id_transaksi;
    JSONArray string_json = null;
    JSONParser jsonParser = new JSONParser();
    private int mYear, mMonth, mDay;
    ProgressDialog pDialog;

    private static final String TAG = PembayaranSingkong.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public static final String BASE_URL = "http://universedeveloper.com/gudangandroid/";
    private static final String url_ambil_id_transaksi = "http://universedeveloper.com/gudangandroid/fieldtripkandri/User/cek_idtransaksi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_singkong);

/*        setActionBarTitle("Yeaay Reservasi berhasil!");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        sharedpreferences = getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", "0");
       //// Toast.makeText(this, "ini id ke-" + id, Toast.LENGTH_SHORT).show();

        id_transaksi = sharedpreferences.getString("id_transaksi", "0");
      ///  Toast.makeText(this, "ini id tansaksi ke-" + id_transaksi, Toast.LENGTH_SHORT).show();

        txt_jumlahsiswa = findViewById(R.id.txt_jumlahsiswa);
        txt_totalharga = findViewById(R.id.txt_totalharga);
        txtnomorkegiatan = findViewById(R.id.txtnomorkegiatan);

//        btnkonfirmadmin = findViewById(R.id.btnkonfirmadmin);
//        btnkonfirmadmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentWhatsapp = new Intent("android.intent.action.MAIN");
//                intentWhatsapp.setAction(Intent.ACTION_VIEW);
//                String url = "https://api.whatsapp.com/send?phone=" + "6285803000346" + "&text=Halo admin, Mau konfirmasi nih...";
//
//                intentWhatsapp.setData(Uri.parse(url));
//                intentWhatsapp.setPackage("com.whatsapp");
//                startActivity(intentWhatsapp);
//
//            }
//        });

        btnkembaliberanda = findViewById(R.id.btnkembaliberanda);

        ambilTransaksiUser();
        new AmbilIdTransaksiTerakhir().execute();

    }


    public void ambilTransaksiUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getTransaksi(id);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                mArrayListTransaksi = new ArrayList<>(Arrays.asList(jsonResponse.getTransaksi()));
                String nomor_kegiatan = mArrayListTransaksi.get(0).getNomor_kegiatan();
                String jumlah_siswa = mArrayListTransaksi.get(0).getJumlah_siswa();
                String total_bayar = mArrayListTransaksi.get(0).getTotal_bayar();

                txtnomorkegiatan.setText(nomor_kegiatan);
                txt_jumlahsiswa.setText(jumlah_siswa);
                txt_totalharga.setText(total_bayar);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    class AmbilIdTransaksiTerakhir extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(PembayaranSingkong.this);
            pDialog.setMessage("Membuat pesanan ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                JSONObject json = jsonParser.makeHttpRequest(url_ambil_id_transaksi, "GET", params1);
                string_json = json.getJSONArray("nomor");

                runOnUiThread(new Runnable() {
                    public void run() {


                        try {
                            // ambil objek member pertama dari JSON Array
                            JSONObject ar = string_json.getJSONObject(0);
                            String nomor_id_d = ar.getString("id");
                            Integer tambah = Integer.parseInt(nomor_id_d) + 1;
                            String fix = getDateId() + "-" + String.valueOf(tambah);
                            String_id_transaksi = getDateId() + "-" + String.valueOf(tambah);

                            if (String_id_transaksi != null) {

                            } else {

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            //simpanTransaksi();
            pDialog.dismiss();
        }
    }


    //untuk transaksi terakhir
    private String getDateId() {
        Date current = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("ddMMyy");
        String dateString = frmt.format(current);
        return dateString;
    }

    public void onClickWhatsApp(View view) {

        PackageManager pm = getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "KONFIRMASI RESERV OUTBOUND";

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(getApplicationContext(), ListPaket.class);
        startActivity(intent);
        return true;
    }



}
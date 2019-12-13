package com.PersonalLancer.mybookingfieldtrip.KonfirmasiPembayaran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.PersonalLancer.mybookingfieldtrip.BuildConfig;
import com.PersonalLancer.mybookingfieldtrip.R;
import com.PersonalLancer.mybookingfieldtrip.Utility.AppController;
import com.PersonalLancer.mybookingfieldtrip.Utility.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailKonfirmasi extends AppCompatActivity {

    TextView txt_namasekolah, txt_tanggalbooking, txt_totalbayar,txt_jumlahsiswa, txt_nomorkegiatan,txt_keterangan;
    CardView btnuploadgambar,btnkonfirmasi;
    ImageView image;
    String id_transaksi, nomor_kegiatan, nama_sekolah,total_bayar, tanggal_booking, jumlah_siswa,keterangan;

    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100

    JSONArray string_json = null;
    JSONParser jsonParser = new JSONParser();
    private ProgressDialog pDialog;
    int success;    private static final String url_konfirmasi_pembayaran = BuildConfig.BASE_URL+"User/konfirmasi_pembayaran.php";

    private static final String TAG = DetailKonfirmasi.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_konfirmasi);
        txt_nomorkegiatan = findViewById(R.id.txt_nomorkegiatan);
        txt_namasekolah = findViewById(R.id.txt_namasekolah);
        txt_tanggalbooking = findViewById(R.id.txt_tanggalbooking);
        txt_totalbayar = findViewById(R.id.txt_totalbayar);
        txt_jumlahsiswa = findViewById(R.id.txt_jumlahsiswa);
        image=findViewById(R.id.image);
        btnuploadgambar = findViewById(R.id.btnuploadgambar);
        btnkonfirmasi = findViewById(R.id.btnkonfirmasi);
        txt_keterangan = findViewById(R.id.txt_keterangan);


        id_transaksi = getIntent().getStringExtra("id_transaksi");
        Toast.makeText(this, "id Transaksi "+ id_transaksi, Toast.LENGTH_SHORT).show();


        ////////////////////////////////////// data lowongan
        id_transaksi = getIntent().getStringExtra("id_transaksi");
        nama_sekolah = getIntent().getStringExtra("nama_sekolah");
        nomor_kegiatan = getIntent().getStringExtra("nomor_kegiatan");
        tanggal_booking = getIntent().getStringExtra("tanggal_booking");
        total_bayar = getIntent().getStringExtra("total_bayar");
        jumlah_siswa = getIntent().getStringExtra("jumlah_siswa");
        keterangan = getIntent().getStringExtra("keterangan");


        txt_nomorkegiatan.setText(nomor_kegiatan);
        txt_namasekolah.setText(nama_sekolah);
        txt_tanggalbooking.setText(tanggal_booking);
        txt_jumlahsiswa.setText(jumlah_siswa);
        txt_totalbayar.setText(total_bayar);
        txt_keterangan.setText(keterangan);


        btnuploadgambar = findViewById(R.id.btnuploadgambar);
        btnuploadgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


        btnkonfirmasi = findViewById(R.id.btnkonfirmasi);
        btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KonfirmasiPembayaran();
            }
        });


    }


    //untuk upload image, compress .JPEG ke bitmap
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //untuk memilih gambar
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*Menampilkan gambar*/
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        image.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    private void KonfirmasiPembayaran() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Silahkan Tunggu ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, url_konfirmasi_pembayaran, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Berhasil", jObj.toString());

                        goToList();
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();





                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Upload Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                /// txtjumlahtf.setText(formatRupiah.format(jumlahbayar));

                params.put("id_transaksi", id_transaksi);
                params.put("bukti_transfer", getStringImage(decoded));
                params.put("status_transaksi", "Menunggu Konfirmasi Pembayaran");


                return params;
            }

        };

        // Adding request to request queue

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        ///AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void goToList(){

        Intent intent = new Intent(DetailKonfirmasi.this, KonfirmasiPembayaran.class);
        finish();
        startActivity(intent);
    }

}

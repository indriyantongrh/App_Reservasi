package com.PersonalLancer.mybookingfieldtrip.LoginRegister;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.PersonalLancer.mybookingfieldtrip.R;
import com.PersonalLancer.mybookingfieldtrip.Utility.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;


public class Register extends AppCompatActivity {
    Button btnlogin, btnregistrasi;


    private static final String TAG = Register.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    ProgressDialog pDialog;

    EditText txtnamalengkap,txtusername, txtemail, txtpassword, txtconfirmpassword, txttelepon;
    Intent intent;
    int success;
    ConnectivityManager conMgr;
    String tag_json_obj = "json_obj_req";
    private String url = "http://universedeveloper.com/gudangandroid/fieldtripkandri/registeruser.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet",
                        Toast.LENGTH_LONG).show();
            }
        }

        btnregistrasi = findViewById(R.id.btnregister);
        txtnamalengkap = findViewById(R.id.txtnamalengkap);
        //  txtusername = findViewById(R.id.txtusername);
        txtemail= findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        /////txtconfirmpassword = findViewById(R.id.txtconfirmpassword);
        txttelepon = findViewById(R.id.txttelepon);



        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginUser.class);
                startActivity(intent);
            }
        });
        btnregistrasi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nama_lengkap = txtnamalengkap.getText().toString();
                //         String username = txtusername.getText().toString();
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                ////  String confirmpassword = txtconfirmpassword.getText().toString();
                String nomor_hp = txttelepon.getText().toString();



                if (isEmpty(nama_lengkap))
                    txtnamalengkap.setError("Username harap diisi");
                else if (isEmpty(email))
                    txtemail.setError("Email harap diisi");
                else if (isEmpty(password))
                    txtpassword.setError("Password harap diisi");
                else if (isEmpty(nomor_hp))
                    txttelepon.setError("Nomor Telepon harap diisi");
                else

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegister(nama_lengkap, email,password, nomor_hp);
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void checkRegister(final String nama_lengkap,final String email,final String password ,final String nomor_hp) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Silahkan Tunggu ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Registrasi berhasil!", jObj.toString());

                        goToLogin();

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txtnamalengkap.setText("");
                        ///  txtusername.setText("");
                        txtemail.setText("");
                        txtpassword.setText("");
                        /// txtconfirmpassword.setText("");
                        txttelepon.setText("");

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
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama_lengkap", nama_lengkap);
                ///  params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("nomor_hp", nomor_hp);

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

    @Override
    public void onBackPressed() {
        intent = new Intent(Register.this, LoginUser.class);
        finish();
        startActivity(intent);
    }


    private void goToLogin(){

        intent = new Intent(Register.this, LoginUser.class);
        finish();
        startActivity(intent);
    }

}

package com.PersonalLancer.mybookingfieldtrip.Beranda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.PersonalLancer.mybookingfieldtrip.Api.JSONResponse;
import com.PersonalLancer.mybookingfieldtrip.Api.ModelProfilUser;
import com.PersonalLancer.mybookingfieldtrip.Api.RequestInterface;
import com.PersonalLancer.mybookingfieldtrip.BookingHari.ListPaket;
import com.PersonalLancer.mybookingfieldtrip.KonfirmasiPembayaran.KonfirmasiPembayaran;
import com.PersonalLancer.mybookingfieldtrip.LihatJadwal.ListLihatJadwal;
import com.PersonalLancer.mybookingfieldtrip.LoginRegister.LoginUser;
import com.PersonalLancer.mybookingfieldtrip.R;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.PersonalLancer.mybookingfieldtrip._sliders.FragmentSlider;
import com.PersonalLancer.mybookingfieldtrip._sliders.SliderIndicator;
import com.PersonalLancer.mybookingfieldtrip._sliders.SliderPagerAdapter;
import com.PersonalLancer.mybookingfieldtrip._sliders.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Beranda.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Beranda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Beranda extends Fragment {
    private ArrayList<ModelProfilUser> mArrayListUser;
    String id;
    LinearLayout btn_logout;
    public final static String TAG_ID_USER = "id";
    public final static String TAG_ID_EMAIL = "email";
    TextView txt_nama_akun,txt_email,txt_nama_user,txt_nomortelepon;
    ImageView btn_profile,btn_reservasi,btn_lihat_jadwal,btn_konfirmasi_payment,btn_waadmin;
    CardView btn_bookingtanggal, btnbungiadmin, btnjadawal,btnkonfirmasi;
    SharedPreferences sharedpreferences;
    public static final String BASE_URL = "http://universedeveloper.com/gudangandroid/";

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Beranda() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Beranda.
     */
    // TODO: Rename and change types and number of parameters
    public static Beranda newInstance(String param1, String param2) {
        Beranda fragment = new Beranda();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_beranda, container, false);
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        txt_nama_akun = rootView.findViewById(R.id.txt_nama_akun);
        txt_nama_user = rootView.findViewById(R.id.txt_nama_user);
        txt_email = rootView.findViewById(R.id.txt_email);
        txt_nomortelepon = rootView.findViewById(R.id.txt_nomortelepon);
        btn_logout = rootView.findViewById(R.id.btn_logout);
        sharedpreferences = getActivity().getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", "0");
        Toast.makeText(getActivity(), "ini id ke-"+ id, Toast.LENGTH_SHORT).show();


        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);
        setupSlider();

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

        btn_reservasi = rootView.findViewById(R.id.btn_reservasi);
        btn_reservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ListPaket.class);
                getActivity().startActivity(intent);
            }

        });

        btn_konfirmasi_payment = rootView.findViewById(R.id.btn_konfirmasi_payment);
        btn_konfirmasi_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), KonfirmasiPembayaran.class);
                getActivity().startActivity(intent);
            }

        });

        btn_waadmin = rootView.findViewById(R.id.btn_waadmin);
        btn_waadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentWhatsapp = new Intent("android.intent.action.MAIN");
                intentWhatsapp.setAction(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + "6285803000346" + "&text=Halo admin, Mau reservasi Outbound nih....";

                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                getActivity().startActivity(intentWhatsapp);
            }

        });

        btn_lihat_jadwal= rootView.findViewById(R.id.btn_lihat_jadwal);
        btn_lihat_jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ListLihatJadwal.class);
                getActivity().startActivity(intent);
            }

        });

        ambilProfilUser();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://gudangandroid.universedeveloper.com/fieldtripkandri/image/image-slider-1.jpg"));
        fragments.add(FragmentSlider.newInstance("http://gudangandroid.universedeveloper.com/fieldtripkandri/image/image-slider-2.jpg"));
        fragments.add(FragmentSlider.newInstance("http://gudangandroid.universedeveloper.com/fieldtripkandri/image/image-slider-3.jpg"));
        fragments.add(FragmentSlider.newInstance("http://gudangandroid.universedeveloper.com/fieldtripkandri/image/image-slider-4.jpg"));
        fragments.add(FragmentSlider.newInstance("http://gudangandroid.universedeveloper.com/fieldtripkandri/image/image-slider-5.jpg"));
        fragments.add(FragmentSlider.newInstance("http://gudangandroid.universedeveloper.com/fieldtripkandri/image/image-slider-6.jpg"));
        fragments.add(FragmentSlider.newInstance("http://gudangandroid.universedeveloper.com/fieldtripkandri/image/image-slider-7.jpg"));




        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
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
                txt_nama_user.setText(nama_lengkap);
                txt_nomortelepon.setText(nomor_hp);
                txt_email.setText(email);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}

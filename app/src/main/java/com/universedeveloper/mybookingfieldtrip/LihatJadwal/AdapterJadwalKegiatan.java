package com.universedeveloper.mybookingfieldtrip.LihatJadwal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.universedeveloper.mybookingfieldtrip.KonfirmasiPembayaran.AdapterKonfirmasiembayaran;
import com.universedeveloper.mybookingfieldtrip.KonfirmasiPembayaran.DetailKonfirmasi;
import com.universedeveloper.mybookingfieldtrip.KonfirmasiPembayaran.ModelKonfirmasiPembayaran;
import com.universedeveloper.mybookingfieldtrip.R;

import java.util.ArrayList;

public class AdapterJadwalKegiatan extends RecyclerView.Adapter<AdapterJadwalKegiatan.ViewHolder> implements Filterable {

    private ArrayList<ModelJadwalKegiatan> mArrayList;
    private ArrayList<ModelJadwalKegiatan> mFilteredList;
    private Context context;


    public AdapterJadwalKegiatan(Context context, ArrayList<ModelJadwalKegiatan> arrayList) {
        this.context = context;
        this.mArrayList = arrayList;
        this.mFilteredList = arrayList;

    }

    @NonNull
    @Override
    public AdapterJadwalKegiatan.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapterjadwalkegiatan, viewGroup, false);
        return new AdapterJadwalKegiatan.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJadwalKegiatan.ViewHolder viewHolder, int i) {
        viewHolder.txt_id_transaksi.setText(mFilteredList.get(i).getId_transaksi());
        ////viewHolder.txt_namalengkap.setText(mFilteredList.get(i).getNama_lengkap());
        viewHolder.txt_namasekolah.setText(mFilteredList.get(i).getNama_sekolah());
        /// viewHolder.txt_statuspesanan.setText((mFilteredList.get(i).getStatus_pesanan()));
        viewHolder.txt_tanggalbooking.setText(mFilteredList.get(i).getTanggal_booking());
    }

    @Override
    public int getItemCount()  {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<ModelJadwalKegiatan> filteredList = new ArrayList<>();

                    for (ModelJadwalKegiatan androidVersion : mArrayList) {

                        if (androidVersion.getTanggal_booking().toLowerCase().contains(charString) || androidVersion.getNama_sekolah().toLowerCase().contains(charString) ) {
                            filteredList.add(androidVersion);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ModelJadwalKegiatan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_namalengkap,txt_nomorkegiatan,txt_namasekolah,txt_tanggalbooking,txt_jumlahsiswa,txt_jumlahguru,txt_jumlahsupir,txt_totalbayar,
                txt_totalharga,txt_statuspesanan,txt_keterangan,txt_jenispaket,txt_id_transaksi, txt_id_user;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);


            txt_id_transaksi = (TextView) view.findViewById(R.id.txt_id_transaksi);
            ///txt_namalengkap = (TextView) view.findViewById(R.id.txt_namal);
            txt_namasekolah = (TextView)view.findViewById(R.id.txt_namasekolah);
            txt_tanggalbooking = (TextView)view.findViewById(R.id.txt_tanggalbooking);




            view.setOnClickListener(this);

            txt_tanggalbooking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent detail = new Intent(view.getContext(), DetailKonfirmasi.class);
/*
                    detail.putExtra("id_transaksi", txt_id_transaksi.getText());
                    detail.putExtra("id", txt_id_user.getText());
                    ///detail.putExtra("id_perusahaan", txt_id_perusahaan.getText());
                    detail.putExtra("namalengkap", txt_namalengkap.getText());
                    detail.putExtra("jenis_cetak", txt_jeniscetak.getText());
                    detail.putExtra("nomor_invoice", txt_invoice.getText());
                    detail.putExtra("lebar", txt_lebar.getText());
                    detail.putExtra("panjang", txt_panjang.getText());
                    detail.putExtra("jumlah_pesanan", txt_jumlahpesanan.getText());
                    detail.putExtra("jumlah_harga", txt_jumlahharga.getText());*/

/*                    detail.putExtra("id_pelamarmasuk", txt_id_pelamarmasuk.getText());
                    detail.putExtra("id_user", txt_id_user.getText());
                    detail.putExtra("id_perusahaan", txt_id_perusahaan.getText());
                    detail.putExtra("nama_perusahaan", txt_nama_perusahaan.getText());
                    detail.putExtra("id_lowongankerja", txt_id_lowongan.getText());
                    detail.putExtra("judul_lowongan", txt_judul_lowongan.getText());
                    detail.putExtra("deskripsi", txt_deskripsi.getText());
                    detail.putExtra("gaji", txt_gaji.getText());
                    detail.putExtra("deadline", txt_deadline.getText());
                    detail.putExtra("kategori", txt_kategori.getText());
                    detail.putExtra("status_pelamar", txt_statuspelamar.getText());
                    detail.putExtra("keterangan", txt_keterangan.getText());*/

                    view.getContext().startActivity(detail);
                }
            });
        }

        @Override
        public void onClick(View view) {
            Intent detail = new Intent(view.getContext(), DetailKonfirmasi.class);

         /*   detail.putExtra("id_transaksi", txt_id_transaksi.getText());
            detail.putExtra("id", txt_id_user.getText());
            ///detail.putExtra("id_perusahaan", txt_id_perusahaan.getText());
            detail.putExtra("namalengkap", txt_namalengkap.getText());
            detail.putExtra("jenis_cetak", txt_jeniscetak.getText());
            detail.putExtra("nomor_invoice", txt_invoice.getText());
            detail.putExtra("lebar", txt_lebar.getText());
            detail.putExtra("panjang", txt_panjang.getText());
            detail.putExtra("jumlah_pesanan", txt_jumlahpesanan.getText());
            detail.putExtra("jumlah_harga", txt_jumlahharga.getText());
            ///detail.putExtra("kategori", txt_kategori.getText());
            ///detail.putExtra("status_pelamar", txt_statuspelamar.getText());
            ///detail.putExtra("keterangan", txt_keterangan.getText());*/

            view.getContext().startActivity(detail);

        }
    }
}

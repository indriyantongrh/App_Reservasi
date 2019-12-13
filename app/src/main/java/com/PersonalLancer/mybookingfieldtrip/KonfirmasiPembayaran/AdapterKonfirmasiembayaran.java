package com.PersonalLancer.mybookingfieldtrip.KonfirmasiPembayaran;

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

import com.PersonalLancer.mybookingfieldtrip.R;

import java.util.ArrayList;

public class AdapterKonfirmasiembayaran extends RecyclerView.Adapter<AdapterKonfirmasiembayaran.ViewHolder> implements Filterable {

    private ArrayList<ModelKonfirmasiPembayaran> mArrayList;
    private ArrayList<ModelKonfirmasiPembayaran> mFilteredList;
    private Context context;


    public AdapterKonfirmasiembayaran(Context context, ArrayList<ModelKonfirmasiPembayaran> arrayList) {
        this.context = context;
        this.mArrayList = arrayList;
        this.mFilteredList = arrayList;

    }

    @NonNull
    @Override
    public AdapterKonfirmasiembayaran.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_konfirmasi_pembayaran, viewGroup, false);
        return new AdapterKonfirmasiembayaran.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKonfirmasiembayaran.ViewHolder viewHolder, int i) {
        viewHolder.txt_id_transaksi.setText(mFilteredList.get(i).getId_transaksi());
        ////viewHolder.txt_namalengkap.setText(mFilteredList.get(i).getNama_lengkap());
        viewHolder.txt_nomorkegiatan.setText(mFilteredList.get(i).getNomor_kegiatan());
        viewHolder.txt_namasekolah.setText(mFilteredList.get(i).getNama_sekolah());
        /// viewHolder.txt_statuspesanan.setText((mFilteredList.get(i).getStatus_pesanan()));
        viewHolder.txt_tanggalbooking.setText(mFilteredList.get(i).getTanggal_booking());  //untuk mengirim url gambar ke berbagai activity
        viewHolder.txt_jumlahsiswa.setText(mFilteredList.get(i).getJumlah_siswa());
        viewHolder.txt_jumlahguru.setText(mFilteredList.get(i).getJumlah_guru());
        viewHolder.txt_jumlahsupir.setText(mFilteredList.get(i).getJumlah_supir());
        viewHolder.txt_totalbayar.setText(mFilteredList.get(i).getTotal_bayar());
        ///  viewHolder.txt_gambar.setText(mFilteredList.get(i).getGambar());
        viewHolder.txt_totalharga.setText(mFilteredList.get(i).getTotal_harga());
        viewHolder.txt_statuspesanan.setText(mFilteredList.get(i).getStatus_transaksi());
        viewHolder.txt_keterangan.setText(mFilteredList.get(i).getKeterangan());
        viewHolder.txt_jenispaket.setText(mFilteredList.get(i).getJenis_paket());
        viewHolder.txt_id_user.setText(mFilteredList.get(i).getId());


    }

    @Override
    public int getItemCount() {
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

                    ArrayList<ModelKonfirmasiPembayaran> filteredList = new ArrayList<>();

                    for (ModelKonfirmasiPembayaran androidVersion : mArrayList) {

                        if (androidVersion.getNomor_kegiatan().toLowerCase().contains(charString) || androidVersion.getNama_sekolah().toLowerCase().contains(charString) ) {
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
                mFilteredList = (ArrayList<ModelKonfirmasiPembayaran>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_namalengkap,txt_nomorkegiatan,txt_namasekolah,txt_tanggalbooking,txt_jumlahsiswa,txt_jumlahguru,txt_jumlahsupir,txt_totalbayar,
                txt_totalharga,txt_statuspesanan,txt_keterangan,txt_jenispaket,txt_id_transaksi, txt_id_user,tvnomorkegiatan,txt_tanggalkegiatan;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);

            txt_id_user = (TextView) view.findViewById(R.id.txt_id_user);
            txt_id_transaksi = (TextView) view.findViewById(R.id.txt_id_transaksi);
            ///txt_namalengkap = (TextView) view.findViewById(R.id.txt_namal);
            txt_nomorkegiatan = (TextView)view.findViewById(R.id.txt_nomorkegiatan);
            txt_namasekolah = (TextView)view.findViewById(R.id.txt_namasekolah);
            txt_statuspesanan = (TextView)view.findViewById(R.id.txt_statuspesanan);
            txt_tanggalbooking = (TextView)view.findViewById(R.id.txt_tanggalbooking);
            ////image = (ImageView)view.findViewById(R.id.image);
            txt_jumlahsiswa = (TextView)view.findViewById(R.id.txt_jumlahsiswa);
            txt_jumlahguru = (TextView)view.findViewById(R.id.txt_jumlahguru);
            txt_jumlahsupir = (TextView)view.findViewById(R.id.txt_jumlahsupir);
            txt_totalbayar = (TextView) view.findViewById(R.id.txt_totalbayar);
            txt_totalharga = (TextView) view.findViewById(R.id.txt_totalharga);
            txt_keterangan = (TextView) view.findViewById(R.id.txt_keterangan);
            txt_jenispaket = (TextView) view.findViewById(R.id.txt_jenispaket);



            view.setOnClickListener(this);

            txt_nomorkegiatan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent detail = new Intent(view.getContext(), DetailKonfirmasi.class);

                    detail.putExtra("id_transaksi", txt_id_transaksi.getText());
                    detail.putExtra("id", txt_id_user.getText());
                    ///detail.putExtra("namalengkap", txt_namalengkap.getText());
                    detail.putExtra("nama_sekolah", txt_namasekolah.getText());
                    detail.putExtra("nomor_kegiatan", txt_nomorkegiatan.getText());
                    detail.putExtra("tanggal_booking", txt_tanggalbooking.getText());
                    detail.putExtra("jumlah_siswa", txt_jumlahsiswa.getText());
                    detail.putExtra("total_bayar", txt_totalbayar.getText());
                    detail.putExtra("keterangan", txt_keterangan.getText());




                    view.getContext().startActivity(detail);
                }
            });
        }

        @Override
        public void onClick(View view) {
            Intent detail = new Intent(view.getContext(), DetailKonfirmasi.class);

            detail.putExtra("id_transaksi", txt_id_transaksi.getText());
            detail.putExtra("id", txt_id_user.getText());
            ///detail.putExtra("namalengkap", txt_namalengkap.getText());
            detail.putExtra("nama_sekolah", txt_namasekolah.getText());
            detail.putExtra("nomor_kegiatan", txt_nomorkegiatan.getText());
            detail.putExtra("tanggal_booking", txt_tanggalbooking.getText());
            detail.putExtra("jumlah_siswa", txt_jumlahsiswa.getText());
            detail.putExtra("total_bayar", txt_totalbayar.getText());
            detail.putExtra("keterangan", txt_keterangan.getText());

            view.getContext().startActivity(detail);

        }
    }
}

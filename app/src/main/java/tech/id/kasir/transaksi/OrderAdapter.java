package tech.id.kasir.transaksi;

import android.app.Activity;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import tech.id.kasir.R;
import tech.id.kasir.database.DBHelper;
import tech.id.kasir.response_api.Menu;

public class OrderAdapter extends  RecyclerView.Adapter<OrderAdapter.ListViewHolder>{
    private OnItemClickCallback onItemClickCallback;
    DBHelper databaseKasir;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    Activity context;
//    ArrayList<ModelQty> modelQties = new ArrayList<ModelQty>();
    public static ArrayList<Menu> produks;
    private final ArrayList<Menu> produksArrayList;

    String no_meja;
    public OrderAdapter(ArrayList<Menu> produks, Activity context, String no_meja) {
        OrderAdapter.produks = produks;
        this.context = context;
        produksArrayList = new ArrayList<>(OrderAdapter.produks);
        databaseKasir = new DBHelper(context);
        this.no_meja = no_meja;
//        for (int position = 0; position < produks.size(); position++) {
//            ModelQty modelQty = new ModelQty();
//            modelQty.setId(String.valueOf(produks.get(position).getId()));
//            modelQty.setQty(String.valueOf(produks.get(position).getQty_produk()));
//            modelQties.add(modelQty);
//        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_row_produk_transaksi_dua, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Menu viewProduk = produks.get(holder.getAdapterPosition());

        holder.tvListProduk.setText(viewProduk.getNama_produk());
        holder.listHargaProduk.setText("Rp. "+viewProduk.getHarga());
        holder.tvKategori.setText(viewProduk.getKategori_nama());
//        holder.tvBarcode.setText(viewProduk.getKategorid_produk());
//        holder.ivListProduk.setImageBitmap(getImage(viewProduk.getGambar_produk()));
//        Toast.makeText(context, ""+viewProduk.getGambar_produk(), Toast.LENGTH_SHORT).show();
//        Glide.with(context).load(viewProduk.getGambar_produk()).into(holder.ivListProduk);

//        holder.tvJumlah.setText(String.valueOf(viewProduk.getQty_produk()));
//        Cursor cursorQty = databaseKasir.getDataProduks();
//        while (cursorQty.moveToNext()){
//            if (viewProduk.getId().equals(cursorQty.getString(0))){
//                if (cursorQty.getString(5).equals("0")){
//                    holder.etJumlah.setText("5");
//                }else{
//                    holder.etJumlah.setText(cursorQty.getString(5));
//                }
//
//            }
//        }

        holder.ivKurangJumlah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cursor cursorQty = databaseKasir.getDataTambahPesananMeja(viewProduk.getId_order(), viewProduk.getNoMeja(), viewProduk.getId());
//                cursorQty.moveToNext();
//                Integer hasilkurang = 0;
//                if (Integer.parseInt(cursorQty.getString(5))<1){
//                    Toast.makeText(context, cursorQty.getString(5), Toast.LENGTH_SHORT).show();
//                }else{
//                    hasilkurang = Integer.parseInt(cursorQty.getString(5)) - 1;
//                    databaseKasir.updateTambahPesananMeja(cursorQty.getInt(0), String.valueOf(hasilkurang));
//                    holder.tvJumlah.setText(String.valueOf(hasilkurang));
//                }




            }
        });

//        holder.cvListTransaksi.setOnClickListener(v -> {
//            onItemClickCallback.onItemClicked(produks.get(holder.getAdapterPosition()));
//            Cursor cursorQty = databaseKasir.getDataTambahPesananMeja(viewProduk.getId_order(), viewProduk.getNoMeja(), viewProduk.getId());
//            if (cursorQty.getCount()>0){
//                cursorQty.moveToNext();
//                holder.tvJumlah.setText(cursorQty.getString(5).toString());
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return produks.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView ivListProduk;
        ImageView  ivKurangJumlah, ivTambahJumlah;
        TextView tvListProduk, tvKategori, tvJumlah, listHargaProduk;
        CardView cvListTransaksi;
        LinearLayout llListTransaksi;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            ivListProduk = itemView.findViewById(R.id.ivListProduk);
            tvListProduk = itemView.findViewById(R.id.tvListProduk);
            ivKurangJumlah = itemView.findViewById(R.id.ivKurangJumlah);
            ivTambahJumlah = itemView.findViewById(R.id.ivTambahJumlah);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
            llListTransaksi = itemView.findViewById(R.id.llListTransaksi);
            listHargaProduk = itemView.findViewById(R.id.listHargaProduk);
            cvListTransaksi = itemView.findViewById(R.id.cvListTransaksi);

//            etJumlah.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    Cursor cursorQty = databaseKasir.getDataProduks();
//                    while (cursorQty.moveToNext()){
//                        if (cursorQty.getString(0).equals(produks.get(getAdapterPosition()).getId())){
//                            databaseKasir.updateTerimaBarang(produks.get(getAdapterPosition()).getId(), etJumlah.getText().toString().trim());
//                        }
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });

        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Menu data);
    }

    public Filter getFilter() {
        return userModelFilter;
    }

    private final Filter userModelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Menu> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(produksArrayList);
            }else{
                String filterpattern = constraint.toString().toLowerCase().trim();

                for (Menu item : produksArrayList){
                    if (item.getNama_produk().toLowerCase().contains(filterpattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            produks.clear();
            produks.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

package com.demoNhom11.DuAn01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demoNhom11.DuAn01.ModelTam.modeTop10;
import com.demoNhom11.DuAn01.R;

import java.util.ArrayList;

public class AdapterSachBanChay extends RecyclerView.Adapter<AdapterSachBanChay.Myholder> {
    Context context;
    ArrayList<modeTop10> listTop10 = new ArrayList<modeTop10>();

    public AdapterSachBanChay(Context context, ArrayList<modeTop10> listTop10) {
        this.context = context;
        this.listTop10 = listTop10;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_item_sach_ban_chay, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.tvSoLuong.setText("Số lượng đã bán: " + String.valueOf(listTop10.get(position).TongTien));
        holder.tvMaSach.setText("Mã sách: " + listTop10.get(position).maSach);
        holder.tvTop.setText("Top: " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return listTop10.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        public TextView tvMaSach, tvSoLuong, tvTop;

        public Myholder(View itemView) {
            super(itemView);
            tvTop = itemView.findViewById(R.id.tvTop);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        }
    }
}

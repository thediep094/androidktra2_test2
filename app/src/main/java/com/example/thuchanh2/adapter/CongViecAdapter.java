package com.example.thuchanh2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thuchanh2.R;
import com.example.thuchanh2.model.CongViec;

import java.util.List;

public class CongViecAdapter extends ArrayAdapter {
    Context context;
    List<CongViec> congViecs;
    private CongViecItemListener congViecItemListener;
    public CongViecAdapter(@NonNull Context context, @NonNull List congViecs) {
        super(context, R.layout.item, congViecs);

        this.context = context;
        this.congViecs = congViecs;
    }

    public void setCongViecItemListener(CongViecItemListener congViecItemListener) {
        this.congViecItemListener = congViecItemListener;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, null, true);
        TextView txtTen = v.findViewById(R.id.textViewTen);
        TextView txtNgay = v.findViewById(R.id.textViewNgay);
        TextView txtChuyenNganh = v.findViewById(R.id.textViewChuyenNganh);
        TextView txtKichHoat = v.findViewById(R.id.textViewKichHoat);
        TextView txtHocPhi = v.findViewById(R.id.textViewHocPhi);

        CongViec congViec = congViecs.get(position);
        txtTen.setText(congViec.getTen());
        txtHocPhi.setText((congViec.getHocPhi()));
        txtNgay.setText(congViec.getNgayBatDau());
        switch (congViec.getChuyenNganh()) {
            case 0:
                txtChuyenNganh.setText("CNTT");
                break;
            case 1:
                txtChuyenNganh.setText("Ke Toan");
                break;
            case 2:
                txtChuyenNganh.setText("Thiet Ke Do Hoa");
                break;
            case 3:
                txtChuyenNganh.setText("Quan Tri Kinh Doanh");
                break;
        }

        if (congViec.isKichHoat()) {
            txtKichHoat.setText("Kich hoat");
        } else {
            txtKichHoat.setText("Khong kich hoat");
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                congViecItemListener.onItemClicked(v, position);
            }
        });

        return v;
    }

    //Item clicked sent to Main
    public interface CongViecItemListener {
        void onItemClicked(View v, int position);
    }
}

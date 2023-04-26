package com.example.thuchanh2.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int ma;
    private String ten,hocPhi ,ngayBatDau;
    private int chuyenNganh;
    private boolean isKichHoat;

    public CongViec(int ma, String ten, String hocPhi,String ngayBatDau,  int chuyenNganh, boolean isKichHoat) {
        this.ma = ma;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.hocPhi = hocPhi;
        this.chuyenNganh = chuyenNganh;
        this.isKichHoat = isKichHoat;
    }

    public CongViec(String ten,String hocPhi, String ngayBatDau,  int chuyenNganh, boolean isKichHoat) {
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.hocPhi = hocPhi;
        this.chuyenNganh = chuyenNganh;
        this.isKichHoat = isKichHoat;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(String hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(int chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public boolean isKichHoat() {
        return isKichHoat;
    }

    public void setKichHoat(boolean kichHoat) {
        isKichHoat = kichHoat;
    }
}

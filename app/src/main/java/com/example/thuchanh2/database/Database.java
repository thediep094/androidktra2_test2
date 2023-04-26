package com.example.thuchanh2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.thuchanh2.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="th2.db.cv";
    private final static int DATABSE_VERSION=1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table congviec(" +
                "id integer primary key autoincrement," +
                "ten text," +
                "hocphi text," +
                "ngaybatdau date," +
                "chuyennganh integer," +
                "kichhoat text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCV(CongViec congViec) {
        String sql = "INSERT INTO congviec(ten,hocphi,ngaybatdau,chuyennganh,kichhoat)"+
                "VALUES(?,?,?,?,?)";
        String[] args = {congViec.getTen(), congViec.getHocPhi(), congViec.getNgayBatDau(),
                String.valueOf(congViec.getChuyenNganh()), String.valueOf(congViec.isKichHoat())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }

    public List<CongViec> getCongViecs(){
        List<CongViec> list=new ArrayList<>();
        String sql="select * " +
                "from congviec";
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,null);
        while(rs!=null && rs.moveToNext()){
            int ma = rs.getInt(0);
            String ten = rs.getString(1);
            String hocphi = rs.getString(2);
            String ngay = rs.getString(3);
            int chuyennganh = rs.getInt(4);
            boolean kichhoat = false;
            if (rs.getString(5).equalsIgnoreCase("true")) {
                kichhoat = true;
            }

            CongViec congViec=new CongViec(ma, ten, hocphi,ngay,  chuyennganh, kichhoat);
            list.add(congViec);
        }
        rs.close();
        return list;
    }

    public int updateCV(CongViec congViec){
        ContentValues values=new ContentValues();
        values.put("ten",congViec.getTen());
        values.put("hocphi",congViec.getHocPhi());
        values.put("ngaybatdau",congViec.getNgayBatDau());
        values.put("chuyennganh",congViec.getChuyenNganh());
        values.put("kichhoat",String.valueOf(congViec.isKichHoat()));
        String where="id=?";
        String[] agrs={Integer.toString(congViec.getMa())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("congviec",values,where,agrs);
    }

    public int deleteCV(int ma){
        String where="id=?";
        String[] agrs={Integer.toString(ma)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("congviec",where,agrs);
    }

    public List<CongViec> searchByKey(String key){
        List<CongViec> list = new ArrayList<>();
        String sql="select * " +
                "from congviec " +
                "where ten like ? or hocphi like ? " +
                "order by ngaybatdau";
        String[] agrs={"%"+key+"%","%"+key+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            int ma = rs.getInt(0);
            String ten = rs.getString(1);
            String hocphi = rs.getString(2);
            String ngay = rs.getString(3);
            int chuyennganh = rs.getInt(4);
            boolean kichhoat = false;
            if (rs.getString(5).equalsIgnoreCase("true")) {
                kichhoat = true;
            }

            CongViec congViec=new CongViec(ma, ten, hocphi, ngay, chuyennganh, kichhoat);
            list.add(congViec);
        }
        rs.close();
        return list;
    }

    public List<CongViec> searchByKey(String key, int tinhtrangKey){
        List<CongViec> list = new ArrayList<>();
        String sql="select * " +
                "from congviec " +
                "where (ten like ? or hocphi like ?) and kichhoat = ? " +
                "order by ngaybatdau";
        String[] agrs={"%"+key+"%","%"+key+"%", String.valueOf(tinhtrangKey)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            int ma = rs.getInt(0);
            String ten = rs.getString(1);
            String hocphi = rs.getString(2);
            String ngay = rs.getString(3);
            int chuyennganh = rs.getInt(4);
            boolean kichhoat = false;
            if (rs.getString(5).equalsIgnoreCase("true")) {
                kichhoat = true;
            }

            CongViec congViec=new CongViec(ma, ten, hocphi, ngay, chuyennganh, kichhoat);
            list.add(congViec);
        }
        rs.close();
        return list;
    }

}

package com.demoNhom11.DuAn01.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.demoNhom11.DuAn01.Model.NguoiDung;
import com.demoNhom11.DuAn01.SQLite.Dbhelper;

import java.util.ArrayList;

public class NguoiDungDAO {
    SQLiteDatabase db;
    Dbhelper dbh;

    public static String TAG = "NguoiDungDAO";
    public static String TABLE_NAME = "nguoiDung";

    public NguoiDungDAO(Context context) {
        dbh = new Dbhelper(context);
    }

    public int insertNguoiDung(NguoiDung nguoiDung) {
        db = dbh.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("userName", nguoiDung.userName);
        values.put("passWord", nguoiDung.passWord);
        values.put("phone", nguoiDung.phone);
        values.put("hoVaTen", nguoiDung.hoVaTen);

        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public ArrayList<NguoiDung> viewNguoiDung() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return getNguoiDung(sql);
    }

    public ArrayList<NguoiDung> getNguoiDung(String sql, String... selectionArgs) {
        db = dbh.getReadableDatabase();

        ArrayList<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                String userName = cursor.getString(0);
                String passWord = cursor.getString(1);
                String phone = cursor.getString(2);
                String hoVaTen = cursor.getString(3);
                NguoiDung nguoiDung = new NguoiDung(userName, passWord, phone, hoVaTen);
                listNguoiDung.add(nguoiDung);
            } while (cursor.moveToNext());
        }

        return listNguoiDung;
    }


    public int deleteNguoiDung(String userName) {
        db = dbh.getWritableDatabase();
        try {
            if (db.delete(TABLE_NAME, "userName=?", new String[]{userName}) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateThongTinNguoiDung(NguoiDung nguoiDung) {
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", nguoiDung.phone);
        values.put("hoVaTen", nguoiDung.hoVaTen);

        try {
            if (db.update(TABLE_NAME, values, "userName=?", new String[]{nguoiDung.userName}) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateMatKhauNguoiDung(NguoiDung nguoiDung) {
        db = dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("passWord", nguoiDung.passWord);
        int result = db.update(TABLE_NAME, values, "userName=?", new String[]{nguoiDung.userName});

        try {
            if (result == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, "NguoiDungDAO" + e.toString());
        }
        return 1;
    }
}

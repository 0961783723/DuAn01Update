package com.demoNhom11.DuAn01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
;

import com.demoNhom11.DuAn01.Adapter.AdapterSachBanChay;
import com.demoNhom11.DuAn01.DAO.HoaDonChiTietDAO;
import com.demoNhom11.DuAn01.DAO.HoaDonDAO;
import com.demoNhom11.DuAn01.DAO.SachDAO;
import com.demoNhom11.DuAn01.Model.HoaDon;
import com.demoNhom11.DuAn01.Model.HoaDonChiTiet;
import com.demoNhom11.DuAn01.Model.Sach;
import com.demoNhom11.DuAn01.ModelTam.modeTop10;
import com.demoNhom11.DuAn01.ModelTam.model;
import com.demoNhom11.DuAn01.ModelTam.model2;

import java.util.ArrayList;

public class SachBanChayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SachDAO sachDAO = new SachDAO(this);
    private ArrayList<Sach> listSachBanChay = new ArrayList<Sach>();

    private HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
    private ArrayList<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<HoaDonChiTiet>();

    ArrayList<model> modelArrayList = new ArrayList<model>();
    ArrayList<model2> model2ArrayList = new ArrayList<model2>();

    ArrayList<modeTop10> listTop10 = new ArrayList<modeTop10>();

    //..get list hoa don
    HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
    ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();
    ArrayList<String> listDate = new ArrayList<String>();
    String dateLayRa = "";
    //...get list hoa don

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);

        AnhXa();

        setToolbar();

        HamXuLyLayTongMoiMaSach();

        HamXuatSauKhiXuLy();

        AdapterTopTenBook();
    }


    private void AnhXa() {
        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void HamXuLyLayTongMoiMaSach() {
        listSachBanChay = sachDAO.viewSach();
        listHoaDonChiTiet = hoaDonChiTietDAO.viewHoaDonChiTiet();
        listHoaDonChiTiet.size();
        //Log.e("ListHDCT", "list--> " + listHoaDonChiTiet.size());


        for (int i = 0; i < listSachBanChay.size(); i++) {
            for (int j = 0; j < listHoaDonChiTiet.size(); j++) {
                if (listSachBanChay.get(i).maSach.equalsIgnoreCase(listHoaDonChiTiet.get(j).maSach)) {
                    modelArrayList.add(new model(listHoaDonChiTiet.get(j).maSach, listHoaDonChiTiet.get(j).soLuongMua));
                }
            }
            int tongTungLoai = 0;
            String maTungLoai = "";
            for (int n = 0; n < modelArrayList.size(); n++) {
                if (listSachBanChay.get(i).maSach.equalsIgnoreCase(modelArrayList.get(n).sach)) {
                    tongTungLoai += modelArrayList.get(n).tongTien;
                    maTungLoai = modelArrayList.get(n).sach;
                }
            }
            if (tongTungLoai > 0) {
                model2ArrayList.add(new model2(maTungLoai, tongTungLoai));
                //Log.e("tongTungLoai:", "--> " + "Ma: " + maTungLoai + ":" + tongTungLoai);
            }
        }

    }

    private void HamXuatSauKhiXuLy() {
        for (int i = 0; i < model2ArrayList.size(); i++) {
            //Log.e("kq", "-->" + model2ArrayList.get(i).loaiSach + ":" + model2ArrayList.get(i).tongTien);
            for (int j = i; j < model2ArrayList.size(); j++) {
                if (model2ArrayList.get(i).tongTien < model2ArrayList.get(j).tongTien) {
                    int bienTam = model2ArrayList.get(j).tongTien;
                    model2ArrayList.get(j).tongTien = model2ArrayList.get(i).tongTien;
                    model2ArrayList.get(i).tongTien = bienTam;
                    //...
                    String bien = model2ArrayList.get(j).loaiSach;
                    model2ArrayList.get(j).loaiSach = model2ArrayList.get(i).loaiSach;
                    model2ArrayList.get(i).loaiSach = bien;
                }
            }
        }
        getTop10();
    }

    private void getTop10() {
        for (int i = 0; i < 10; i++) {
            //Log.e("->", "_--?: " + model2ArrayList.get(i).loaiSach + ":" + model2ArrayList.get(i).tongTien);
            try {
                listTop10.add(new modeTop10(model2ArrayList.get(i).loaiSach, model2ArrayList.get(i).tongTien));
            } catch (Exception e) {
            }
        }
    }

    private void AdapterTopTenBook() {


        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        AdapterSachBanChay adapterSachBanChay = new AdapterSachBanChay(this, listTop10);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterSachBanChay);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter2, R.anim.anim_exit2);
    }
}


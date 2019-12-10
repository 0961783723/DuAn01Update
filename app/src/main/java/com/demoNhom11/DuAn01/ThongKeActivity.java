package com.demoNhom11.DuAn01;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.demoNhom11.DuAn01.DAO.HoaDonChiTietDAO;
import com.demoNhom11.DuAn01.DAO.HoaDonDAO;
import com.demoNhom11.DuAn01.Model.HoaDon;
import com.demoNhom11.DuAn01.Model.HoaDonChiTiet;
import com.demoNhom11.DuAn01.ModelTam.SupportThongke;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ThongKeActivity extends AppCompatActivity {

    private BarChart barChart;
    private Toolbar toolbar;
    private TextView tvTongDate, tvTongMonth, tvTongYear;

    //...get xu ly date
    private String dateNow, monthNow, yearNow, toDateNow;
    private String dateHoaDon, monthHoaDon, yearHoaDon, dateThanhToan;

    private ArrayList<SupportThongke> MangTachFromHd = new ArrayList<SupportThongke>();
    //...get xu ly date

    //..getListHoaDOnChiTiet
    private HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
    private ArrayList<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<HoaDonChiTiet>();
    //..getliStHoaDonChiTiet

    //..getHoaDon
    private HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
    private ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();
    //.getHoaDon

    //..
    private Double tongHoaDonDate = 0.0;
    private long tongHoaDonMonth = 0;
    private long tongHoaDonYear = 0;
    //...

    //..xy ly lay tong moi thang
    private ArrayList<String> thangDaLoc = new ArrayList<String>();
    private ArrayList<Long> dsDaXuLyTongMoiThang = new ArrayList<>();
    //..xy ly lay tong moi thang

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        AnhXa();
        setToolbar();

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolBar);
        barChart = findViewById(R.id.barchart);
        listHoaDonChiTiet = hoaDonChiTietDAO.viewHoaDonChiTiet();
        tvTongDate = findViewById(R.id.tvHomNay);
        tvTongMonth = findViewById(R.id.tvThangNay);
        tvTongYear = findViewById(R.id.tvNamNay);
        listHoaDon = hoaDonDAO.viewHoaDon();

        getToDay();
        XuLyGetDateMonthYearFromHoaDon();
        TongHoaDonHomNay();
        TongHoaDonThangNay();
        TongHoaDonNamNay();
        XuLyDuLieu();
        XuLyBieuDo();

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getToDay() {
        Date getToday = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        toDateNow = df.format(getToday);

        String SplitDate[] = toDateNow.split("/");
        dateNow = SplitDate[0].trim();
        monthNow = SplitDate[1].trim();
        yearNow = SplitDate[2].trim();
    }

    private void XuLyGetDateMonthYearFromHoaDon() {
        for (int i = 0; i < listHoaDon.size(); i++) {
            String mangChung[] = listHoaDon.get(i).ngayMua.split("/");
            MangTachFromHd.add(new SupportThongke(mangChung[0], mangChung[1], mangChung[2], listHoaDon.get(i).maHoadon));
        }
    }

    //..Ham xu ly lay ra tong hoa don cua ngay
    private void TongHoaDonHomNay() {
        for (int i = 0; i < MangTachFromHd.size(); i++) {
            for (int j = 0; j < listHoaDonChiTiet.size(); j++) {
                if (Integer.parseInt(MangTachFromHd.get(i).date) == Integer.parseInt(dateNow)
                        && Integer.parseInt(MangTachFromHd.get(i).month) == Integer.parseInt(monthNow)
                        && Integer.parseInt(MangTachFromHd.get(i).year) == Integer.parseInt(yearNow)
                        && MangTachFromHd.get(i).maHoaDon.equalsIgnoreCase(listHoaDonChiTiet.get(j).maHoaDon)) {
                    tongHoaDonDate += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                }
            }
        }
        //format tong tien
        String tongDate= NumberFormat.getInstance().format(tongHoaDonDate);
        tvTongDate.setText(tongDate + " vnđ");
    }

    //Ham xu ly lay ra tong hoa don cua thang
    private void TongHoaDonThangNay() {
        for (int i = 0; i < MangTachFromHd.size(); i++) {
            for (int j = 0; j < listHoaDonChiTiet.size(); j++) {
                if (Integer.parseInt(MangTachFromHd.get(i).month) == Integer.parseInt(monthNow)
                        && Integer.parseInt(MangTachFromHd.get(i).year) == Integer.parseInt(yearNow)
                        && MangTachFromHd.get(i).maHoaDon.equalsIgnoreCase(listHoaDonChiTiet.get(j).maHoaDon)) {
                    tongHoaDonMonth += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                }
            }
        }
        String tongMonth= NumberFormat.getInstance().format(tongHoaDonMonth);
        tvTongMonth.setText(tongMonth + " vnđ");
    }

    //Ham xy ly lay ra tong hoa don cua nam
    private void TongHoaDonNamNay() {
        for (int i = 0; i < MangTachFromHd.size(); i++) {
            for (int j = 0; j < listHoaDonChiTiet.size(); j++) {
                if (Integer.parseInt(MangTachFromHd.get(i).year) == Integer.parseInt(yearNow)
                        && MangTachFromHd.get(i).maHoaDon.equalsIgnoreCase(listHoaDonChiTiet.get(j).maHoaDon)) {
                    tongHoaDonYear += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                }
            }
        }
        String tongYear= NumberFormat.getInstance().format(tongHoaDonYear);
        tvTongYear.setText(tongYear + " vnđ");
    }

    private void XuLyDuLieu() {
        ArrayList<String> thang = new ArrayList<String>();
        //
        for (int i = 0; i < MangTachFromHd.size(); i++) {
            thang.add(MangTachFromHd.get(i).month);
        }
        //


        for (String element : thang) {
            // Check if element not exist in list, perform add element to list
            if (!thangDaLoc.contains(element)) {
                thangDaLoc.add(element);
            }
        }

        //..
        long tongthang = 0;
        long tam = 0;
        for (int c = 0; c < thangDaLoc.size(); c++) {
            Log.e("aaaa//", thangDaLoc.get(c) + "");
            for (int i = 0; i < MangTachFromHd.size(); i++) {
                for (int j = 0; j < listHoaDonChiTiet.size(); j++) {
                    if (Integer.parseInt(MangTachFromHd.get(i).month) == Integer.parseInt(thangDaLoc.get(c))
                            && Integer.parseInt(MangTachFromHd.get(i).year) == Integer.parseInt(yearNow)
                            && MangTachFromHd.get(i).maHoaDon.equalsIgnoreCase(listHoaDonChiTiet.get(j).maHoaDon)) {
                        switch (Integer.parseInt(thangDaLoc.get(c))) {
                            case 1:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 2:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 3:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 4:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 5:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 6:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 7:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 8:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 9:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 10:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 11:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            case 12:
                                if (tongthang != 0) {
                                    tongthang = 0;
                                }
                                tongthang += Double.parseDouble(listHoaDonChiTiet.get(j).thanhTien);
                                tam += tongthang;
                                tongthang = tam;
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            tam = 0;
            dsDaXuLyTongMoiThang.add(tongthang);
            //Log.e("aaaa", tongthang + "");
        }

//        for (int i = 0; i < dsDaXuLyTongMoiThang.size(); i++) {
//            Log.e("bbbb", "-->" + dsDaXuLyTongMoiThang.get(i));
//        }
        //..

    }

    private void XuLyBieuDo() {

        ArrayList<String> labels = new ArrayList<String>();
        try {
            for (int k = 0; k < thangDaLoc.size(); k++) {
                labels.add("Tháng: " + thangDaLoc.get(k));
            }

        } catch (Exception e) {

        }

        // create BarEntry for Bar Group 1
        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
        try {

            for (int k = 0; k < dsDaXuLyTongMoiThang.size(); k++) {
                bargroup1.add(new BarEntry((dsDaXuLyTongMoiThang.get(k) * 100) / tongHoaDonYear, k));
            }
        } catch (Exception e) {

        }

        BarDataSet bardataset = new BarDataSet(bargroup1, ": tháng");

        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of labels into chart
        barChart.setDescription("Tỉ lệ % Tổng tiền theo tháng");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(3000);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter2, R.anim.anim_exit2);
    }

}

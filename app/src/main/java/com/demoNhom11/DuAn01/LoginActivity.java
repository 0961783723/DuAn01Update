package com.demoNhom11.DuAn01;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.demoNhom11.DuAn01.DAO.NguoiDungDAO;
import com.demoNhom11.DuAn01.Model.NguoiDung;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    //dialog
    private EditText tenDangNhap, matKhau, matKhauXacNhan, soDienThoai, hoVaTen;
    //dialog

    private EditText edtTenDangNhap, edtMatKhau;
    private Button btDangNhap, btDk;
    private ImageView logoLogin;
    private CheckBox chkGhiNho;

    private SharedPreferences sharedPreferences;

    //goi list nguoi dung
    private NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
    private ArrayList<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
    //goi list nguoi dung

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //...
        find_View_ById();
        //...
        animationLogoLogin();

        //...
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        //..get date Preferences
        try {
            edtTenDangNhap.setText(sharedPreferences.getString("tenDangNhap", ""));
            edtMatKhau.setText(sharedPreferences.getString("matKhau", ""));
            chkGhiNho.setChecked(sharedPreferences.getBoolean("kiemTra", false));
        } catch (Exception e) {
            Log.e("this is problem", "-->" + e.toString());
        }
        //...

        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evenDangNhap();
                animationButtonZoomLogin();
            }
        });

        btDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evenDangKi();

                animationButtonZoomHuy();
            }
        });

    }

    private void evenDangKi() {
        //dialog
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inf = LoginActivity.this.getLayoutInflater();
        View view1 = inf.inflate(R.layout.dialog_for_dk_nguoidung, null);

        tenDangNhap = view1.findViewById(R.id.edtTenDangNhap);
        matKhau = view1.findViewById(R.id.edtMatKhau);
        matKhauXacNhan = view1.findViewById(R.id.edtMatKhauXacNhan);
        soDienThoai = view1.findViewById(R.id.edtSoDienThoai);
        hoVaTen = view1.findViewById(R.id.edtHoVaTen);

        alertDialog.setView(view1);

        animationDialogItem();

        alertDialog.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String edtTenDangNhapDK = tenDangNhap.getText().toString().trim();
                String edtMatKhauDK = matKhau.getText().toString().trim();
                String edtMatKhauXacNhan = matKhauXacNhan.getText().toString().trim();
                String edtSoDienThoai = soDienThoai.getText().toString().trim();
                String edtHoVaTen = hoVaTen.getText().toString().trim();

                //.., kiem tra ten dang nhap co trung nhau hay khong
                listNguoiDung = nguoiDungDAO.viewNguoiDung();
                boolean kt = false;
                for (NguoiDung nguoiDung : listNguoiDung) {
                    if (edtTenDangNhapDK.equals(nguoiDung.userName)) {
                        kt = true;
                        break;
                    } else {
                        kt = false;
                    }
                }
                //...kiem tra ten dang nhap co trung nhay hay khong

                //sqlite
                NguoiDung nguoiDung = new NguoiDung(edtTenDangNhapDK, edtMatKhauDK, edtSoDienThoai, edtHoVaTen);
                if (!edtMatKhauDK.equals(edtMatKhauXacNhan)
                        || edtTenDangNhapDK.equals("")
                        || edtMatKhauDK.equals("")
                        || edtSoDienThoai.equals("")
                        || edtHoVaTen.equals("")
                        || kt == true
                ) {

                    evenDangKi();
                    tenDangNhap.setText(edtTenDangNhapDK);
                    matKhau.setText(edtMatKhauDK);
                    matKhauXacNhan.setText(edtMatKhauXacNhan);
                    soDienThoai.setText(edtSoDienThoai);
                    hoVaTen.setText(edtHoVaTen);
                    Toasty.error(LoginActivity.this, "Lỗi!", Toast.LENGTH_SHORT, true).show();

                } else {

                    nguoiDungDAO.insertNguoiDung(nguoiDung);

                    edtTenDangNhap.setText("");
                    edtMatKhau.setText("");
                    edtTenDangNhap.setText(edtTenDangNhapDK);
                    edtMatKhau.setText(edtMatKhauDK);

                    listNguoiDung = nguoiDungDAO.viewNguoiDung();
                    Toasty.success(LoginActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT, true).show();

                }
                //sqlite

                //TastyToast.makeText(getContext(), "Đ !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            }
        });
        alertDialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toasty.success(LoginActivity.this, "Đã hủy!", Toast.LENGTH_SHORT, true).show();
            }
        });
        alertDialog.show();
        //dialog
    }

    private void evenDangNhap() {

        //...
        String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();
        boolean kt = false;
        for (NguoiDung nguoiDung : listNguoiDung) {
            if (tenDangNhap.equals(nguoiDung.userName) && matKhau.equals(nguoiDung.passWord)) {
                kt = true;
                break;
            } else {
                kt = false;
            }
        }
        if (kt == true) {

            //...put date and kt data tuong tac voi nguoi dung
            if (chkGhiNho.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("tenDangNhap", tenDangNhap);
                editor.putString("matKhau", matKhau);
                editor.putBoolean("kiemTra", true);
                editor.commit();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("tenDangNhap");
                editor.remove("matKhau");
                editor.remove("kiemTra");
                editor.commit();
            }
            //...put date and kt data tuong tac voi nguoi dung

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            finish();
            Toasty.success(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT, true).show();

        } else if (tenDangNhap.equals("Admin") && matKhau.equals("123")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            finish();
            Toasty.success(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT, true).show();

        } else {
            Toasty.error(LoginActivity.this, "Lỗi!", Toast.LENGTH_SHORT, true).show();
            edtTenDangNhap.setText("");
            edtMatKhau.setText("");
        }
        //...

    }

    private void find_View_ById() {
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        btDangNhap = findViewById(R.id.btnDangNhap);
        btDk = findViewById(R.id.btnDk);
        logoLogin = findViewById(R.id.logoLogin);
        chkGhiNho = findViewById(R.id.chkGhiNho);

        //get listNguoiDung
        listNguoiDung = nguoiDungDAO.viewNguoiDung();
        //get listNguoiDung

    }

    private void animationLogoLogin() {
        Animation animationLogo = AnimationUtils.loadAnimation(this, R.anim.anim_logo_login);
        logoLogin.startAnimation(animationLogo);
    }

    private void animationButtonZoomLogin() {
        Animation animationBT = AnimationUtils.loadAnimation(this, R.anim.anim_zoom_button_login);
        btDangNhap.startAnimation(animationBT);
    }

    private void animationButtonZoomHuy() {
        Animation animationBT = AnimationUtils.loadAnimation(this, R.anim.anim_zoom_button_login);
        btDk.startAnimation(animationBT);
    }

    private void animationDialogItem() {
        Animation tenDangNhapAnim = AnimationUtils.loadAnimation(this, R.anim.anim_dialog_item_one);
        tenDangNhap.startAnimation(tenDangNhapAnim);

        Animation matKhauAnim = AnimationUtils.loadAnimation(this, R.anim.anim_dialog_item_two);
        matKhau.startAnimation(matKhauAnim);

        Animation matKhauXacNhanAnim = AnimationUtils.loadAnimation(this, R.anim.anim_dialog_item_three);
        matKhauXacNhan.startAnimation(matKhauXacNhanAnim);

        Animation soDienThoaiAnim = AnimationUtils.loadAnimation(this, R.anim.anim_dialog_item_four);
        soDienThoai.startAnimation(soDienThoaiAnim);

        Animation hoVaTenAnim = AnimationUtils.loadAnimation(this, R.anim.anim_dialog_item_five);
        hoVaTen.startAnimation(hoVaTenAnim);
    }

}


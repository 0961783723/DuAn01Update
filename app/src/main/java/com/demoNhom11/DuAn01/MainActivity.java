package com.demoNhom11.DuAn01;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgNguoiDung, imgTheLoai, imgSach, imgHoaDon, imgSachBanChay, imgThongKe;
    private CardView cardOne, cardTwo, cardThree, cardFour, cardFive, cardSix;
    private TextView tvName01, tvName02;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //...
        AnhXa();
        //...

        animationCardView();

        imgNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = tvName01.getText().toString();
                Bundle mBundle = new Bundle();
                mBundle.putString("user", user);
                Intent intent = new Intent(MainActivity.this, NguoidungActivity.class);
                intent.putExtras(mBundle);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);

            }
        });

        imgTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TheLoaiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });

        imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sach = tvName02.getText().toString();
                Bundle mBundle = new Bundle();
                mBundle.putString("book", sach);
                Intent intent = new Intent(MainActivity.this, SachActivity.class);
                intent.putExtras(mBundle);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });

        imgHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HoaDonActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });

        imgSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SachBanChayActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);

            }
        });

        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThongKeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });
    }

    private void AnhXa() {
        imgNguoiDung = findViewById(R.id.imgNguoiDung);
        imgTheLoai = findViewById(R.id.imgTheLoai);
        imgSach = findViewById(R.id.imgSach);
        imgHoaDon = findViewById(R.id.imgHoaDon);
        imgSachBanChay = findViewById(R.id.imgSachBanChay);
        imgThongKe = findViewById(R.id.imgThongKe);

        cardOne = findViewById(R.id.cardOne);
        cardTwo = findViewById(R.id.cardTwo);
        cardThree = findViewById(R.id.cardThree);
        cardFour = findViewById(R.id.cardFour);
        cardFive = findViewById(R.id.cardFive);
        cardSix = findViewById(R.id.cardSix);

        tvName01 = findViewById(R.id.tvName01);
        tvName02 = findViewById(R.id.tvName02);

    }

    private void animationCardView() {
        Animation one = AnimationUtils.loadAnimation(this, R.anim.anim_card_one);
        cardOne.startAnimation(one);

        Animation two = AnimationUtils.loadAnimation(this, R.anim.anim_card_two);
        cardTwo.startAnimation(two);

        Animation three = AnimationUtils.loadAnimation(this, R.anim.anim_card_three);
        cardThree.startAnimation(three);

        Animation four = AnimationUtils.loadAnimation(this, R.anim.anim_card_four);
        cardFour.startAnimation(four);

        Animation five = AnimationUtils.loadAnimation(this, R.anim.anim_card_five);
        cardFive.startAnimation(five);

        Animation six = AnimationUtils.loadAnimation(this, R.anim.anim_card_six);
        cardSix.startAnimation(six);
    }
}

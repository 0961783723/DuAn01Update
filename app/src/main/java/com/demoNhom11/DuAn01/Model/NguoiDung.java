package com.demoNhom11.DuAn01.Model;

public class NguoiDung {
    public String userName;
    public String passWord;
    public String phone;
    public String hoVaTen;


    public NguoiDung(String userName, String phone, String hoVaTen) {
        this.userName = userName;
        this.phone = phone;
        this.hoVaTen = hoVaTen;
    }

    public NguoiDung(String userName, String passWord, String phone, String hoVaTen) {
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.hoVaTen = hoVaTen;
    }

    public NguoiDung(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

}

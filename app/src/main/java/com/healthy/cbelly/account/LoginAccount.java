package com.healthy.cbelly.account;

/**
 * Created by chan1park on 2017. 10. 24..
 */

public class LoginAccount {

    public int colum;
    public String name;
    public String id;
    public String pw;
    public String birth;
    public String address;
    public String center;
    public String weight;
    public String tall;
    public String waist;
    public String target_weight;
    public String photo;

    @Override
    public String toString() {
        return "LoginAccount{" +
                "colum=" + colum +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", birth='" + birth + '\'' +
                ", address='" + address + '\'' +
                ", center='" + center + '\'' +
                ", weight='" + weight + '\'' +
                ", tall='" + tall + '\'' +
                ", waist='" + waist + '\'' +
                ", target_weight='" + target_weight + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public void clear() {
        colum = -1;
        name = "";
        id = "";
        pw = "";
        birth = "";
        address = "";
        center = "";
        weight = "";
        tall = "";
        waist = "";
        target_weight = "";
        photo = "";
    }

}

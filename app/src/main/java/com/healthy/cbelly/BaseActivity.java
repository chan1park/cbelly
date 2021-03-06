package com.healthy.cbelly;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.healthy.cbelly.account.JoinInfo;
import com.healthy.cbelly.account.LoginAccount;
import com.healthy.cbelly.database.DB;
import com.healthy.cbelly.database.DBOpenHelper;
import com.healthy.cbelly.util.LogUtil;

/**
 * Created by chan1park on 2017. 10. 24..
 */

public class BaseActivity extends AppCompatActivity {

    public Context mContext;

    public Toolbar mToolbar;

    public static DBOpenHelper dbOpenHelper;
    public static Cursor cursor;

    public static LoginAccount loginAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public void initDB(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
        dbOpenHelper.open();
    }

    public void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        supportInvalidateOptionsMenu();
    }

    public void setLoginaAccount(LoginAccount vo) {
        if(null == loginAccount) loginAccount = new LoginAccount();
        loginAccount.colum = vo.colum;
        loginAccount.name = vo.name;
        loginAccount.pw = vo.pw;
        loginAccount.birth = vo.birth;
        loginAccount.address = vo.address;
        loginAccount.center = vo.center;
        loginAccount.weight = vo.weight;
        loginAccount.tall = vo.tall;
        loginAccount.waist = vo.waist;
        loginAccount.target_weight = vo.target_weight;
        loginAccount.photo = vo.photo;
    }

    public void doWhileCursorToArray() {

        cursor = null;
        cursor = dbOpenHelper.getAllColumns();
        LogUtil.e("Base", "COUNT = " + cursor.getCount());

        while (cursor.moveToNext()) {
            LogUtil.e("Base",
                    cursor.getInt(cursor.getColumnIndex("_id")) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.NAME)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.ID)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.PW)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.BIRTH)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.ADDRESS)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.CENTER)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.WEIGHT)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.TALL)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.WAIST)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.TARGET_WEIGHT)) +
                            cursor.getString(cursor.getColumnIndex(DB.CreateDB.PHOTO)));
        }
        cursor.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

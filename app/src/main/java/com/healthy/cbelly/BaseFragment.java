package com.healthy.cbelly;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healthy.cbelly.account.LoginAccount;
import com.healthy.cbelly.database.DB;
import com.healthy.cbelly.database.DBOpenHelper;
import com.healthy.cbelly.util.LogUtil;

/**
 * Created by chan1park on 2017. 10. 24..
 */

public class BaseFragment extends Fragment {

    public Context mContext;

    public Toolbar mToolbar;

    public static DBOpenHelper dbOpenHelper;
    public static Cursor cursor;

    public static LoginAccount loginAccount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void initDB(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
        dbOpenHelper.open();
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
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

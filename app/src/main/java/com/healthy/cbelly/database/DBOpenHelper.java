package com.healthy.cbelly.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by parkchanil on 2017. 11. 2..
 */

public class DBOpenHelper {
    private static final String DATABASE_NAME = "addressbook.db";
    private static final int DATABASE_VERSION = 3;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB.CreateDB._CREATE);

        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DB.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public DBOpenHelper(Context context){
        this.mCtx = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String name, String id, String pw, String birth, String addr, String center,
                             String weight, String tall, String waist, String target_weight, String photo){
        ContentValues values = new ContentValues();
        values.put(DB.CreateDB.NAME, name);
        values.put(DB.CreateDB.ID, id);
        values.put(DB.CreateDB.PW, pw);
        values.put(DB.CreateDB.BIRTH, birth);
        values.put(DB.CreateDB.ADDRESS, addr);
        values.put(DB.CreateDB.CENTER, center);
        values.put(DB.CreateDB.WEIGHT, weight);
        values.put(DB.CreateDB.TALL, tall);
        values.put(DB.CreateDB.WAIST, waist);
        values.put(DB.CreateDB.TARGET_WEIGHT, target_weight);
        values.put(DB.CreateDB.PHOTO, photo);
        return mDB.insert(DB.CreateDB._TABLENAME, null, values);
    }

    // Update DB
    public boolean updateColumn(String name, String id, String pw, String birth, String addr, String center, String weight, String tall, String waist,
                                String target_weight, String photo){
        ContentValues values = new ContentValues();
        values.put(DB.CreateDB.NAME, name);
        values.put(DB.CreateDB.ID, id);
        values.put(DB.CreateDB.PW, pw);
        values.put(DB.CreateDB.BIRTH, birth);
        values.put(DB.CreateDB.ADDRESS, addr);
        values.put(DB.CreateDB.CENTER, center);
        values.put(DB.CreateDB.WEIGHT, weight);
        values.put(DB.CreateDB.TALL, tall);
        values.put(DB.CreateDB.WAIST, waist);
        values.put(DB.CreateDB.TARGET_WEIGHT, target_weight);
        values.put(DB.CreateDB.PHOTO, photo);
        return mDB.update(DB.CreateDB._TABLENAME, values, "_id="+id, null) > 0;
    }

    // Delete ID
    public boolean deleteColumn(long id){
        return mDB.delete(DB.CreateDB._TABLENAME, "_id="+id, null) > 0;
    }

    public boolean deleteColumnID(String id){
        return mDB.delete(DB.CreateDB._TABLENAME, "ID="+id, null) > 0;
    }
    // Delete Contact
    public boolean deleteColumn(String number){
        return mDB.delete(DB.CreateDB._TABLENAME, "contact="+number, null) > 0;
    }

    /*모든 컬럼 삭*/
    public void deleteAllColumns() {
        mDB.execSQL("delete from "+DB.CreateDB._TABLENAME);
    }

    // Select All
    public Cursor getAllColumns(){
        return mDB.query(DB.CreateDB._TABLENAME, null, null, null, null, null, null);
    }

    // ID 컬럼 얻어 오기
    public Cursor getColumn(long id){
        Cursor c = mDB.query(DB.CreateDB._TABLENAME, null,
                "_id="+id, null, null, null, null);
        if(c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }
    public Cursor getColumn(String id){
        Cursor c = mDB.query(DB.CreateDB._TABLENAME, null,
                DB.CreateDB.ID+"="+id, null, null, null, null);
        if(c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }

    // 이름 검색 하기 (rawQuery)
    public Cursor getMatchName(String name){
        Cursor c = mDB.rawQuery( "select * from address where name=" + "'" + name + "'" , null);
        return c;
    }

    // 아이디 검색 하기 (rawQuery)
    public Cursor getMatchID(String id){
        Cursor c = mDB.rawQuery( "select * from address where id=" + "'" + id + "'" , null);
        return c;
    }

    // 생년월일 검색 하기 (rawQuery)
    public Cursor getMatchBirth(String birth){
        Cursor c = mDB.rawQuery( "select * from address where birth=" + "'" + birth + "'" , null);
        return c;
    }
}

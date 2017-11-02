package com.healthy.cbelly.database;

import android.provider.BaseColumns;

/**
 * Created by parkchanil on 2017. 11. 2..
 */

public class DB {

    public static final class CreateDB implements BaseColumns {

        public static final String _TABLENAME = "address";

        public static final String NAME = "name";
        public static final String ID = "id";
        public static final String PW = "pw";
        public static final String BIRTH="birth";
        public static final String ADDRESS="address";
        public static final String CENTER="center";
        public static final String WEIGHT="weight";
        public static final String TALL="tall";
        public static final String WAIST="waist";
        public static final String TARGET_WEIGHT="target_weight";
        public static final String PHOTO="photo";


        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +NAME+" text not null , "
                        +ID+" text not null , "
                        +PW+" text not null , "
                        +BIRTH+" text not null , "
                        +ADDRESS+" text not null , "
                        +CENTER+" text not null , "
                        +WEIGHT+" text not null , "
                        +TALL+" text not null , "
                        +WAIST+" text not null , "
                        +TARGET_WEIGHT+" text not null , "
                        +PHOTO+" text not null );";
    }
}

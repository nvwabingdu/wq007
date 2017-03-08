package com.example.administrator.intent.F3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/3/5.
 */
public class DB_M extends SQLiteOpenHelper{

    public static final String table_name="info";

    public DB_M(Context context) {
        super(context, "coll.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  table_sql="create table "+table_name+"( _id  integer primary key autoincrement," +
                " title varchar, url varchar,yes_no varchar)";
        db.execSQL(table_sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

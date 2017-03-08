package com.example.administrator.intent.F3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/5.
 */
public class DB_C {

    private User u;
    private SQLiteDatabase db;
    public static ArrayList<User> list1=new ArrayList<>();

    public DB_C(Context context) {
        db = new DB_M(context).getWritableDatabase();
    }

    //添加
    public void add(String title, String url, String yes_no) {
        String sql = "insert into " + DB_M.table_name + " values (null,' " + title
                + " ','" + url + " ','" + yes_no + " ')";
        db.execSQL(sql);
    }

    //更新
//    public void update(String name, int count) {
//        String sql = "UPDATE " + DB_M.table_name + " SET count='" + count + " ' WHERE name ='"+name+"'";
//        db.execSQL(sql);
//    }

    //删除
    public void deleteAll(ArrayList<User> list) {
        for (User u : list) {
            if (u.getYes_no().equalsIgnoreCase("否")) {
                String sql = "delete from " + DB_M.table_name + " where yes_no='" + u.getYes_no() + "'";
                db.execSQL(sql);
            }
        }
    }

    //查看
    public ArrayList<User> showAll() {
        ArrayList<User> list = new ArrayList<User>();
        String sql = "select distinct * from " + DB_M.table_name;

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(new User(cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("url")),
                    cursor.getString(cursor.getColumnIndex("yes_no"))
            ));
        }
        cursor.close();
        list1=list;
        return list;
    }

}

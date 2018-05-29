package com.walke.dbdemo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by walke.Z on 2018/5/29.
 */

public class OriginDaoTest {

    private DBHelper mDBHelper;

    public OriginDaoTest(Context context) {
        mDBHelper = DBHelper.getInstance(context);
    }

    public void add(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'zhangsan','170')");
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'lisi','165')");
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'wangwu','175')");
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'zhaoliu','160')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void queryAll(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + DBHelper.USER_TABLE, null);
//        Cursor cursor = db.rawQuery("select * from " + DBHelper.USER_TABLE, new String[]{"name"});
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int anInt = cursor.getInt(0);
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                Log.i("walke--OriginDaoTest", "queryAll:------> name = "+name+"  id="+id);
            }
            cursor.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

}

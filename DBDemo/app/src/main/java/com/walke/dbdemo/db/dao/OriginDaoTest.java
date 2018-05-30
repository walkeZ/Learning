package com.walke.dbdemo.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.walke.dbdemo.db.DBHelper;

/**
 * Created by walke.Z on 2018/5/29.
 */

public class OriginDaoTest {

    private DBHelper mDBHelper;

    public OriginDaoTest(Context context) {
        mDBHelper = DBHelper.getInstance(context);
    }

    public DBHelper getDBHelper() {
        return mDBHelper;
    }

    public void add(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'zhangsan','170')");
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'lisi','165')");
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'wangwu','175')");
            db.execSQL("insert into "+DBHelper.USER_TABLE+" values(null,'zhaoliu','160')");
        } catch (SQLException e) {//当升级后(添加字段weight)，在执行这个会报错：table USER has 4 columns but 3 values were supplied (code 1)，已经4个字段了
            e.printStackTrace();
            Log.e("walke: OriginDaoTest", "update: ----->exception: ", e);
        }

    }

    /** 条件查询
     *      Cursor c = db.rawQuery("select * from user【这是表名】 where username=? and password = ?",new Stirng[]{"用户名","密码"});
     *
     *      db.query("表名", new String[]{"字段1，字段2"}, "条件1=? and 条件2=?", new String[]{"条件1的值，条件2的值"},null,null,null)
     * @param name
     */
    public void queryByName(String name){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + DBHelper.USER_TABLE +" where name=?", new String[]{name});
            cursor.moveToFirst();
            String name0 = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int id0 = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            int tall0 = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
            Log.i("walke: OriginDaoTest", "queryByName:------> name0 = "+name0+"  id0 = "+id0+"  tall0 = "+tall0+" cm ");
            while (cursor.moveToNext()){
                String n = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int anInt = cursor.getInt(0);
                int anInt1 = cursor.getInt(1);
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int tall = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
                Log.i("walke: OriginDaoTest", "queryByName:------> name = "+n+"  id = "+id+"  tall = "+tall+"cm -------------- anInt0 = "+anInt+"  anInt1: "+anInt1);
            }
            Log.i("walke: OriginDaoTest", "queryByName:------> \n");
            cursor.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("walke: OriginDaoTest", "queryByName: ----->exception: ", e);
        }

    }

    public void queryAll(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + DBHelper.USER_TABLE, null);
//        Cursor cursor = db.rawQuery("select * from " + DBHelper.USER_TABLE, new String[]{"name"});
            boolean b = cursor.moveToFirst();
            if (b) {
                String name0 = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int id0 = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int tall0 = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
                Log.i("walke: OriginDaoTest", "queryAll:------> name0 = " + name0 + "  id0 = " + id0 + "  tall0 = " + tall0 + " cm ");
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    int anInt = cursor.getInt(0);
                    int anInt1 = cursor.getInt(1);
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int tall = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
                    Log.i("walke: OriginDaoTest", "queryAll:------> name = " + name + "  id = " + id + "  tall = " + tall + "cm -------------- anInt0 = " + anInt + "  anInt1: " + anInt1);
                }
            }
            Log.i("walke: OriginDaoTest", "queryAll:------> cursor.moveToFirst "+b+"\n");
            cursor.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("walke: OriginDaoTest", "update: ----->exception: ", e);
        }

    }

    public void update(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            db.execSQL("update "+DBHelper.USER_TABLE+" set tall=? where name='lisi'",new Object[]{180});
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("walke: OriginDaoTest", "update: ----->exception: ", e);
        }
    }
    public void deleteOne(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            db.execSQL("delete from "+DBHelper.USER_TABLE+" where name=?",new Object[]{"lisi"});
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("walke: OriginDaoTest", "deleteOne: ----->exception: ", e);
        }
    }

    /**
     * https://blog.csdn.net/qq_26462135/article/details/74295905
     * 当 SQLite数据库中包含自增列时，会自动建立一个名为 sqlite_sequence 的表。这个表包含两个列：name和seq。
     * name记录自增列所在的表，seq记录当前序号（下一条记录的编号就是当前序号加1）。
     * 如果想把某个自增列 的序号归零，只需要修改 sqlite_sequence表就可以了。
     */
    public void deleteAll(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            db.execSQL("delete from "+DBHelper.USER_TABLE);
            //将所有表的自增列都归零，直接清空sqlite_sequence表就可以了
            db.execSQL("delete from sqlite_sequence WHERE name = '"+DBHelper.USER_TABLE+"'");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("walke: OriginDaoTest", "deleteOne: ----->exception: ", e);
        }
    }


}

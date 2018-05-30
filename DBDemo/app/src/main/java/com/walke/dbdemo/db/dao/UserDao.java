package com.walke.dbdemo.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.walke.dbdemo.db.DBHelper;

/**
 * Created by walke.Z on 2018/5/30.
 *  https://blog.csdn.net/sinat_29962405/article/details/50167611
 */

public class UserDao {

    private DBHelper mDBHelper;

    public UserDao(Context context) {
        mDBHelper = DBHelper.getInstance(context);
    }

    public void add(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("id",10);//第二次添加失败，原因：已存在  报错： PRIMARY KEY must be unique (code 19)[主键必须唯一]
            values.put("name","Aaa");
            values.put("tall",166);
            values.put("weight",58.45);
            long insert = db.insert(DBHelper.USER_TABLE, null, values);// -1为失败，其他返回id
            values.clear();
            values.put("name","Bbb");
            values.put("tall",186);
            values.put("weight",56.30);
            long insert2 = db.insertOrThrow(DBHelper.USER_TABLE, null, values);//
            values.clear();
            values.put("name","Ccc");
            values.put("tall",176);
            values.put("weight",60);
            long insert3 = db.insert(DBHelper.USER_TABLE, null, values);
            Log.i("walke: UserDao", "add:------> insert = "+insert+" insert2 = "+insert2+" insert3 = "+insert3);
        } catch (SQLException e) {//第一版数据库没有weight字段,报错: table USER has no column named weight (code 1)
            e.printStackTrace();
            Log.e("walke: UserDao", "add: ----->exception: ", e);
        }

    }

    public void updateWeightByName(String name, float weight){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("weight",weight);
            int update = db.update(DBHelper.USER_TABLE, values, "name=?", new String[]{name});// 返回更新个数，0表示没有更新[已删除/不存在]
            Log.i("walke: UserDao", "updateWeightByName:------> update = "+update);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: UserDao", "updateWeightByName: ----->exception: ", e);
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
            //参数二为null表示查询对应名字的所有信息，不为空表示只查询对应的字段信息
            Cursor cursor = db.query(DBHelper.USER_TABLE,null, "name=?", new String[]{name}, null, null, null);
            boolean moveToFirst = cursor.moveToFirst();
            if (moveToFirst){

                float firstWeight = cursor.getFloat(cursor.getColumnIndexOrThrow("weight"));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int firstTall = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
                int firstID = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                Log.i("walke: UserDao", "queryByName:------> firstID = "+firstID+" firstName = "+ firstName+" firstTall = "+ firstTall +" firstWeight = "+ firstWeight+"  = ");
                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String n = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    int tall = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
                    float weight = cursor.getFloat(cursor.getColumnIndexOrThrow("weight"));
                    Log.i("walke: UserDao", "queryByName:------> id="+id+" name="+n+" tall="+tall+" wieght="+weight);
                }
            }
            cursor.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("walke: UserDao", "queryByName: ----->exception: ", e);
        }

    }

    public void queryAll(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            Cursor cursor = db.query(DBHelper.USER_TABLE, null, null, null, null, null, null);
            boolean moveToFirst = cursor.moveToFirst();
            if (moveToFirst){
                int firstID = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                float firstWeight = cursor.getFloat(cursor.getColumnIndexOrThrow("weight"));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int firstTall = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
                Log.i("walke: UserDao", "queryAll:------> firstID = "+firstID+" firstName = "+ firstName+" firstTall = "+ firstTall +" firstWeight = "+ firstWeight+"  = ");
                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    int tall = cursor.getInt(cursor.getColumnIndexOrThrow("tall"));
                    float weight = cursor.getFloat(cursor.getColumnIndexOrThrow("weight"));
                    Log.i("walke: UserDao", "queryAll:------> id="+id+" name="+name+" tall="+tall+" wieght="+weight);
                }

            }
            cursor.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("walke: UserDao", "queryAll: ----->exception: ", e);
        }

    }

    public void deleteOneByName(String name){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            int delete = db.delete(DBHelper.USER_TABLE, "name=?", new String[]{name});//返回删除个数，0表示没有删除[已删除/不存在]
            Log.i("walke: UserDao", "deleteOneByName:------> delete= "+delete);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: UserDao", "deleteOneByName: ----->exception: ", e);
        }
    }
    public void deleteOneById(int id){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            int delete = db.delete(DBHelper.USER_TABLE, "id=?", new String[]{id + ""});//返回删除个数，0表示没有删除[已删除/不存在]
            Log.i("walke: UserDao", "deleteOneById:------> delete= "+delete);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: UserDao", "deleteOneById: ----->exception: ", e);
        }
    }

    /**
     * 删除全部，
     */
    public void deleteAll(){
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            // 删除全部，但还保留自增记录 [ 当SQLite数据库中包含自增列时，会自动建立一个名为 sqlite_sequence 的表 ]
            int delete = db.delete(DBHelper.USER_TABLE, null, null);// 返回删除个数，0表示没有[删除/不存在]
            // 清除 自增记录
            db.execSQL("delete from sqlite_sequence where nam='"+DBHelper.USER_TABLE+"'");
            Log.i("walke: UserDao", "deleteAll:------> delete= "+delete);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: UserDao", "deleteAll: ----->exception: ", e);
        }
    }



}

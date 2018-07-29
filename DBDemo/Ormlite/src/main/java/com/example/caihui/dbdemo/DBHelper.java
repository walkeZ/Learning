package com.example.caihui.dbdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by caihui on 2016/10/20.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private final static int DATABASE_VERTION=1;
    private final static String DB_NAME="orm.db";
    private static DBHelper dbHelper;
    private Dao<School,Integer> mSchoolDao;
    private Dao<Student,Integer> mStudentDao;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERTION);

    }
    public static DBHelper getIntance(Context context){
        if (dbHelper==null){
            dbHelper=new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            // 清空表
//            TableUtils.clearTable(connectionSource,Student.class);
            TableUtils.createTable(connectionSource,Student.class);
            TableUtils.createTable(connectionSource,School.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    public Dao<Student,Integer> getStudentDao(){
        if (mStudentDao==null){
            try {
                mStudentDao=getDao(Student.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mStudentDao;
    }

    public Dao<School,Integer> getSchoolDao() throws SQLException {
        if (mSchoolDao==null){
            mSchoolDao=getDao(School.class);
        }
        return mSchoolDao;

    }

}

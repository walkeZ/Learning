package com.walke.dbdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by walke.Z on 2018/5/29.
 * 笔记：day13
 * http://www.jb51.net/article/84713.htm
 * <p>
 * http://www.cnblogs.com/linjiqin/archive/2011/05/26/2059182.html
 * SQLite3支持NULL、INTEGER、REAL（浮点数字）、TEXT(字符串文本)和BLOB(二进制对象)数据类型，虽然它支持的类型只有五种
 * <p>
 * 使用单例模式，懒汉式，
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb.db";
    private static final int CURRENT_VERSION = 1;//4
    public static final String USER_TABLE = "USER";
    public static final String BOOK = "BOOK";

    private static DBHelper instance;

   /* public DBHelper getInstance(Context context) {
        synchronized(DBHelper.this) {
            if (instance == null)
                instance = new DBHelper(context, DB_NAME, null, CURRENT_VERSION);
        }
        return instance;
    }*/

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null)
            instance = new DBHelper(context, DB_NAME, null, CURRENT_VERSION);
        return instance;
    }

    /**
     * @param context 上下文
     * @param name    数据库名称
     * @param factory 游标工厂/构造器 (默认为null)
     * @param version 数据库版本号
     */
    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
        super(context, DB_NAME, null, CURRENT_VERSION);
    }


    /**
     * 数据库创建时调用
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //这条语句会创建一个名为 USER_TABLE 的表(当数据库中不存在创建)，
        //表中有一个列名为_id 并且是主键，该列的值是会自动增长的整数，
        // (例如当你插入一行时SQLite会给这一列自动赋值)，另外还有两列：
        // title(字符)和(浮点数)。注：SQLite会自动为主键创建索引
        db.execSQL("create table if not exits" + USER_TABLE +
                "(_id INTEGER primary key autoincrement," +
                "name TEXT," +
                "tall REAL)");


//        onUpgrade(db, 1, CURRENT_VERSION);
    }

    /**
     * 当数据库版本升级时调用
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("walke--DBHelper", "onUpgrade:------> oldVersion = " + oldVersion + "  newVersion = " + newVersion);
        switch (oldVersion) {
            case 1://版本二执行的更新,old为1，一般新的为2
                db.execSQL("ALTER TABLE " + USER_TABLE + " ADD COLUMN ‘NICKNAME’");//该语句表示往USER_TABLE表中增加一列，注意拼接时的空格
                break;
            case 2:// 版本三执行的更新
                //该语句表示新建一个BOOK表，有id(主键) name price 三列.name、price没声明类型原因：
                //SQLite最大的特点是你可以把各种类型的数据保存到任何字段中，而不用关心字段声明的数据类型是什么,
                db.execSQL("create table if not exits" + BOOK + "(_id integer primary key autoincrement," +
                        "name," +
                        "price)");
                break;
            case 3:// 版本四执行的更新
                // 该语句表示往BOOK表中增加一列author
                db.execSQL("alter table " + BOOK + " add column ‘author’");
                break;

        }
    }

    /**
     * getWritableDatabase()，getReadableDatabase的区别是当数据库写满时，调用前者会报错，调用后者不会，
     * 所以如果不是更新数据库的话，最好调用后者来获得数据库连接。
     *
     * @return
     */
    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    /**
     * 一般使用这个就可以了
     *
     * @return
     */
    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }
}

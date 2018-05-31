package com.walke.greendao;

import android.app.Application;

import com.walke.greendao.bean.DaoMaster;
import com.walke.greendao.bean.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by walke.Z on 2018/5/30.
 */

public class GreenDaoApp extends Application {

    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     * 一个标志，显示您可以轻松地从标准SQLite切换到加密的sql密码。
     */
    public static final boolean ENCRYPTED = true; //encrypted : 把...加密，把...编码。

    private DaoSession mDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDatabase();

    }

    /**
     * 配置数据库
     * https://blog.csdn.net/u013617622/article/details/72784024
     * greenDao小坑一个：
     *  java.lang.NoClassDefFoundError: org.greenrobot.greendao.database.DatabaseOpenHelper$EncryptedHelper

     *  解决方式： 引入加密依赖库 compile ‘net.zetetic:android-database-sqlcipher:3.5.2’
     */
    private void setupDatabase() {
        //创建数据库
        //报错了java.lang.NoClassDefFoundError: org.greenrobot.greendao.database.DatabaseOpenHelper$EncryptedHelper
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "walke-db-encrypted" : "walke-db");
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "walke-db-encrypted.db" : "walke-db.db",null);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();

        //可以的
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
//        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}

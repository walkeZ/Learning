package com.walke.realm;

import android.app.Application;

import com.walke.realm.util.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by walke.Z on 2018/5/23.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //realm数据库初始化
        Realm.init(this);
        //1.使用默认配置
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();

        //2.使用自定义配置
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}

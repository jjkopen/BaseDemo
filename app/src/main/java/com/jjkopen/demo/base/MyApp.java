package com.jjkopen.demo.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.jjkopen.demo.greendao.bean.DaoMaster;
import com.jjkopen.demo.greendao.bean.DaoSession;

public class MyApp extends Application {
    private static MyApp instance;

    private static DaoSession daoSession;

    public static MyApp getInstance() {
        return instance;
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupDatabase("database.db");
    }

    /**
     * 配置数据库
     */
    private void setupDatabase(String name) {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, name, null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }
}

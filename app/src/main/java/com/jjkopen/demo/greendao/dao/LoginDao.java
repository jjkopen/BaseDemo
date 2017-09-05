package com.jjkopen.demo.greendao.dao;

import com.jjkopen.demo.base.MyApp;
import com.jjkopen.demo.greendao.bean.UserName;

import java.util.List;

/**
 * Created on 2017/8/31.
 */

public class LoginDao {
    /**
     * 添加数据
     *
     * @param name
     */
    public static void insertHistory(UserName name) {
        MyApp.getDaoInstant().getUserNameDao().insertOrReplace(name);
    }

    /**
     * 删除数据
     *
     * @param name
     */
    public static void deleteHistory(UserName name) {
        MyApp.getDaoInstant().getUserNameDao().delete(name);
    }

    /**
     * 查询全部数据
     *
     * @return List<name>
     */
    public static List<UserName> queryAll() {
        return MyApp.getDaoInstant().getUserNameDao().loadAll();
    }

    /**
     * 清空数据
     */
    public static void clearAll() {
        MyApp.getDaoInstant().getUserNameDao().deleteAll();
    }
}

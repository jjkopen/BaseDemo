package com.jjkopen.demo.greendao.dao;

import com.jjkopen.demo.base.MyApp;
import com.jjkopen.demo.greendao.bean.SearchHistory;
import com.jjkopen.demo.greendao.bean.Shop;
import com.jjkopen.demo.greendao.bean.ShopDao;

import java.util.List;

/**
 * Created on 2017/8/28.
 */

public class SearchHistoryDao {

    /**
     * 添加数据
     *
     * @param searchHistory
     */
    public static void insertHistory(SearchHistory searchHistory) {
        MyApp.getDaoInstant().getSearchHistoryDao().insertOrReplace(searchHistory);
    }

    /**
     * 删除数据
     *
     * @param searchHistory
     */
    public static void deleteHistory(SearchHistory searchHistory) {
        MyApp.getDaoInstant().getSearchHistoryDao().delete(searchHistory);
    }

    /**
     * 查询全部数据
     *
     * @return List<SearchHistory>
     */
    public static List<SearchHistory> queryAll() {
        return MyApp.getDaoInstant().getSearchHistoryDao().loadAll();
    }

    /**
     * 清空数据
     */
    public static void clearAll() {
        MyApp.getDaoInstant().getSearchHistoryDao().deleteAll();
    }
}

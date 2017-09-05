package com.jjkopen.demo.greendao.dao;

import com.jjkopen.demo.base.MyApp;
import com.jjkopen.demo.greendao.bean.Shop;
import com.jjkopen.demo.greendao.bean.ShopDao;

import java.util.List;

/**
 * Created on 2017/8/28.
 */

public class CartDao {

    /**
     * 添加数据
     *
     * @param shop
     */
    public static void insertCart(Shop shop) {
        MyApp.getDaoInstant().getShopDao().insertOrReplace(shop);
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public static void insertCart(List<Shop> list) {
        MyApp.getDaoInstant().getShopDao().insertOrReplaceInTx(list);
    }

    /**
     * 插入数据
     *
     * @param shop
     */
    public static void addCart(Shop shop) {
        MyApp.getDaoInstant().getShopDao().insert(shop);
    }

    /**
     * 插入数据
     *
     * @param list
     */
    public static void addCart(List<Shop> list) {
        MyApp.getDaoInstant().getShopDao().insertInTx(list);
    }

    /**
     * 删除数据
     *
     * @param shop
     */
    public static void deleteCart(Shop shop) {
        MyApp.getDaoInstant().getShopDao().delete(shop);
    }

    /**
     * 删除数据
     *
     * @param list
     */
    public static void deleteCart(List<Shop> list) {
        MyApp.getDaoInstant().getShopDao().deleteInTx(list);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteCartByKey(long id) {
        MyApp.getDaoInstant().getShopDao().deleteByKey(id);
    }

    /**
     * 删除数据
     *
     * @param ids
     */
    public static void deleteCartByKey(Long... ids) {
        MyApp.getDaoInstant().getShopDao().deleteByKeyInTx(ids);
    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateCart(Shop shop) {
        MyApp.getDaoInstant().getShopDao().update(shop);
    }

    /**
     * 更新数据
     *
     * @param list
     */
    public static void updateCart(List<Shop> list) {
        MyApp.getDaoInstant().getShopDao().updateInTx(list);
    }

    /**
     * 查询条件为Type=TYPE_CART的数据
     *
     * @return List<Shop>
     */
    public static List<Shop> queryCart() {
        return MyApp.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_CART)).list();
    }

    /**
     * 查询全部数据
     *
     * @return List<Shop>
     */
    public static List<Shop> queryAll() {
        return MyApp.getDaoInstant().getShopDao().loadAll();
    }

    /**
     * 清空数据
     */
    public static void clearAll() {
        MyApp.getDaoInstant().getShopDao().deleteAll();
    }
}

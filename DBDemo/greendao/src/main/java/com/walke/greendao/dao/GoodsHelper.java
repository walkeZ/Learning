package com.walke.greendao.dao;

import android.util.Log;

import com.walke.greendao.base.BaseActivity;
import com.walke.greendao.bean.Goods;
import com.walke.greendao.bean.GoodsDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by walke.Z on 2018/5/31.
 *  Android数据存储之GreenDao 3.0 详解
 *  https://www.cnblogs.com/whoislcj/p/5651396.html
 * <p>
 * <p>
 * <p>
 * 封装想法：
 * 原本打算将getDao方法弄到BaseActivity这样能较快编码(还是传入Activity，可以activity.调用)
 * 后来感觉职责隔离不好，以后又新增其他的Dao岂不是又要改BaseActivity？让BaseActivity负责公共模块
 * (getApp,沉浸式状态栏等)即可，DaoHelper负责dao的操作，新增到就对应新增DaoHelper符合单一职责原则
 */

public class GoodsHelper {

    private static GoodsDao getDao(BaseActivity activity) {
        return activity.getApp().getDaoSession().getGoodsDao();
    }
    //------------------------------------查询-----------------------------------------------
    public static List<Goods> queryAll(BaseActivity activity) {
        GoodsDao goodsDao = activity.getApp().getDaoSession().getGoodsDao();
        List<Goods> list = goodsDao.queryBuilder().list();
        //查询所有，并按姓名排序a-z
//        List<Goods> list = goodsDao.queryBuilder().orderAsc(GoodsDao.Properties.Name).list();
        return list;
    }

    /**
     * @param activity
     * @param price
     * @return
     */
    public static List<Goods> queryByPrice(BaseActivity activity, String price) {
        GoodsDao goodsDao = getDao(activity);
        List<Goods> list = goodsDao.queryBuilder().where(GoodsDao.Properties.Price.eq(price)).list();
        return list;
    }

    /**
     * @param activity
     * @param id
     * @return
     */
    public static List<Goods> queryById(BaseActivity activity, Long id) {
        GoodsDao goodsDao = getDao(activity);
        QueryBuilder<Goods> where = goodsDao.queryBuilder().where(GoodsDao.Properties.Id.eq(id));
        List<Goods> list = where.list();
        return list;
    }

    /**
     * @param activity
     * @param id
     * @return
     */
    public static Goods queryById2(BaseActivity activity, Long id) {
        GoodsDao goodsDao = getDao(activity);
        Goods goods = goodsDao.loadByRowId(id);
        return goods;
    }

    //------------------------------------添加-----------------------------------------------

    public static void add(BaseActivity activity, Goods goods) {
        GoodsDao goodsDao = getDao(activity);
        long l = goodsDao.insert(goods);
//        long l = goodsDao.insertOrReplace(goods);
        Log.i("walke: GoodsHelper", "add:------> l = " + l);
    }

    /**
     * @param activity
     * @param gs
     */
    public static void addList(BaseActivity activity, List<Goods> gs) {
        try {
            GoodsDao goodsDao = getDao(activity);
//            goodsDao.insertOrReplaceInTx(gs);
            goodsDao.insertInTx(gs);
            Log.i("walke: GoodsHelper", "addList:------> size = " + gs.size());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: GoodsHelper", "addList: ----->exception: ", e);
        }
    }

    //------------------------------------更新-----------------------------------------------
    public static boolean updateOne(BaseActivity activity, Goods goods) {
        try {
            Log.i("walke: GoodsHelper", "updateOne:------> success");
            getDao(activity).update(goods);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("walke: GoodsHelper", "updateOne:------> e");
            return false;
        }
        return true;
    }

    public static boolean updateList(BaseActivity activity, List<Goods> gs) {
        try {
            getDao(activity).updateInTx(gs);
            Log.i("walke: GoodsHelper", "updateList:------> success");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: GoodsHelper", "updateList: ----->exception: ", e);
            return false;
        }
        return true;
    }

    //------------------------------------删除-----------------------------------------------

    /**
     * @param activity
     * @param goods
     * @return
     */
    public static boolean deleteOne(BaseActivity activity, Goods goods) {
        try {
            GoodsDao dao = getDao(activity);
            dao.delete(goods);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** 主键自增索引表还在，有记录
     * @param activity
     * @return
     */
    public static boolean deleteAll(BaseActivity activity) {
        try {
            getDao(activity).deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param activity
     * @return
     */
    public static boolean deleteById(BaseActivity activity, Long id) {
        try {
            getDao(activity).deleteByKey(id);
            Log.i("walke: GoodsHelper", "deleteById:------> success");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: GoodsHelper", "deleteById: ----->exception: ", e);
            return false;
        }
        return true;
    }

    /**
     * @param activity
     * @return
     */
    public static boolean deleteByPrice(BaseActivity activity, String name) {
        try {
            List<Goods> goodses = queryByPrice(activity, name);
            getDao(activity).deleteInTx(goodses);
            Log.i("walke: GoodsHelper", "deleteByPrice:------> success");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("walke: GoodsHelper", "deleteByPrice: ----->exception: ", e);
            return false;
        }
        return true;
    }



}

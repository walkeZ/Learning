package com.example.caihui.dbdemo.demo2.test;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.example.caihui.dbdemo.demo2.bean.User;
import com.example.caihui.dbdemo.demo2.db.DatabaseHelper;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

/**
 * Created by caihui on 2016/10/23.
 */
public class TestUtils {

    private static final String TAG = "TestUtils";
    private Context mContext;

    public TestUtils(Context context) {
        mContext = context;
    }
    /**
     * 增
     */
    public void addUserTest()
    {
        DatabaseHelper helper = DatabaseHelper.getHelper(mContext);

        try {

            User u1=new User("stb", 22, "男");
            helper.getUserDao().create(u1);

            User u2=new User("stb2", 23, "男");
            helper.getUserDao().create(u2);
            User u3=new User("stb3", 24, "男");
            helper.getUserDao().create(u3);
            Log.d(TAG, "addUserTest: --->");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删
     */
    public void delUserTest()
    {
        DatabaseHelper helper=DatabaseHelper.getHelper(mContext);

        try {
            helper.getUserDao().deleteById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     */
    public void deleteAll(){
        DatabaseHelper helper = DatabaseHelper.getHelper(mContext);

        try {
            TableUtils.clearTable(helper.getConnectionSource(),User.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 改
     */
    public void updateUserTest()
    {
        DatabaseHelper helper=DatabaseHelper.getHelper(mContext);
        User u=new User("我爱你", 25, "女");
        u.setId(5);
        try {
            helper.getUserDao().update(u);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查
     */
    public void queryUserTest()
    {
        DatabaseHelper helper=DatabaseHelper.getHelper(mContext);
        List<User> list=null;
        try {
            list = helper.getUserDao().queryForAll();
            User user = helper.getUserDao().queryForId(5);
            System.out.println("------------------------------------------------------------"+user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        if(list!=null)
        {
            for(int i=0;i<list.size();i++)
            {
                System.out.println(list.get(i).toString());
            }
        }
    }

}

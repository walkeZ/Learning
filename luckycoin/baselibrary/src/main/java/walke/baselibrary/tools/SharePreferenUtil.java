package walke.baselibrary.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Walke.Z on 2017/4/27.
 * 一个新发现：SharedPreferences中记录的是k-v对，
 * 当相同的key，去保存不同类型的values会发生数据获取混乱导致类型转化异常
 */
public class SharePreferenUtil {
    public static SharedPreferences getSharedPreferences(Context context) {
        //getSharedPreferences("mySp", Context.MODE_PRIVATE);
        //该方法可以在/data/data/<package>/shared_pref/目录下创建一个以name命名的xml文件，mode文件为模式
        return context.getSharedPreferences("huiGatherSP", Context.MODE_PRIVATE);
    }
    public static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).commit();
    }
    public static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, null);
    }

    public static void putInt(Context context, String key, int i) {
        getSharedPreferences(context).edit().putInt(key,i).commit();
    }
    public static int getInt(Context context, String key, int i) {
        return getSharedPreferences(context).getInt(key,i);
    }

    public static void putIntTimes(Context context, String key, int times) {
        getSharedPreferences(context).edit().putInt(key,times).commit();
    }
    public static int getIntTimes(Context context, String key, int defaultTimes) {
        return getSharedPreferences(context).getInt(key,defaultTimes);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).commit();
    }
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getSharedPreferences(context).getBoolean(key, defValue);
    }

    public static void putIsFinished(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key+"isFinish", value).commit();
    }
    public static boolean getIsFinished(Context context, String key, boolean defValue) {
        return getSharedPreferences(context).getBoolean(key+"isFinish", defValue);
    }
}

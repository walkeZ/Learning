package walke.baselibrary.tools;

/**
 * Created by Walke.Z
 * on 2017/8/24. 39.
 * email：1126648815@qq.com
 */
public class LongUtil {
    /**
     * integer转换
     *
     * @param value

     * @return
     */
    public static long toInt(Long value) {
        if (value!=null)
            return value;
        else
            return 0;
    }
    /**
     * integer转换
     *
     * @param value

     * @return
     */
    public static String toString(Long value) {
        if (value!=null)
            return value+"";
        else
            return 0+"";
    }
}

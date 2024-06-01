package com.walker.mvvmlearn.manager;

import com.walker.mvvmlearn.utils.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaManger {
    private static class Instance {
        private static final JavaManger INSTANCE = new JavaManger();
    }

    private JavaManger() {
    }

    public static JavaManger instance() {
        return JavaManger.Instance.INSTANCE;
    }

    private List<Integer> mListTotalHr = new ArrayList<>();
    // 阶段的心率数据
    private List<Integer> mListJieDuanHr = new ArrayList<>();
    // 当前分钟的心率
    private List<Integer> mListCurrentMinHr = new ArrayList<>();
    private List<Integer> mListLastMinHr = new ArrayList<>();

    public void testListAddAll() {
        for (int i = 0; i < 10; i++) {
            mListTotalHr.add(i);
            if (i < 3) {
                mListCurrentMinHr.add(i);
            }
            if (i > 6) {
                mListJieDuanHr.add(i);
            }
        }
//        LogUtil.i("mListTotalHr " + listToString(mListTotalHr));
//        LogUtil.i("mListJieDuanHr " + listToString(mListJieDuanHr));
        LogUtil.i("mListCurrentMinHr " + listToString(mListCurrentMinHr));
        LogUtil.i("mListLastMinHr " + listToString(mListLastMinHr));

        mListLastMinHr.clear();
        mListLastMinHr.addAll(mListCurrentMinHr);
        LogUtil.e(" addAll ---> mListCurrentMinHr " + listToString(mListCurrentMinHr));
        LogUtil.i("mListLastMinHr " + listToString(mListLastMinHr));
        mListCurrentMinHr.set(1, 20);
        LogUtil.i("change 1 origin, mListCurrentMinHr " + listToString(mListCurrentMinHr));
        LogUtil.i("mListLastMinHr " + listToString(mListLastMinHr));
        mListCurrentMinHr.clear();
        LogUtil.i("clear origin, mListCurrentMinHr " + listToString(mListCurrentMinHr));
        LogUtil.i("mListLastMinHr " + listToString(mListLastMinHr));
        //2024-06-01 19:13:15.828 16137-16137 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:40) mListCurrentMinHr [0, 1, 2]
        //2024-06-01 19:13:15.828 16137-16137 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:41) mListLastMinHr []
        //2024-06-01 19:13:15.829 16137-16137 hui_1                   com.walker.mvvmlearn                 E  (JavaManger.java:45)  addAll ---> mListCurrentMinHr [0, 1, 2]
        //2024-06-01 19:13:15.829 16137-16137 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:46) mListLastMinHr [0, 1, 2]
        //2024-06-01 19:13:15.829 16137-16137 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:48) change 1 origin, mListCurrentMinHr [0, 20, 2]
        //2024-06-01 19:13:15.830 16137-16137 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:49) mListLastMinHr [0, 1, 2]
        //2024-06-01 19:13:15.830 16137-16137 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:51) clear origin, mListCurrentMinHr []
        //2024-06-01 19:13:15.830 16137-16137 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:52) mListLastMinHr [0, 1, 2]1 19:09:14.582 15487-15487 hui_1                   com.walker.mvvmlearn                 I  (JavaManger.java:49) mListLastMinHr [0, 1, 2]

        // 从日志可以看出addAll 是将传入的数组copy一份到目标数组，并非单纯的引用，addALl之后2个数字相互独立
    }

    /**
     * https://blog.csdn.net/piaoranyuji/article/details/125385703
     */
    public String listToString(List<Integer> list) {
        int[] res = list.stream().mapToInt(Integer::intValue).toArray();
        return Arrays.toString(res);
    }

}

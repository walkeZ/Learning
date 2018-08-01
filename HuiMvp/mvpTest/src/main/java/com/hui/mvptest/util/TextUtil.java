package com.hui.mvptest.util;

import android.text.TextUtils;

/**
 * Created by walke.Z on 2017/8/4.
 */

public class TextUtil {

    public static String replaceEmpty(String emptyPossible,String tagText){
        if (TextUtils.isEmpty(emptyPossible))
            return tagText;
        else
            return emptyPossible;
    }

}

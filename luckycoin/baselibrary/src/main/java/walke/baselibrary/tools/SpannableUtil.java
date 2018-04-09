package walke.baselibrary.tools;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by Walke.Z
 * on 2017/11/16. 18.
 * email：1126648815@qq.com
 */
public class SpannableUtil {
    /** SpannableStringBuilder的使用,TextView部分字体变颜色
     * @param tv
     * @param front
     * @param tag
     * @param after
     * @param color
     */
    public static void tvSpannable(TextView tv, String front, String tag, String after, int color) {
        String str = front + tag + after;
        int winStart = str.indexOf(tag);
        int winEnd = winStart + tag.length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(color), winStart, winEnd, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(style);
    }

}

package com.walke.demo.myclick;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.walke.demo.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by walke on 2018/9/26.
 * email:1032458982@qq.com
 * 参考： https://blog.csdn.net/mp624183768/article/details/53785142
 */

public class SpannableStringClickActivity extends AppCompatActivity {

    private TextView tvText1, tvText2, tvText3, tvText4,tvText5;

    //存放的是表情的标识和映射关系
    private static HashMap<String, Integer> emojiMap = new HashMap<>();

    static {
        emojiMap.put("[:bigBIng]", R.drawable.icon_smiley);
        emojiMap.put("[:jiong]", R.mipmap.ic_launcher);
        emojiMap.put("[:what]", R.mipmap.ic_launcher_round);
//        emojiMap.put("[:smile]", R.drawable.smiley_13);
//        emojiMap.put("[:cry]", R.drawable.smiley_5);
        Set<Map.Entry<String, Integer>> entries = emojiMap.entrySet();
    }


    /**
     * 简易findViewById
     */
    public <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_spannable_string);
        tvText1 = findView(R.id.acss_tvText1);
        tvText2 = findView(R.id.acss_tvText2);
        tvText3 = findView(R.id.acss_tvText3);
        tvText4 = findView(R.id.acss_tvText4);
        tvText5 = findView(R.id.acss_tvText5);

        //表情的解析
        // 1 让文本和图片一起显示
        tvText1.setText(showTextWithImage("我是文本[:bigBIng]", R.drawable.icon_smiley));
        //让某段文字变色
        tvText2.setText(showTextWithColor("王二,大明,大兵等3人觉得很赞", Color.BLUE));
        //让某段文字变色
        String text = "详情请点击<a href='http://www.baidu.com'>百度</a>";
        //使用Html类识别带有html标签的字符串
        Spanned spanned = Html.fromHtml(text);
        tvText3.setText(spanned);
        //设置可以点击超连接
        tvText3.setMovementMethod(LinkMovementMethod.getInstance());

        // 4 让某段文字可以被点击并自定义点击的逻辑操作
        String string = "王二,大明,大兵等3人觉得很赞";
        SpannableString ss = new SpannableString(string);
        MyUrlSpan myUrlSpan = new MyUrlSpan(string.substring(0, string.indexOf(",")));
        ss.setSpan(myUrlSpan, 0, 2, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        tvText4.setText(ss);
        tvText4.setMovementMethod(LinkMovementMethod.getInstance());

        // 4 让某段文字可以被点击并自定义点击的逻辑操作

        String title = "挑战卡-自我修炼";
        String str5= title + "点击颜色不同\n我是文本加图片\n我是文本加图片\n我是文本加图片";
//        MyForegroundColorSpanImpl colorSpan=new MyForegroundColorSpanImpl(Color.YELLOW);
        ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.parseColor("#ff7777"));
        SpannableString ss5 = new SpannableString(str5);
        ss5.setSpan(colorSpan, 0, title.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        tvText5.setText(ss5);
    }

    private CharSequence showTextWithColor(String text, int color) {
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.BLACK);
        ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.RED);
        int end = text.indexOf("等");
        ss.setSpan(colorSpan, 0, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorSpan2, end + 1, end + 3, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorSpan3, 0, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss;
    }

    private SpannableString showTextWithImage(String text, int imageRes) {
        SpannableString ss = new SpannableString(text);
        Drawable drawable = getResources().getDrawable(imageRes);
        //必须设置drawable的边界，就是设置drawable的宽高//        一般不用原始宽高//
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, 40, 40);
        //能够放在字符串中的图片
        ImageSpan span = new ImageSpan(drawable);
        ImageSpan span2 = new ImageSpan(drawable);
        //可以设置多个span对象
        ss.setSpan(span, text.indexOf("["), text.indexOf("]") + 1, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(span2, text.length() - 1, text.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        tvText4.setText(ss);
        tvText4.setMovementMethod(LinkMovementMethod.getInstance());
        return ss;
    }

    @SuppressLint("ParcelCreator")
    private class MyUrlSpan extends URLSpan {
        public MyUrlSpan(String url) {
            super(url);
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(SpannableStringClickActivity.this, getURL(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            //设置文字的颜色和是否显示下划线
            ds.setColor(Color.GREEN);
            ds.setUnderlineText(false);
        }
    }

//    @SuppressLint("ParcelCreator")
//    private class MyForegroundColorSpan extends ForegroundColorSpan implements ClickableSpan{
//
//        public MyForegroundColorSpan(@ColorInt int color) {
//            super(color);
//        }
//
//        public MyForegroundColorSpan(Parcel src) {
//            super(src);
//        }
//
//        @Override
//        public void onClick(View view) {
//            Log.i("walke: ", " MyForegroundColorSpan:  onClick:-------> ");
//        }
//    }
//
//    public class MyForegroundColorSpanImpl extends MyForegroundColorSpan {
//        public MyForegroundColorSpanImpl(@ColorInt int color) {
//            super(color);
//        }
//    }


}

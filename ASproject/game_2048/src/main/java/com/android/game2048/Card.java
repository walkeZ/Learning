package com.android.game2048;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 每张独立卡片，一共16张
 */
public class Card extends FrameLayout {
    /**
     * 每张卡片的大小
     */
    public static int width;

    /**
     * 每张独立卡片，显示数字的控件
     */
    private TextView label; // label :标签

    public Card(Context context) {
        super(context);
        LayoutParams lp = null;
        View background = new View(getContext());
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(10, 10, 0, 0);
        background.setBackgroundColor(Color.BLUE);
        addView(background, lp);

        label = new TextView(getContext());
        label.setTextSize(28);
        label.setTextColor(Color.RED);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(Color.GREEN);

        LayoutParams lp2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp2.setMargins(10, 10, 0, 0);
        addView(label, lp2);
        setNum(0);
    }


    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(String.valueOf(num));

        }
        //数字的级别[2的次方数]不同显示不同背景色
//        switch (num) {
//            case 0:
//                label.setBackgroundColor(0x00000000);
//                break;
//            case 2:
//                label.setBackgroundColor(0xffeee4da);
//                break;
//            case 4:
//                label.setBackgroundColor(0xffede0c8);
//                break;
//            case 8:
//                label.setBackgroundColor(0xfff2b179);
//                break;
//            case 16:
//                label.setBackgroundColor(0xfff59563);
//                break;
//            case 32:
//                label.setBackgroundColor(0xfff67c5f);
//                break;
//            case 64:
//                label.setBackgroundColor(0xfff65e3b);
//                break;
//            case 128:
//                label.setBackgroundColor(0xffedcf72);
//                break;
//            case 256:
//                label.setBackgroundColor(0xffedcc61);
//                break;
//            case 512:
//                label.setBackgroundColor(0xffedc850);
//                break;
//            case 1024:
//                label.setBackgroundColor(0xffedc53f);
//                break;
//            case 2048:
//                label.setBackgroundColor(0xffedc22e);
//                break;
//            default:
//                label.setBackgroundColor(0xff3c3a32);
//                break;
//        }
    }

    /** 判断卡片里面的数字是否相等，用相等时相加
     * @param another
     * @return
     */
    public boolean equals(Card another) {
        return getNum() == another.getNum();
    }

    public TextView getLabel() {
        return label;
    }

    /**
     * 空卡片内容添加数字时执行动画
     */
    public void addScaleAnimation() {
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(1000);
        setAnimation(null);
        getLabel().startAnimation(sa);
    }

}

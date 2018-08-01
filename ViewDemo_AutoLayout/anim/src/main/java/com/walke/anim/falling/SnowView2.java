package com.walke.anim.falling;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by walke.Z on 2018/4/20.
 * 雪花飘落效果：
 * 1，单个飘落效果
 *  a.onDraw 使用 canvas.drawBitmap()方法绘制雪花
 *  b.改变绘制位置实现动画效果
 *
 */

public class SnowView2 extends RelativeLayout {

    private float[] mAlphas = new float[]{0.35f,0.55f,0.6f,0.6f,0.8f,1f};//透明度数组

    private float[] mSpeedFactors = new float[]{0.5f,0.8f,1f,1.2f,1.5f,2.2f};//飘落速度数组

    private float[] mScaleFactors = new float[]{0.3f,0.4f,0.6f,0.65f,0.78f,0.9f};//缩放大小数组

    private int snowNum=12;

    private Random mRandom = new Random();
    private LayoutParams mLayoutParams;
    private Context mContext;

    public SnowView2(Context context) {
        this(context,null);
    }

    public SnowView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SnowView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }

    private void init(Context context) {
        mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getChildCount()>0){
//            Log.i("walke", "SnowView2 onSizeChanged: -----> getChildCount()>0 --> removeAllViews()");
            removeAllViews();
        }
        for (int i = 0; i < snowNum; i++) {
            float scale=mScaleFactors[mRandom.nextInt(mScaleFactors.length-1)];
            int alpha= (int) (mAlphas[mRandom.nextInt(mAlphas.length-1)]*255);
            float speed= (mSpeedFactors[mRandom.nextInt(mSpeedFactors.length-1)]);
//            mLayoutParams.leftMargin=mRandom.nextInt(w-20)-20;
//            mLayoutParams.topMargin=mRandom.nextInt(40)+10;
            int left = mRandom.nextInt(w - 20) - 20;
            int top = mRandom.nextInt(40) + 10;
            mLayoutParams.setMargins(left, top,0,0);
//            Log.i("walke", "SnowView2 onSizeChanged: ---> left = "+ left+" -- > top = " +top+" -- > w = " +w+" -- > h = " +h);
            FallView1 child = new FallView1(mContext,scale, alpha, speed);
//            child.setBackgroundColor(Color.parseColor("#20ffff00"));
//            addView(child, mLayoutParams);
            child.setLayoutParams(mLayoutParams);
            addView(child);
        }
    }

    private float degrees=0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

package com.walke.anim.falling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.walke.anim.R;

import java.util.Random;

/**
 * Created by walke.Z on 2018/4/20.
 * 雪花飘落效果：
 * 1，单个飘落效果
 *  a.onDraw 使用 canvas.drawBitmap()方法绘制雪花
 *  b.改变绘制位置实现动画效果
 *
 */

public class SnowView3 extends RelativeLayout {

    private float[] mAlphas = new float[]{0.45f,0.55f,0.6f,0.6f,0.8f,1f};//透明度数组，画笔的透明图，不透明=255

    private float[] mSpeedFactors = new float[]{1.5f,1.8f,2f,2.2f,2.5f,2.5f};//飘落速度数组，飘落(重绘)变化的像素点

    private float[] mScaleFactors = new float[]{0.3f,0.4f,0.6f,0.65f,0.78f,0.9f};//缩放大小数组,通过resizeBitmap(scale)方法实现每个雪花大小数组里随机

    private int snowNum=13;

    private Random mRandom = new Random();
//    private RelativeLayout.LayoutParams mLayoutParams;//用这个会导致都在一个垂直线上
    private Context mContext;
    private Bitmap mSnow;

    public SnowView3(Context context) {
        this(context,null);
    }

    public SnowView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SnowView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }

    private void init(Context context) {
//        mLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSnow = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.image_snow);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getChildCount()>0){
            Log.i("walke", "SnowView3 onSizeChanged: -----> getChildCount()>0 --> removeAllViews()");
            removeAllViews();
        }
        for (int i = 0; i < snowNum; i++) {
            float scale=mScaleFactors[mRandom.nextInt(mScaleFactors.length-1)];
            int alpha= (int) (mAlphas[mRandom.nextInt(mAlphas.length-1)]*255);
            float speed= (mSpeedFactors[mRandom.nextInt(mSpeedFactors.length-1)]);
            int left = mRandom.nextInt(w - 20);
            int top = mRandom.nextInt(50);
//            mLayoutParams.setMargins(left, top,0,0);
//            mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            Log.i("walke", "SnowView3 onSizeChanged: ---> left = "+ left+" -- > top = " +top+" -- -------> w = " +w+" -- > h = " +h);
            Bitmap bm = resizeBitmap(scale);
            FallView2 child = new FallView2(mContext,bm,  alpha, speed);
//            child.setBackgroundColor(Color.parseColor("#20ffff00"));
//            addView(child, mLayoutParams);
//            if (i==0){
//                child.setBackgroundColor(Color.parseColor("#AAFF2A2A"));//红
//            }else if (i==1){
//                child.setBackgroundColor(Color.parseColor("#55FF2AFF"));
//            }else if (i==2){
//                child.setBackgroundColor(Color.parseColor("#552A2AFF"));
//            }

//            mLayoutParams.width=bm.getWidth();
//            child.setLayoutParams(mLayoutParams);
//            addView(child,mLayoutParams);

            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(left, top,0,0);
            lp.width=bm.getWidth();
            addView(child,lp);

        }
    }

    private Bitmap resizeBitmap(float scale){
        Matrix m = new Matrix();
        m.setScale(scale, scale);
        Bitmap bm= Bitmap.createBitmap(mSnow,0,0,mSnow.getWidth(),mSnow.getHeight(),m,true);
        return bm;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

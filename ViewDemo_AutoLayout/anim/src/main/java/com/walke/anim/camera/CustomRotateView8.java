package com.walke.anim.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.walke.anim.R;

/**
 * Created by walke.Z on 2018/4/20.
 * 雪花飘落效果：
 * 1，单个飘落效果
 *  a.onDraw 使用 canvas.drawBitmap()方法绘制雪花
 *  b.改变绘制位置实现动画效果
 *
 */

public class CustomRotateView8 extends View {

    private Bitmap mSnow;

    /**
     * 控件宽度
     */
    private int mWidth;
    /**
     * 控件高度
     */
    private int mHeight;

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     *
     */
    private float mX1;
    /**
     *
     */
    private float mY1=0;

    /**
     * graphics 包中的类，用于实现旋转
     */
    Camera mCamera;
    /**
     * 是否在下落
     */
    private boolean isDowning;

    public CustomRotateView8(Context context) {
        this(context,null);
    }

    public CustomRotateView8(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRotateView8(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mSnow = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.image_snow);
        mSnow = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.image_snow_96);
        mPaint = new Paint();
        mCamera = new Camera();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mX1= (mWidth-mSnow.getWidth())/2;
    }

    private float degrees=0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mY1+=2;

        //新增-------
        Matrix mMatrix=new Matrix();
        mCamera.save(); // 记录一下初始状态。save()和restore()可以将图像过渡得柔和一些。
        if (degrees<360){
            degrees+=3;
        }else {
            degrees=0;
        }
        mCamera.rotateY(degrees);
        // translate
        if (mY1==0)
            isDowning=true;

        if (isDowning) {
            if (mY1 > -(mHeight - mSnow.getHeight())) {
                mY1 -= 0.5;
            } else {
                isDowning = false;
            }
        }else {
            mY1 += 0.5;
        }

       /* if (mY1>-(mHeight-mSnow.getHeight())){
            mY1-=0.5;
        }else {
            mY1+=0.5;
        }*/
        mCamera.translate(mX1, mY1, 0);// mY1: -50
        mCamera.getMatrix(mMatrix);
        int centerX = (mWidth)/2;
        int centerY = mSnow.getHeight() / 2;

//        mMatrix.preTranslate(-centerX, -centerY);
//        mMatrix.preTranslate(-centerX+20, centerY);

//        mMatrix.postTranslate(centerX, centerY);
        mCamera.restore();
        // 设置图像处理的中心点
        mMatrix.preTranslate(0, mSnow.getHeight() >> 1);
        Log.i("walke", "CustomRotateView8 draw: -------> mX1 = "+mX1+" ---> mY1 = "+mY1 );
        Log.i("walke", "CustomRotateView8 draw: -------> centerX = "+centerX+" ---> centerY = "+centerY );
//        RectF src = new RectF(mX1, mY1, mSnow.getWidth(), mSnow.getHeight()+mY1);
//        mY1+=2;
//        RectF dst = new RectF(mX1, mY1, mSnow.getWidth(), mSnow.getHeight()+mY1);
//        mMatrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
//        canvas.setMatrix(mMatrix);
//        canvas.drawBitmap(mSnow,mX1,mY1,mPaint);
        canvas.drawBitmap(mSnow, mMatrix,mPaint);// 画图由左上角开始

        invalidate();//60帧每秒
    }
}

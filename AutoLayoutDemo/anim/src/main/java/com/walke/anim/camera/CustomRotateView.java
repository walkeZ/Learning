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

public class CustomRotateView extends View {

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

    public CustomRotateView(Context context) {
        this(context,null);
    }

    public CustomRotateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSnow = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.image_snow);
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
        mY1+=2;

        //新增-------
//        Matrix matrix = canvas.getMatrix();
//
//        mCamera.save();
//        if (degrees<360){
//            degrees++;
//        }else {
//            degrees=0;
//        }
//        mCamera.rotateY(degrees);
//        mCamera.getMatrix(matrix);
////        int centerX = (int) mX1;
////        int centerY = (int) mY1;
//        int centerX = mSnow.getWidth()/2;
//        int centerY = mSnow.getHeight()/2;
//        // 设置图像处理的中心点
//        matrix.preTranslate(centerX, centerY);
//        mCamera.restore();
//        canvas.setMatrix(matrix);
//        Log.i("walke", "SnowView  onDraw: ----> degrees = "+degrees+"--- centerX = "+centerX+"--- centerY = "+centerY);
//


        //新增-------
        Matrix mMatrix=new Matrix();
        mCamera.save();
        if (degrees<360){
            degrees++;
        }else {
            degrees=0;
        }
        mCamera.rotateY(degrees);
        mCamera.getMatrix(mMatrix);
        int centerX = mSnow.getWidth() / 2;
        int centerY = mSnow.getHeight() / 2;
        mMatrix.preTranslate(-centerX, -centerY);
        mMatrix.postTranslate(centerX, centerY);
        mCamera.restore();
//        canvas.setMatrix(mMatrix);
        Log.i("walke", "SnowUtils3 draw: -------> centerX = "+centerX+" ---> centerY = "+centerY );

        canvas.drawBitmap(mSnow, mMatrix,mPaint);// 画图由左上角开始

//        canvas.drawBitmap(mSnow, mX1, mY1, mPaint);// 画图由左上角开始
//        mY1+=2;

        invalidate();//60帧每秒
    }
}

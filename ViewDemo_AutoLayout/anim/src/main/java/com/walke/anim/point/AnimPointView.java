package com.walke.anim.point;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.walke.anim.R;

/**
 * Created by Troy on 2017/3/20.
 *
 * 通过对对象进行值操作来实现动画效果的功能，这就是ValueAnimator的高级用法
 */

public class AnimPointView extends View {

    public static final float sRADIUS = 20F;
    private Point mCurrentPoint;
    private Paint mPaint;
    private Paint mTextPaint;

    //动画持续时间 默认5S
    private int mAnimDuration;
    private int mDefaultAnimDuration = 5;

    //小球序号
    private String mBallText;
    private String mDefaultBallText = "1";

    //初始颜色
    private String mBallStartColor;
    private String mDefaultBallStartColor = "#0000FF";

    //结束颜色
    private String mBallEndColor;
    private String mDefaultBallEndColor = "#FF0000";

    public AnimPointView(Context context) {
        super(context);
        init();
    }

    public AnimPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Ball);
        mAnimDuration = typedArray.getInt(R.styleable.Ball_anim_duration, mDefaultAnimDuration);
        mBallText = typedArray.getString(R.styleable.Ball_ball_text);
        mBallStartColor = typedArray.getString(R.styleable.Ball_start_color);
        mBallEndColor = typedArray.getString(R.styleable.Ball_end_color);
        if(TextUtils.isEmpty(mBallText)){
            mBallText = mDefaultBallText;
        }
        if(TextUtils.isEmpty(mBallStartColor)){
            mBallStartColor = mDefaultBallStartColor;
        }
        if(TextUtils.isEmpty(mBallEndColor)){
            mBallEndColor = mDefaultBallEndColor;
        }
        //回收typedArray
        typedArray.recycle();
        init();
    }

    public AnimPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        //画圆的画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        //画文字的画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(sRADIUS);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mCurrentPoint == null){
            mCurrentPoint = new Point(((int) sRADIUS), ((int) sRADIUS));
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }

    //绘制圆球
    private void drawCircle(Canvas canvas){
//        float x = mCurrentPoint.getX();
        float x = mCurrentPoint.x;
//        float y = mCurrentPoint.getY();
        float y = mCurrentPoint.y;
        canvas.drawCircle(x, y, sRADIUS, mPaint);
        canvas.drawText(mBallText, x, y + 5, mTextPaint);
    }

    // 调用了invalidate()方法，这样的话 onDraw()方法就会重新调用，并且由于currentPoint 对象的坐标已经改变了，
    // 那么绘制的位置也会改变，于是一个平移的动画效果也就实现了；
    private void startAnimation(){
        //改变小球的位置 ValueAnimator
        Point startPoint = new Point(getWidth() / 2, (int) sRADIUS);
        Point endPoint = new Point(getWidth() / 2, (int) (getHeight() - sRADIUS));
//        Log.i("TEST", "startPoint:" + startPoint.getX()+ "-" + startPoint.getY());
//        Log.i("TEST", "endPoint:" + endPoint.getX() + "-" + endPoint.getY());

        Log.i("TEST", "startPoint:" + startPoint.x + "-" + startPoint.y);
        Log.i("TEST", "endPoint:" + endPoint.x + "-" + endPoint.y);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        //动画监听事件，不断重绘view

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPoint = (Point) animation.getAnimatedValue();
                //invalidate() 与 requestLayout()的区别，这个地方也可以用requestLayout()；
                invalidate();
            }
        });
        //设置动画的弹跳差值器
        anim.setInterpolator(new BounceInterpolator());

        //改变小球的颜色 ObjectAnimator
        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(),
                mBallStartColor, mBallEndColor);

        //组合动画
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim).with(anim2);
        animSet.setDuration(mAnimDuration*1000);
        animSet.start();
    }

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }
}
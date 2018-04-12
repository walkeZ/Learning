package com.gdcaihui.luckycoin.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import walke.baselibrary.tools.ViewUtil;

/**
 * Created by Walke.Z
 * on 2017/5/18. 31.
 * email：1126648815@qq.com
 * 半圆试图
 */
public class SemicircleView extends View {

    private Context mContext;
    public SemicircleView(Context context) {
        this(context,null);
    }

    public SemicircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SemicircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        /***********配置画笔*************/
        Paint paint=new Paint();    //采用默认设置创建一个画笔
        paint.setAntiAlias(true);//使用抗锯齿功能
        paint.setColor(0xFFd6d6d6);    //设置画笔的颜色
        //FILL--实心半圆(全填充)  STROKE--半圆弧


        /***********绘制扇形*************/
        //FILL--实心半圆(全填充)  STROKE--半圆弧
        paint.setStyle(Paint.Style.FILL);//设置画笔类型为STROKE类型（个人感觉是描边的意思）
        paint.setColor(Color.WHITE);
        RectF rectfCenter=new RectF(0, 4, height*2-paddingRight-1, height*2);//确定外切矩形范围
        rectfCenter.offset(paddingLeft-1, paddingTop);//使rectf_head所确定的矩形向右偏移paddingLeft-1像素，向下偏移paddingTop像素
        canvas.drawArc(rectfCenter, -0, -180, false, paint);//绘制圆弧，不含圆心
        /***********绘制圆弧*************/
        paint.setColor(0xFFd6d6d6);    //设置画笔的颜色
        paint.setStyle(Paint.Style.STROKE);//设置画笔类型为STROKE类型（个人感觉是描边的意思）
        int strokeWidth = ViewUtil.dipTopx(mContext, 1);
        paint.setStrokeWidth(strokeWidth);//
        RectF rectf=new RectF(0, 4, height*2-paddingRight-1, height*2);//确定外切矩形范围
        rectf.offset(paddingLeft-1, paddingTop);//使rectf_head所确定的矩形向右偏移paddingLeft-1像素，向下偏移paddingTop像素
        canvas.drawArc(rectf, -0, -180, false, paint);//绘制圆弧，不含圆心


       /* int strokeWidth = LayoutUtil.dipTopx(mContext, 1);
        paint.setStrokeWidth(strokeWidth);//
        RectF rectf=new RectF(0, 0, height*2-paddingRight, height*2+5);//确定外切矩形范围
        rectf.offset(paddingLeft-1, paddingTop);//使rectf_head所确定的矩形向右偏移100像素，向下偏移20像素
        canvas.drawArc(rectf, -0, -180, false, paint);//绘制圆弧，不含圆心

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);//设置画笔类型为STROKE类型（个人感觉是描边的意思）
        RectF rectfCenter=new RectF(0, 0, height*2-paddingRight-strokeWidth, height*2-5);//确定外切矩形范围
        rectfCenter.offset(paddingLeft-1+strokeWidth-1, paddingTop+strokeWidth);//使rectf_head所确定的矩形向右偏移100像素，向下偏移20像素
        canvas.drawArc(rectfCenter, -0, -180, false, paint);//绘制圆弧，不含圆心*/
        //canvas.drawRect(rectfCenter,paint);
    }
}

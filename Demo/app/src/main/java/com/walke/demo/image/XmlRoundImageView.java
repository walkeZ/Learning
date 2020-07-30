package com.walke.demo.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import com.walke.demo.R;


/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2016/12/1.
 * 圆角图片
 */
public class XmlRoundImageView extends AppCompatImageView {

    private Paint paint;
    private int roundWidth = 2;
    private int roundHeight = 2;
    private Paint paint2;

    public XmlRoundImageView(Context context) {
        this(context, null);
    }

    public XmlRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XmlRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XmlRoundImageView);
        //roundWidth = a.getDimensionPixelSize(R.styleable.MyImageView_roundWidth, roundWidth);
        roundWidth=a.getDimensionPixelSize(R.styleable.XmlRoundImageView_roundWidth,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,roundWidth,getResources().getDisplayMetrics()));
        //roundHeight = a.getDimensionPixelSize(R.styleable.MyImageView_roundHeight, roundHeight);
        roundHeight = a.getDimensionPixelSize(R.styleable.XmlRoundImageView_roundHeight,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,roundHeight,getResources().getDisplayMetrics()));
        Log.i("walke: ", " XmlRoundImageView:  XmlRoundImageView:-------> roundWidth = "+roundWidth);
        float density = context.getResources().getDisplayMetrics().density;
//        roundWidth = (int) (roundWidth * density);// 上面获取的方法已经对应密度做处理了
//        roundHeight = (int) (roundHeight * density);
        Log.i("walke: ", " XmlRoundImageView:  XmlRoundImageView:-------> density = "+density);
        Log.i("walke: ", " XmlRoundImageView:  XmlRoundImageView:-------> roundWidth = "+roundWidth);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);

    }


    @Override
    public void draw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        super.draw(canvas2);
        drawLiftUp(canvas2);
        drawRightUp(canvas2);
        drawLiftDown(canvas2);
        drawRightDown(canvas2);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();

    }


    private void drawLiftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, roundHeight);
        path.lineTo(0, 0);
        path.lineTo(roundWidth, 0);
        int right = roundWidth * 2;
        int bottom = roundHeight * 2;
        path.arcTo(new RectF(0, 0, right, bottom), -90, -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLiftDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, getHeight() - roundHeight);
        path.lineTo(0, getHeight());
        path.lineTo(roundWidth, getHeight());
        int top = getHeight() - roundHeight * 2;
        int right = 0 + roundWidth * 2;
        int bottom = getHeight();
        path.arcTo(new RectF(0, top, right, bottom), 90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }


    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth() - roundWidth, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - roundHeight);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, getHeight() - roundHeight * 2, getWidth(), getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth(), roundHeight);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - roundWidth, 0);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, 0, getWidth(), 0 + roundHeight * 2), -90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }


}

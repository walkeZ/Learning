package com.walke.demo.myclick;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by walke on 2018/9/26.
 * email:1032458982@qq.com
 */

@SuppressLint("AppCompatCustomView")
public class SubClickTextView extends TextView {
    public SubClickTextView(Context context) {
        this(context, null);
    }

    public SubClickTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubClickTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private String referTitle = "挑战卡-自我修炼";//-自我修炼

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float left = 0f;
            int lineHeight = getLineHeight();
            String trim = getText().toString().trim();
            if (trim.contains(referTitle)) {
                left = getPaint().measureText(referTitle);
            }
            int totalPaddingLeft = getTotalPaddingLeft();
            float eventX = event.getX();
            boolean clickXLeft = eventX > totalPaddingLeft;
            boolean clickXRight = (eventX-totalPaddingLeft) < left;

            boolean touchableX = clickXLeft && clickXRight;

            float eventY = event.getY();
            boolean touchableY = eventY < lineHeight;
            Log.i("walke: ", " SubClickTextView:  onTouchEvent:-------> " +
                    "lineHeight="+lineHeight+
                    "   left(text)=" +left+
                    "   eventX="+eventX+ // 8.xx
                    "   eventY="+ eventY+ // 9.xx  故这是相对控件的
                    "   totalPaddingLeft= "+totalPaddingLeft+  //
                    "   getY=" + getY()+  //
                    "   getX="+getX()); //

            if (touchableX) {
                Log.i("walke: ", " SubClickTextView:  onTouchEvent:-------> 区域点击了 touchableX ="+touchableX);
            }
            if (touchableY) {
                Log.i("walke: ", " SubClickTextView:  onTouchEvent:-------> 区域点击了 touchableY="+touchableY);
            }
            if (touchableX&&touchableY){
                Log.i("walke: ", " SubClickTextView:  onTouchEvent:-------> 区域点击了 ");
                Toast.makeText(((AppCompatActivity) getContext()),"区域点击了",Toast.LENGTH_LONG).show();
            }

        }
        return super.onTouchEvent(event);
    }
}

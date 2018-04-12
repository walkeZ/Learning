package com.gdcaihui.luckycoin.android.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdcaihui.luckycoin.android.R;

import walke.baselibrary.tools.ViewUtil;


/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2016/11/24.
 * 顶部布局，左右图标，中标题
 */
public class TittleLayout extends RelativeLayout implements View.OnClickListener, View.OnTouchListener {
    private FrameLayout flRight;
    private LinearLayout llParent;
    private View stutasBar;
    private ImageView ivLeft, ivRight;
    private TextView tvCenter,tvArea,tvRight;
    private String titleText;
    private Context mContext;
    private LinearLayout llArea;

    public TittleLayout(Context context) {
        this(context, null);
    }

    public TittleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TittleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.widget_title_layout, this);
        stutasBar = findViewById(R.id.wtl_stutas);
        llParent = ((LinearLayout) findViewById(R.id.wtl_parent));
        ivLeft = ((ImageView) findViewById(R.id.wtl_ivLeft));
        tvCenter = ((TextView) findViewById(R.id.wtl_tvCenter));
        ivRight = ((ImageView) findViewById(R.id.wtl_ivRight));
        llArea = ((LinearLayout) findViewById(R.id.wtl_llLeft));
        tvArea = ((TextView) findViewById(R.id.wtl_tvArea));
        tvRight = ((TextView) findViewById(R.id.wtl_tvRight));
        flRight = ((FrameLayout) findViewById(R.id.wtl_flRight));
        llArea.setOnClickListener(this);
        flRight.setOnClickListener(this);
        adaptiveNdk(context);
    }

    public LinearLayout getLlParent() {
        return llParent;
    }
    public void setThisBackgroundColor(int color){
        llParent.setBackgroundColor(color);
    }

    private void adaptiveNdk(Context context) {
        int statusBarHeight = ViewUtil.getStatusBarHeight(context);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) stutasBar.getLayoutParams();
        params.height=statusBarHeight;
        stutasBar.setLayoutParams(params);
        stutasBar.setBackgroundResource(R.drawable.bg_stutas_bar);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            //LogUtil.i("11111","SDK_INT = "+Build.VERSION.SDK_INT);
            stutasBar.setVisibility(GONE);
        }else {
            //LogUtil.i("1111122222","SDK_INT = "+Build.VERSION.SDK_INT);
            stutasBar.setVisibility(VISIBLE);
        }

    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        tvCenter.setText(titleText);
    }
    public void setTitleTextColor(int titleTextColor) {
        tvCenter.setTextColor(titleTextColor);
    }

    public TextView getTvArea() {
        return tvArea;
    }

    public LinearLayout getLlArea() {
        return llArea;
    }

    public View getStutasBar() {
        return stutasBar;
    }

    public ImageView getIvLeft() {
        return ivLeft;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public TextView getTvCenter() {
        return tvCenter;
    }

    public String getTitleText() {
        return titleText;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wtl_llLeft:
                if (mTittleLayoutClickListener != null)
                    mTittleLayoutClickListener.leftClick();
                break;
            case R.id.wtl_flRight:
                if (mTittleLayoutClickListener != null)
                    mTittleLayoutClickListener.rightClick();
                break;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public void setRightId(int resId) {
        ivRight.setImageResource(resId);
    }


    public interface TittleLayoutClickListener {
        void leftClick();

        void rightClick();
    }

    private TittleLayoutClickListener mTittleLayoutClickListener;

    public void setTittleLayoutClickListener(TittleLayoutClickListener tittleLayoutClickListener) {
        mTittleLayoutClickListener = tittleLayoutClickListener;
    }
}

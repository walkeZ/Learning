package com.hui.mvptest.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

import com.hui.mvptest.R;
import com.hui.mvptest.util.ViewUtil;


/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2016/11/24.
 * 顶部布局，左右图标，中标题
 */
public class ActionBarLayout extends RelativeLayout implements View.OnClickListener, View.OnTouchListener {
    private View stutasBar;
    private TextView tvRight;
    private FrameLayout flRight;
    private ImageView ivLeft, ivRight;
    private TextView tvCenter;
    private String titleText;
    private int ivLeftId, ivRightId, titleTextColor;
    private Context mContext;

    public ImageView getIvRightImageView() {
        return ivRight;
    }

    public ActionBarLayout(Context context) {
        this(context, null);
    }

    public ActionBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.action_bar_lalyout, this);
        stutasBar = findViewById(R.id.abl_stutas);
        ivLeft = ((ImageView) findViewById(R.id.abl_ivLeft));
        tvCenter = ((TextView) findViewById(R.id.abl_tv_center));
        ivRight = ((ImageView) findViewById(R.id.abl_iv_right));
        tvRight = ((TextView) findViewById(R.id.abl_tv_right));
        flRight = ((FrameLayout) findViewById(R.id.abl_fl_right));
        ivLeft.setOnClickListener(this);
        flRight.setOnClickListener(this);
        adaptiveNdk(context);
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

    /**
     * 设置资源Id并将其显示
     *
     * @param ivLeftId
     */
    public void setLeftId(int ivLeftId) {
        this.ivLeftId = ivLeftId;
        ivLeft.setImageResource(ivLeftId);
        ivLeft.setVisibility(VISIBLE);
//        ivLeft.setBackgroundResource(R.drawable.pressed_default_transparent);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param ivRightId
     */
    public void setRightId(int ivRightId) {
        this.ivRightId = ivRightId;
        ivRight.setImageResource(ivRightId);
        ivRight.setVisibility(VISIBLE);
//        flRight.setBackgroundResource(R.drawable.pressed_default_transparent);
    }

    /**
     * 设置右边文字、颜色并将其显示
     *
     * @param textColor
     * @param text
     */
    public void setRightText(int textColor,String text) {
        tvRight.setText(text + "");
        tvRight.setTextColor(textColor);
        tvRight.setVisibility(VISIBLE);
//        flRight.setBackgroundResource(R.drawable.pressed_default_transparent);
    }

    /**
     * 设置右边文字并将其显示
     *
     * @param text
     */
    public void setRightText(String text) {
        tvRight.setText(text + "");
        tvRight.setVisibility(VISIBLE);
//        flRight.setBackgroundResource(R.drawable.pressed_default_transparent);
    }

    /**
     * 设置右边文字并将其显示
     *
     * @param text
     */
    public void setRightText(String text,int iconId) {
        tvRight.setText(text + "");
        tvRight.setVisibility(VISIBLE);
//        flRight.setBackgroundResource(R.drawable.pressed_default_transparent);

        Drawable drawable = getResources().getDrawable(iconId);
        //int height = tvRight.getHeight();//0
        int viewHeight = ViewUtil.getViewHeight(tvRight);
        //int minimumHeight = drawable.getMinimumHeight();
        int minimumWidth = drawable.getMinimumWidth();
        int top = viewHeight - minimumWidth;
        int temp = ViewUtil.dipTopx(mContext, 2);
        int tt = ViewUtil.dipTopx(mContext, 3);
        drawable.setBounds(0, tt, minimumWidth-tt, minimumWidth); //设置边界
        tvRight.setCompoundDrawables(null, null, drawable, null);//画在右边

    }

    /**
     * 设置资源Id并将其显示
     *
     * @param padding
     */
    public void setIvLeftPadding(int padding) {
        ivLeft.setPadding(padding, padding, padding, padding);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param padding
     */
    public void setIvRightPadding(int padding) {
        ivRight.setPadding(padding, padding, padding, padding);
    }

    public void setTitleTextColor(int titleTextColor) {
        tvCenter.setTextColor(titleTextColor);
        this.titleTextColor = titleTextColor;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tl_ivLeft:
                if (mTitleLayoutClickListener != null)
                    mTitleLayoutClickListener.leftClick();
                break;

            case R.id.tl_fl_right:
                if (mTitleLayoutClickListener != null)
                    mTitleLayoutClickListener.rightClick();
                break;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public interface TitleLayoutClickListener {
        void leftClick();

        void rightClick();
    }

    private TitleLayoutClickListener mTitleLayoutClickListener;

    public void setTitleLayoutClickListener(TitleLayoutClickListener titleLayoutClickListener) {
        mTitleLayoutClickListener = titleLayoutClickListener;
    }
}

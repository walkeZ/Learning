package com.walke.xietiaozhebuju;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Walke.Z on 2017/4/27.
 * 标题布局，左-返回图标，中-标题文本，右-文本或标题
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener {
    private TextView tvRight;
    private FrameLayout flRight;
    private ImageView ivBack, ivRight;
    private TextView tvTitle;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.title_lalyout, this);
        ivBack = ((ImageView) findViewById(R.id.tl_back));
        tvTitle = ((TextView) findViewById(R.id.tl_title));
        ivRight = ((ImageView) findViewById(R.id.tl_ivRight));
        tvRight = ((TextView) findViewById(R.id.tl_tvRight));
        flRight = ((FrameLayout) findViewById(R.id.tl_flRight));
        ivBack.setOnClickListener(this);
        /*tvTitle.setOnClickListener(this);*/
        flRight.setOnClickListener(this);
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public ImageView getIvBack() {
        return ivBack;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTitleText(String titleText) {
        tvTitle.setText(titleText+"");
    }
    public void setTitleText(String titleText, int textColor) {
        tvTitle.setText(titleText);
        tvTitle.setTextColor(textColor);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param ivLeftId
     */
    public void setIvLeftId(int ivLeftId) {
        ivBack.setImageResource(ivLeftId);
        ivBack.setVisibility(VISIBLE);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param ivRightId
     */
    public void setIvRightId(int ivRightId) {
        ivRight.setImageResource(ivRightId);
        ivRight.setVisibility(VISIBLE);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param text
     * @param textColor
     */
    public void setTvRightText(String text, int textColor) {
        tvRight.setText(text + "");
        tvRight.setTextColor(textColor);
        tvRight.setVisibility(VISIBLE);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param text
     */
    public void setTvRightText(String text) {
        tvRight.setText(text + "");
        tvRight.setVisibility(VISIBLE);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param padding
     */
    public void setIvLeftPadding(int padding) {
        ivBack.setPadding(padding, padding, padding, padding);
    }

    /**
     * 设置资源Id并将其显示
     *
     * @param padding
     */
    public void setIvRightPadding(int padding) {
        ivRight.setPadding(padding, padding, padding, padding);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tl_back:
                if (mTitleLayoutClickListener != null)
                    mTitleLayoutClickListener.leftClick();
                break;
           /* case R.id.tl_title:
                if (mTitleLayoutClickListener != null)
                    mTitleLayoutClickListener.centerClick();
                break;*/
            case R.id.tl_flRight:
                if (mTitleLayoutClickListener != null)
                    mTitleLayoutClickListener.rightClick();
                break;

        }
    }

    public interface TitleLayoutClickListener {
        void leftClick();

        /*void centerClick();*/

        void rightClick();
    }

    private TitleLayoutClickListener mTitleLayoutClickListener;

    public void setTitleLayoutClickListener(TitleLayoutClickListener titleLayoutClickListener) {
        mTitleLayoutClickListener = titleLayoutClickListener;
    }
}

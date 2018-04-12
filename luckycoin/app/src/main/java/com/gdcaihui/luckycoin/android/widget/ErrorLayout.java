package com.gdcaihui.luckycoin.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gdcaihui.luckycoin.android.R;

/**
 * Created by Walke.Z
 * on 2017/10/10. 01.
 * email：1126648815@qq.com
 */
public class ErrorLayout extends LinearLayout {
    private ImageView ivIcon;
    private TextView tvTips;
    private Button btToHome;

    public ErrorLayout(Context context) {
        this(context,null);
    }

    public ErrorLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ErrorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_error_layout,this);
        ivIcon = ((ImageView) findViewById(R.id.el_ivIcon));
        tvTips = ((TextView) findViewById(R.id.el_tvTips));
        btToHome = ((Button) findViewById(R.id.el_btToHome));

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ErrorLayout, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ErrorLayout_img:
                    int resourceId = a.getResourceId(attr, R.mipmap.logo3x);
                    ivIcon.setImageResource(resourceId);
                    break;
                case R.styleable.ErrorLayout_text:
                    String str = a.getString(attr);
                    if (!TextUtils.isEmpty(str))
                        tvTips.setText(str);
                    break;
            }
        }
        a.recycle();
    }

    public ImageView getIvIcon() {
        return ivIcon;
    }

    public TextView getTvTips() {
        return tvTips;
    }

    public Button getBtToHome() {
        return btToHome;
    }
}

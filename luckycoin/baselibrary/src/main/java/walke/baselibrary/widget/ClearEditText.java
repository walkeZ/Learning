package walke.baselibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import walke.baselibrary.R;
import walke.baselibrary.tools.ViewUtil;


/**
 * Created by Xiongrui on 2016/7/4.
 * walke改
 */
@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context);
        return;
    }

    private void init(Context context) {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        this.mClearDrawable = getCompoundDrawables()[2];
        if (this.mClearDrawable == null)
            this.mClearDrawable = getResources().getDrawable(R.mipmap.icon_edittext_clear);

        int drawablePadding = getCompoundDrawablePadding();

        //this.mClearDrawable.setBounds(0,0, this.mClearDrawable.getIntrinsicWidth()-drawablePadding, this.mClearDrawable.getIntrinsicHeight()-drawablePadding);
        int temp = ViewUtil.dipTopx(context, 13);
        this.mClearDrawable.setBounds(-temp,0, this.mClearDrawable.getIntrinsicWidth()-drawablePadding-temp, this.mClearDrawable.getIntrinsicHeight()-drawablePadding);
        //默认设置隐藏图标
        this.setClearIconVisible(false);
        //设置焦点改变的监听
        this.setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        this.addTextChangedListener(this);
        return;
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int left = ViewUtil.dipTopx(this.getContext(), 11);
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()-left)
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus)
            this.setClearIconVisible(this.getText().length() > 0);
        else
            this.setClearIconVisible(false);
        return;
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        if (this.isEnabled()) {
            Drawable right = visible ? this.mClearDrawable : null;
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], right, this.getCompoundDrawables()[3]);
        }
        return;
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (this.hasFoucs)
            this.setClearIconVisible(s.length() > 0);
        return;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        return;
    }

    @Override
    public void afterTextChanged(Editable s) {
        return;
    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
        return;
    }

    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(300);
        return translateAnimation;
    }
}

package com.walke.demo.custom_refresh.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.walke.demo.R;

/**
 * Created by walke on 2018/11/8.
 * email:1032458982@qq.com
 */

public class RefreshLayout extends ViewGroup {
    private View refreshHeader;
    private View target;
    private int currentTargetOffsetTop; // target偏移距离
    private boolean hasMeasureHeader;   // 是否已经计算头部高度
    private int touchSlop;
    private int headerHeight;       // header高度
    private int totalDragDistance;  // 需要下拉这个距离才进入松手刷新状态，默认和header高度一致

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        // 添加默认的头部，先简单的用一个ImageView代替头部
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.one_piece);
        imageView.setBackgroundColor(Color.BLACK);
        setRefreshHeader(imageView);
    }

    /**
     * 设置自定义header
     */
    public void setRefreshHeader(View view) {
        if (view != null && view != refreshHeader) {
            removeView(refreshHeader);

            // 为header添加默认的layoutParams
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(layoutParams);
            }
            refreshHeader = view;
            addView(refreshHeader);
        }
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (target == null) {
            ensureTarget();
        }

        if (target == null) {
            return;
        }

        // ----- measure target -----
        // target占满整屏
        target.measure(MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));

        // ----- measure refreshView-----
        measureChild(refreshHeader, widthMeasureSpec, heightMeasureSpec);
        if (!hasMeasureHeader) { // 防止header重复测量
            hasMeasureHeader = true;
            headerHeight = refreshHeader.getMeasuredHeight(); // header高度
            totalDragDistance = headerHeight;   // 需要pull这个距离才进入松手刷新状态
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }

        if (target == null) {
            ensureTarget();
        }
        if (target == null) {
            return;
        }

        // onLayout执行的时候，要让target和header加上偏移距离（初始0），因为有可能在滚动它们的时候，child请求重新布局，从而导致target和header瞬间回到原位。

        // target铺满屏幕
        final View child = target;
        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop() + currentTargetOffsetTop;
        final int childWidth = width - getPaddingLeft() - getPaddingRight();
        final int childHeight = height - getPaddingTop() - getPaddingBottom();
        child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);

        // header放到target的上方，水平居中
        int refreshViewWidth = refreshHeader.getMeasuredWidth();
        refreshHeader.layout((width / 2 - refreshViewWidth / 2),
                -headerHeight + currentTargetOffsetTop,
                (width / 2 + refreshViewWidth / 2),
                currentTargetOffsetTop);
    }

    /**
     * 将第一个Child作为target
     */
    private void ensureTarget() {
        // Don't bother getting the parent height if the parent hasn't been laid
        // out yet.
        if (target == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (!child.equals(refreshHeader)) {
                    target = child;
                    break;
                }
            }
        }
    }

}

package com.walke.anim.springback;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by walke.Z on 2018/4/16.
 * RecyclerView 不是继承 AbsListView 故无效
 * 用其他方法实现： 待研究
 */

public class SpringBackRecyclerView extends RecyclerView {
    public SpringBackRecyclerView(Context context) {
        super(context);
    }

    public SpringBackRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpringBackRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int mMaxOverScrollY = 500;// 滑动最大距离 默认300

    /**
     * 设置最大的回弹距离
     * @param maxOverScrollY
     */
    public void setMaxOverScrollY(int  maxOverScrollY){
        this.mMaxOverScrollY = maxOverScrollY;
    }

    /**
     *
     * @param deltaX  继续滑动x方向的距离
     * @param deltaY  继续滑动y方向的距离     负：表示顶部到头   正：表示底部到头
     * @param scrollX x方向滑动的距离
     * @param scrollY y方法滑动的距离
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX x方向最大可以滚动的距离
     * @param maxOverScrollY y方向最大可以滚动的距离
     * @param isTouchEvent 是手指拖动滑动     false:表示fling靠惯性滑动;
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverScrollY, isTouchEvent);
    }


}

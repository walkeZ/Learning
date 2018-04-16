package com.walke.anim.springback;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by walke.Z on 2018/4/16.
 * Android ndk9 之后才新增的API.
 * 参考：最简单的方式实现ListView 拉出回弹效果,阻尼效果
 *      https://blog.csdn.net/jsonnan/article/details/73526783
 */

public class SpringBackListView extends ListView {

    private int mMaxOverScrollY = 500;// 滑动最大距离 默认300

    public SpringBackListView(Context context) {
        this(context,null);
    }

    public SpringBackListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpringBackListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

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

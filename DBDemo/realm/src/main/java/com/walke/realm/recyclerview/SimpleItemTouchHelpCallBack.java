package com.walke.realm.recyclerview;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.walke.realm.adapter.StudentAdapter;

/**
 * Created by walke.Z on 2018/5/25.
 * RecyclerView进阶：使用ItemTouchHelper实现拖拽和侧滑删除
 *      https://www.jianshu.com/p/0c1984bc9383
 */

public class SimpleItemTouchHelpCallBack extends ItemTouchHelper.Callback{

    private StudentAdapter mAdapter;

    public SimpleItemTouchHelpCallBack(StudentAdapter adapter) {
        mAdapter = adapter;
    }

    /** 该方法用于返回可以滑动的方向 比如从右到左，从左到右，允许上下拖动等
     *  我们一般使用makeMovementFlags(int,int) ,或者makeFlag(int,int)来构造我们的返回值
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        return 0;
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFalgs = ItemTouchHelper.LEFT;// 只允许从右到左侧滑
        return makeMovementFlags(dragFlags,swipeFalgs);
    }

    /** 当用户拖动一个Item进行上下移动从旧位置到新位置时会回调该方法。
     *  在该方法内，我们可以调用Adapter的notifyItemMove方法来交换两个View Holder的位置，最后返回true。
     *  表示被拖动的ViewHolder已经移动到目的位置。所以如果要实现拖动交换位置可以重写该方法(前提是支持上下拖动)
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        return false;
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    /**用户左右滑动Item达到删除条件时，会调用该方法，一般手指触摸滑动的距离达到RecyclerView宽度的一半时，
     * 再松开手指，此时该Item会继续向原先滑动方向滑过去并且调用onSwiped方法进行删除
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    /** 该方法返回true时，表示支持长按拖动，即长按ItemView后才可以拖动，我们遇到的场景一般也是这样的。默认是返回true。
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
//        return super.isLongPressDragEnabled();
        return true;
    }

    /** 该方法返回true时，表示如果用户触摸并左右滑动了View，那么可以执行滑动删除操作，即可以调用到onSwiped()方法。默认是返回true
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
//        return super.isItemViewSwipeEnabled();
        return true;
    }

    /** 从静止状态变为拖拽或者滑动的时候会回调该方法，参数actionState表示当前的状态。
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    /** 当用户操作完毕某个item并且其动画也结束后会调用该方法，一般我们在该方法内恢复ItemView的初始状态，防止由于复用而产生的显示错乱问题。
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    /** 我们可以在这个方法内实现我们自定义的交互规则或者自定义的动画效果。
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}

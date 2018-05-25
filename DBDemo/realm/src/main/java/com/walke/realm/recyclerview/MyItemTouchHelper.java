package com.walke.realm.recyclerview;

/**
 * Created by walke.Z on 2018/5/25.
 * RecyclerView进阶：使用ItemTouchHelper实现拖拽和侧滑删除
 *      https://www.jianshu.com/p/0c1984bc9383
 */

public interface MyItemTouchHelper {

    /** 数据交换
     * @param fromPosition
     * @param toPosition
     */
    void onItemMove(int fromPosition,int toPosition);

    /** 数据删除
     * @param position
     */
    void onItemDismiss(int position);

}

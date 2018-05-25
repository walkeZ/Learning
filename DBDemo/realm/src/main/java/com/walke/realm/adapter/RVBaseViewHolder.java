package com.walke.realm.adapter;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by walke.Z on 2018/5/24. 加个人注释
 * 原著：https://github.com/RaphetS/DemoRealm
 */

public class RVBaseViewHolder extends RecyclerView.ViewHolder {
    /**
     * SparseArray是android里为<Interger,Object>这样的Hashmap而专门写的class,目的是提高效率，其核心是折半查找函数（binarySearch）
     * //https://blog.csdn.net/xiangzhihong8/article/details/52139091
     */
    SparseArray<View> mViews;
    View mItemView;

    public RVBaseViewHolder(View itemView) {
        super(itemView);
        mItemView=itemView;
        mViews=new SparseArray<>();
    }

    /** 泛型封装通用方法：通过viewID获取View
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view==null){
            view=mItemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    /** -------------------TextView---------------------------*/
    public RVBaseViewHolder setText(int textViewId,String text){
        TextView tv = getView(textViewId);
        tv.setText(text);
        if (TextUtils.isEmpty(text))
            tv.setText("");
        return this;//返回当前对象，这是链式结构封装
    }
    public RVBaseViewHolder setText(int textViewId,int textRes){
        TextView tv = getView(textViewId);
        tv.setText(textRes);
        return this;//返回当前对象，这是链式结构封装
    }
    public RVBaseViewHolder setTextColor(int textViewId,int color){
        TextView tv = getView(textViewId);
        tv.setTextColor(color);
        return this;
    }

    /** -------------------ImageView---------------------------*/
    public RVBaseViewHolder setImageResource(int imageViewId, int imgRes){
        ImageView iv=getView(imageViewId);
        iv.setImageResource(imgRes);
        return this;
    }
    public RVBaseViewHolder setBitmap(int imageViewId, Bitmap bm){
        ImageView iv=getView(imageViewId);
        iv.setImageBitmap(bm);
        return this;
    }
    public RVBaseViewHolder setBitmap(int imageViewId, String imgUrl){
        ImageView iv=getView(imageViewId);
//        Glide.with(iv.getContext()).load(imgUrl).into(iv);
        return this;
    }
    /** 所有view */
    public RVBaseViewHolder setBackgroundColor(int viewId, int color){
        getView(viewId).setBackgroundColor(color);
        return this;
    }
    public RVBaseViewHolder setVisible(int viewId,int visible){
        getView(viewId).setVisibility(visible);
        return this;
    }
    public RVBaseViewHolder setAlpha(int viewId,float alpha){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            getView(viewId).setAlpha(alpha);
        }else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation a = new AlphaAnimation(alpha, alpha);
            a.setDuration(0);
            a.setFillAfter(true);
            getView(viewId).startAnimation(a);
        }
        return this;
    }
    /** 实现了Checkabled的view */
    public RVBaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件监听
     */
    public RVBaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {

        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public RVBaseViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public RVBaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}

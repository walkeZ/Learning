package com.walke.realm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by walke.Z on 2018/5/24.
 */

public abstract class RVBaseAdapter<T> extends RecyclerView.Adapter<RVBaseViewHolder> {
    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;

    public RVBaseAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, null, false);
        //用parent在布局中设置的属性才会有效，不然可能出现机型适配问题
        View inflate = LayoutInflater.from(parent.getContext()).inflate(mLayoutId,parent,false);
        return new RVBaseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, final int position) {
        itemView(mContext,holder,mDatas.get(position));
        if (mOnItemClickListener!=null){
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });

        }
        if (mOnLongItemClickListener!=null){
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnLongItemClickListener.onItemLongClick(v,position);
                }
            });
        }


    }

    protected abstract void itemView(Context context, RVBaseViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    /**-------------自定义点击事件----------------*/
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface onLongItemClickListener {
        void onItemLongClick(View view, int postion);
    }
    private onLongItemClickListener mOnLongItemClickListener;

    public void setOnLongItemClickListener(onLongItemClickListener onLongItemClickListener) {
        mOnLongItemClickListener = onLongItemClickListener;
    }
}

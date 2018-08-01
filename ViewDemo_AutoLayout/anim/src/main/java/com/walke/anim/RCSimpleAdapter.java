package com.walke.anim;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by walke.Z on 2017/10/10.
 */

public class RCSimpleAdapter extends RecyclerView.Adapter<RCSimpleAdapter.SimpleViewHolder> {
    private String[] datas;

    public RCSimpleAdapter(String[] skills) {
        datas=skills;
    }

    @Override
    public RCSimpleAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_recyclerview, parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(dataBinding.getRoot());
        viewHolder.setBinding(dataBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();
        //参数一:包名.BR.布局variable的name
        binding.setVariable(com.walke.anim.BR.text,datas[position]);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }


    class SimpleViewHolder extends RecyclerView.ViewHolder{

        private ViewDataBinding binding;//在onCreateViewHolder设置(赋值)，在onBindViewHolder中获取使用

        public SimpleViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null!=mOnAdapterItemClickListener)
                        mOnAdapterItemClickListener.onAdapterItemClick(getLayoutPosition());
                }
            });
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }

    }

    public interface OnAdapterItemClickListener{
        void onAdapterItemClick(int position);
    }

    private OnAdapterItemClickListener mOnAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        mOnAdapterItemClickListener = onAdapterItemClickListener;
    }
}

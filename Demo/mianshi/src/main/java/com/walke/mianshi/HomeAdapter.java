package com.walke.mianshi;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by walke.Z on 2018/8/1.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] datas;

    public HomeAdapter(String[] datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_recyclerview, parent, false);
        HomeHolder viewHolder = new HomeHolder(dataBinding.getRoot());
        viewHolder.setBinding(dataBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        ViewDataBinding binding = homeHolder.getBinding();
        //参数一:包名.BR.布局variable的name
        binding.setVariable(com.walke.mianshi.BR.title, datas[position]);
        binding.executePendingBindings();
        if (position % 2 == 0)
            homeHolder.mImageView.setImageResource(R.mipmap.ic_launcher);
        else
            homeHolder.mImageView.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    class HomeHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private ViewDataBinding mBinding;// 在onCreateViewHolder设置(赋值)，在onBindViewHolder中获取使用

        public HomeHolder(View itemView) {
            super(itemView);
            mImageView = ((ImageView) itemView.findViewById(R.id.home_item_icon));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null)
                        mOnItemClickListener.onItemClick(getLayoutPosition());
                }
            });
        }

        public void setBinding(ViewDataBinding binding) {
            mBinding = binding;
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

    /**********接口回调***********/

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}

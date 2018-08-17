package com.walke.demo.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walke.demo.R;

import java.util.List;

/**
 * Created by walke.Z on 2018/8/6.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> datas;

    public MainAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);// home_item 根布局设置的属性有效
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        homeHolder.mTextView.setText(""+datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder{

        private final TextView mTextView;

        public HomeHolder(View itemView) {
            super(itemView);
            mTextView = ((TextView) itemView.findViewById(R.id.main_item_text));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null)
                        mOnItemClickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    /***************自定义点击事件*****************/
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}

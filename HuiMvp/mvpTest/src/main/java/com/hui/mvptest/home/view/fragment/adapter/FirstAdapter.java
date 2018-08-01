package com.hui.mvptest.home.view.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hui.mvptest.R;

/**
 * Created by walke.Z on 2017/8/9.
 */

public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private String[] datas;
    private LayoutInflater inflater;
    private View mInflate;

    public FirstAdapter(Context context, String[] datas) {
        mContext = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflate = inflater.inflate(R.layout.item_first_recyclerview, parent,false);
        return new FirstViewHolder(mInflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FirstViewHolder) holder).tvText.setText(datas[position]);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }


    class FirstViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        FirstViewHolder(View view) {
            super(view);
            tvText = ((TextView) view.findViewById(R.id.ifr_text));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClicklistener != null)
                        mOnItemClicklistener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    public interface OnItemClicklistener {
        void onItemClick(int position);
    }

    private OnItemClicklistener mOnItemClicklistener;

    public void setOnItemClicklistener(OnItemClicklistener onItemClicklistener) {
        mOnItemClicklistener = onItemClicklistener;
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    /*@Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        ButterKnife.bind(this, mInflate);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }*/
}

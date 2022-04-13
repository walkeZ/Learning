package com.walke.ktpractice.java2kotlin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.walke.ktpractice.R;

import java.util.List;

/**
 * author Walke - 2020/9/22 9:19 AM
 * email 1126648815@qq.ocm
 * dec
 */
class MyAdapter extends BaseAdapter {

    private final List<String> mList;


    MyAdapter(List<String> list){
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_string, parent, false);
            holder=new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
            holder.tv.setText(mList.get(position));
        }
        return convertView;
    }
    class MyViewHolder{
        private final TextView tv;

        MyViewHolder(View root){
            tv = root.findViewById(R.id.ls_tvContent);
        }
    }
}

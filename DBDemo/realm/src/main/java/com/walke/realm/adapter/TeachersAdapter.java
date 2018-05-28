package com.walke.realm.adapter;

import android.content.Context;

import com.walke.realm.R;
import com.walke.realm.entity.Teacher;

import java.util.List;

/**
 * Created by walke.Z on 2018/5/28.
 */

public class TeachersAdapter extends RVBaseAdapter<Teacher> {

    public TeachersAdapter(Context context, List<Teacher> teachers, int mLayoutId) {
        super(context,teachers,mLayoutId);
    }

    @Override
    protected void itemView(Context context, RVBaseViewHolder holder, Teacher teacher) {
        holder.setText(R.id.lt_tvId,"id: "+teacher.getId());
        holder.setText(R.id.lt_tvName,"name: "+teacher.getName()+"    age:"+teacher.getAge());

    }
}

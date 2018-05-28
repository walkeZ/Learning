package com.walke.realm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.walke.realm.R;
import com.walke.realm.entity.Student;
import com.walke.realm.util.RealmHelper;

import java.util.List;

/**
 * Created by walke.Z on 2018/5/24.
 */

public class StudentAdapter_old extends RVBaseAdapter<Student> {
    private RealmHelper mRealmHelper;

    public StudentAdapter_old(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        mRealmHelper = new RealmHelper(context);
    }

    @Override
    protected void itemView(Context context, RVBaseViewHolder holder, final Student student) {
        holder.setText(R.id.ls_tvName,"姓名："+student.getName()+"     age: "+student.getAge());
        holder.setText(R.id.ls_tvId,"学号："+student.getId());
        final ImageView iv = holder.getView(R.id.ls_img);
        boolean studentExist = mRealmHelper.isStudentExist(student.getId());
        if (studentExist)
            iv.setSelected(true);
        else
            iv.setSelected(false);

    }
}

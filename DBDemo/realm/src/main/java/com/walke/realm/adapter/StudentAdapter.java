package com.walke.realm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.walke.realm.R;
import com.walke.realm.entity.Student;
import com.walke.realm.recyclerview.MyItemTouchHelper;
import com.walke.realm.util.RealmHelper;

import java.util.Collections;
import java.util.List;

/**
 * Created by walke.Z on 2018/5/24.
 */

public class StudentAdapter extends RVBaseAdapter<Student> implements MyItemTouchHelper {
    private List<Student> mDatas;
    private RealmHelper mRealmHelper;

    public StudentAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        mRealmHelper = new RealmHelper(context);
        mDatas = datas;
    }

    @Override
    protected void itemView(Context context, RVBaseViewHolder holder, final Student student) {
        holder.setText(R.id.ls_tvName,"姓名："+student.getName()+ "      年龄："+student.getAge());
        holder.setText(R.id.ls_tvId,"学号："+student.getId());
        final ImageView iv = holder.getView(R.id.ls_img);
        boolean studentExist = mRealmHelper.isStudentExist(student.getId());
        if (studentExist)
            iv.setSelected(true);
        else
            iv.setSelected(false);

    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(mDatas,fromPosition,toPosition);
        notifyDataSetChanged();
    }

    @Override
    public void onItemDismiss(int position) {
        //移除数据
        mDatas.remove(position);
        notifyDataSetChanged();
    }
}

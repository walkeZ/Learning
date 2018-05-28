package com.walke.realm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.walke.realm.R;
import com.walke.realm.entity.Teacher;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by walke.Z on 2018/5/24.
 */

public class AsyncTeacheresAdapter extends RVBaseAdapter<Teacher> {
    private final Realm mRealm;
    private List<Teacher> mDatas;
    private RealmAsyncTask mDeleteTask;
    private RealmAsyncTask mAddTask;

    public AsyncTeacheresAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        mDatas = datas;
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void itemView(Context context, RVBaseViewHolder holder, final Teacher teacher) {
        holder.setText(R.id.lt_tvName, "课程：" + teacher.getName() + "      成绩：" + teacher.getAge());
        holder.setText(R.id.lt_tvId, "id：" + teacher.getId());
        final ImageView iv = holder.getView(R.id.lt_ivLike);
//        if (isLike(teacher.getId()))
//            iv.setSelected(true);
//        else
//            iv.setSelected(false);

        iv.setSelected(isLike(teacher.getId()));

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv.isSelected()) {
                    delete(teacher.getId(), iv);
                } else {
                    add(teacher,iv);
                }
            }
        });


    }


    private boolean isLike(int id) {
        Teacher teacher = mRealm.where(Teacher.class).equalTo("id", id).findFirst();
        if (teacher == null)
            return false;
        return true;
    }

    /**
     * @param id
     * @param iv
     */
    private void delete(final int id, final ImageView iv) {
        mDeleteTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Teacher teacher = realm.where(Teacher.class).equalTo("id", id).findFirst();
                teacher.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(iv.getContext(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                iv.setSelected(false);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(iv.getContext(), "取消收藏失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     */
    private void add(final Teacher teacher, final ImageView iv) {
        mAddTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(teacher);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(iv.getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                iv.setSelected(true);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(iv.getContext(), "收藏失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cancelTask() {
        if (mAddTask != null && !mAddTask.isCancelled())
            mAddTask.cancel();
        if (mDeleteTask != null && !mDeleteTask.isCancelled())
            mDeleteTask.cancel();
    }

}

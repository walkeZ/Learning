package com.walke.realm.async;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.walke.realm.R;
import com.walke.realm.activity.BaseActivity;
import com.walke.realm.adapter.RVBaseAdapter;
import com.walke.realm.adapter.TeachersAdapter;
import com.walke.realm.constants.IntentCode;
import com.walke.realm.constants.IntentStr;
import com.walke.realm.entity.Teacher;
import com.walke.realm.recyclerview.DefaultItemTouchHelpCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by walke.Z on 2018/5/28.
 */

public class AsyncQueryActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.aaq_recyclerView)
    RecyclerView mRecyclerView;

    private List<Teacher> mTeachers=new ArrayList<>();
    private TeachersAdapter mAdapter;
    private Realm mRealm;
    private RealmAsyncTask mDeleteTask;
    private RealmResults<Teacher> mDatas;


    @Override
    protected int rootLayout() {
        return R.layout.activity_async_query;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar,"异步查询(改)");
        mRealm = Realm.getDefaultInstance();

        initRecycerView();
        getData();

    }

    private void initRecycerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TeachersAdapter(this,mTeachers, R.layout.list_teacher);
        mRecyclerView.setAdapter(mAdapter);
        setSwipeDelete();

        mAdapter.setOnItemClickListener(new RVBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(AsyncQueryActivity.this, AsyncUpdateActivity.class);
                intent.putExtra(IntentStr.TEACHER,mTeachers.get(position));
                startActivityForResult(intent, IntentCode.UPDATE_REQUEST_CODE);
            }
        });
    }

    private void setSwipeDelete() {
        DefaultItemTouchHelpCallback callback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                delete(mTeachers.get(adapterPosition));
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                return false;
            }
        });
        callback.setDragEnable(false);
        callback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void delete(final Teacher teacher) {
        mDeleteTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Teacher tc = realm.where(Teacher.class).equalTo("id", teacher.getId()).findFirst();
                if (tc==null)
                    toast("tc为空，操作失败");
                else
                    tc.deleteFromRealm();
//                if (teacher!=null)
//                    teacher.deleteFromRealm();
//                else
//                    toast("teacher 为空，操作失败");
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                toast("删除成功");
                boolean remove = mTeachers.remove(teacher);
                mAdapter.notifyDataSetChanged();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                toast("删除失败");
            }
        });
    }



    public void getData() {
        mDatas = mRealm.where(Teacher.class).findAllAsync();
        mDatas.addChangeListener(new RealmChangeListener<RealmResults<Teacher>>() {
            @Override
            public void onChange(RealmResults<Teacher> element) {
                Log.i("walke", "onChange: --------");
                toast("查询成功");
                element=element.sort("id");
                List<Teacher> list = mRealm.copyFromRealm(element);
                mTeachers.clear();
                mTeachers.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==IntentCode.UPDATE_REQUEST_CODE){
            getData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDeleteTask!=null&&!mDeleteTask.isCancelled())
            mDeleteTask.cancel();
    }
}

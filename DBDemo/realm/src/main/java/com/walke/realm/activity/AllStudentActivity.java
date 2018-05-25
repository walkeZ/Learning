package com.walke.realm.activity;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.walke.realm.R;
import com.walke.realm.adapter.RVBaseAdapter;
import com.walke.realm.adapter.StudentAdapter;
import com.walke.realm.constants.IntentCode;
import com.walke.realm.constants.IntentStr;
import com.walke.realm.entity.Student;
import com.walke.realm.recyclerview.DefaultItemTouchHelpCallback;
import com.walke.realm.util.RealmHelper;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by walke.Z on 2018/5/25.
 */

public class AllStudentActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.aas_recyclerView)
    RecyclerView mAasRecyclerView;
    private RealmHelper mRealmHelper;
    private List<Student> mStudents;
    private StudentAdapter mAdapter;

    @Override
    protected int rootLayout() {
        return R.layout.activity_all_student;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar,"查询所有");
        initData();

    }

    private void initData() {
        mRealmHelper = new RealmHelper(this);
        mStudents = mRealmHelper.queryAll();
        mAasRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAasRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new StudentAdapter(this,mStudents, R.layout.list_student_5_0_shipei);
        mAasRecyclerView.setAdapter(mAdapter);
        //SnackBar使用心得  http://www.cnblogs.com/Fndroid/p/5232358.html

        setSwipeDelete();

        Snackbar.make(mAasRecyclerView,"滑动删除item、点击Item进入修改界面", BaseTransientBottomBar.LENGTH_LONG).show();

        mAdapter.setOnItemClickListener(new RVBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent it=new Intent(AllStudentActivity.this,UpdateActivity.class);
                it.putExtra(IntentStr.STUDENT,mStudents.get(position));
                startActivityForResult(it, IntentCode.UPDATE_REQUEST_CODE);
            }
        });
    }

    /**
     * 设置滑动删除
     */
    private void setSwipeDelete() {
        // TODO: 2018/5/25  待实现滑动Item删除效果
//        // 先实例化Callback
//        SimpleItemTouchHelpCallBack callBack = new SimpleItemTouchHelpCallBack(mAdapter);
//        // 用Callback构造ItemtouchHelper
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
//        // 调用ItemTouchHelper的attachToRecyclerView方法建立联系
//        itemTouchHelper.attachToRecyclerView(mAasRecyclerView);

        DefaultItemTouchHelpCallback mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                //删除数据库数据
                mRealmHelper.deleteOne(mStudents.get(adapterPosition));
                //滑动删除
                mStudents.remove(adapterPosition);
                mAdapter.notifyItemRemoved(adapterPosition);

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                Collections.swap(mStudents,srcPosition,targetPosition);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
        mCallback.setDragEnable(true);//false
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mAasRecyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK==resultCode&&IntentCode.UPDATE_REQUEST_CODE==requestCode){
            mStudents.clear();
            mStudents.addAll(mRealmHelper.queryAll());
            mAdapter.notifyDataSetChanged();
        }
    }
}

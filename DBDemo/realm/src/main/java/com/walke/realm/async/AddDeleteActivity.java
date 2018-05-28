package com.walke.realm.async;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.walke.realm.R;
import com.walke.realm.activity.BaseActivity;
import com.walke.realm.adapter.AsyncTeacheresAdapter;
import com.walke.realm.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by walke.Z on 2018/5/28.
 */

public class AddDeleteActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.aad_recyclerView)
    RecyclerView mRecyclerView;

    private List<Teacher> mTeachers;
    private String[] letters=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private String[] letters1=new String[]{"a","c","u","p","q","y"};
    private AsyncTeacheresAdapter mAdapter;

    @Override
    protected int rootLayout() {
        return R.layout.activity_add_delete;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar,"异步增删");
        initData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AsyncTeacheresAdapter(this,mTeachers, R.layout.list_teacher);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mTeachers = new ArrayList<>();
        for (int i = 0; i < letters.length; i++) {
            String name = letters[i] + letters1[i % letters1.length] + letters1[i % letters1.length];
            Teacher teacher = new Teacher(name, 30 + i % 8, 10000 + i);
            mTeachers.add(teacher);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancelTask();
    }
}

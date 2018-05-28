package com.walke.realm.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.walke.realm.R;
import com.walke.realm.adapter.StudentAdapter_old;
import com.walke.realm.entity.Student;
import com.walke.realm.util.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2018/5/28.
 */

public class ConditionQueryActivity extends BaseActivity {


    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.aqc_etId)
    EditText mEtId;
    @BindView(R.id.aqc_etName)
    EditText mEtAge;
    @BindView(R.id.aqc_recyclerView)
    RecyclerView mRecyclerView;
    private RealmHelper mRealmHelper;
    private ArrayList<Student> mStudents;
    private StudentAdapter_old mAdapter;

    @Override
    protected int rootLayout() {
        return R.layout.activity_query_condition;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar, "条件查询");
        mRealmHelper = new RealmHelper(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStudents = new ArrayList<>();
        mAdapter = new StudentAdapter_old(this, mStudents, R.layout.list_student);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.aqc_btQueryById, R.id.aqc_btQueryByAge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aqc_btQueryById:
                String id = mEtId.getText().toString().trim();
                if (TextUtils.isEmpty(id)) {
                    toast("请输入id(学号)");
                    return;
                }
                mStudents.clear();
                Student student = mRealmHelper.queryOneById(id);
                if (student!=null)
                    mStudents.add(student);
                else
                    toast("查询结果为空");
                mAdapter.notifyDataSetChanged();

                break;
            case R.id.aqc_btQueryByAge:
                String age = mEtAge.getText().toString().trim();
                if (TextUtils.isEmpty(age)){
                    toast("input the age , please.");
                    return;
                }
                mStudents.clear();
                List<Student> list = mRealmHelper.queryByAge(Integer.parseInt(age));
                if(list!=null)
                    mStudents.addAll(list);
                else
                    toast("查询结果为空");
                mAdapter.notifyDataSetChanged();

                break;
        }
    }
}

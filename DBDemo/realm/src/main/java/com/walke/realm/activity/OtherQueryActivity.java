package com.walke.realm.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.walke.realm.R;
import com.walke.realm.adapter.StudentAdapter_old;
import com.walke.realm.entity.Student;
import com.walke.realm.util.RealmHelper;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by walke.Z on 2018/5/28.
 */

public class OtherQueryActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.aqo_tvAverageAge)
    TextView mTvAverageAge;
    @BindView(R.id.aqo_tvMaxAge)
    TextView mTvMaxAge;
    @BindView(R.id.aqo_tvSumAge)
    TextView mTvSumAge;
    @BindView(R.id.aqo_recyclerView)
    RecyclerView mRecyclerView;
    private RealmHelper mRealmHelper;
    private Realm mRealm;

    @Override
    protected int rootLayout() {
        return R.layout.activity_query_other;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar, "其他查询");
        mRealmHelper = new RealmHelper(this);
        mRealm = Realm.getDefaultInstance();
        initRecyclerView();
        getAverage();
        getSum();
        setMax();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Student> students = mRealmHelper.queryAll();
        mRecyclerView.setAdapter(new StudentAdapter_old(this,students,R.layout.list_student_5_0_shipei_old));
    }

    public void getAverage() {
        double age = mRealm.where(Student.class).average("age");
        //保留两位小数
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            DecimalFormat decimalFormat = new DecimalFormat("#.00");
//        }
        BigDecimal decimal = new BigDecimal(age);
        //四舍五入
        double value = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        mTvAverageAge.setText(value + "");

    }

    public void getSum() {
        int age = mRealm.where(Student.class).sum("age").intValue();
        mTvSumAge.setText(age + "");
    }

    private void setMax() {
        int age = mRealm.where(Student.class).max("age").intValue();
        mTvMaxAge.setText(age + "");//直接填age-- Resources$NotFoundException: String resource ID #0x16
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

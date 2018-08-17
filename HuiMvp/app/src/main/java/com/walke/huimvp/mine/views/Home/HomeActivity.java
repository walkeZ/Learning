package com.walke.huimvp.mine.views.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.walke.huimvp.R;
import com.walke.huimvp.google_demo.views.UserInfoActivity;
import com.walke.huimvp.google_demo.views.UserInfoActivity2;
import com.walke.huimvp.mine.base.BaseActivity;
import com.walke.huimvp.mine.presenters.HomePresenter;
import com.walke.huimvp.mine.views.login.UserLoginActivity1;
import com.walke.huimvp.mine.views.login.UserLoginActivity2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walke.Z on 2018/8/6.
 */

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeIView<List<String>>,HomeAdapter.OnItemClickListener {


    private RecyclerView mRecyclerView;
    private HomePresenter mHomePresenter;
    private HomeAdapter mAdapter;
    private List<Class> mActivitys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = ((RecyclerView) findViewById(R.id.home_rv));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mHomePresenter = new HomePresenter(this);
        mHomePresenter.loadHomeData();

        initActivitys();

    }

    private void initActivitys() {
        mActivitys=new ArrayList<>();
        mActivitys.add(UserLoginActivity1.class);
        mActivitys.add(UserLoginActivity2.class);
        mActivitys.add(UserInfoActivity.class);
        mActivitys.add(UserInfoActivity2.class);
    }

    @Override
    public void loadSuccess(List<String> titles) {
        mAdapter = new HomeAdapter(titles);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void loadFail(Exception exc) {
        Toast.makeText(this, ""+exc.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
//        Toast.makeText(this, "position = "+position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,mActivitys.get(position)));

    }
}

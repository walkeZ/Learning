package com.walke.mianshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2018/8/1.
 */

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnItemClickListener {

    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    private String[] skill=new String[]{"简单Service案例","Service案例2","简单广播(BoackReciver)案例",};
    private List<Class> mActivities=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // initRecyclerView
        mHomeRv.setLayoutManager(new LinearLayoutManager(this));
//        HomeAdapter homeAdapter = new HomeAdapter(skill);
//        mHomeRv.setAdapter(homeAdapter);
//        homeAdapter.setOnItemClickListener(this);
        initActivitys();

    }

    private void initActivitys() {
        mActivities.add(ServiceActivity.class);
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(this,mActivities.get(position)));
    }
}

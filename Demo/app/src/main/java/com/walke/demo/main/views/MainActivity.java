package com.walke.demo.main.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.walke.demo.BaseMVPActivity;
import com.walke.demo.R;
import com.walke.demo.main.MainAdapter;
import com.walke.demo.main.presenter.MainPresenter;
import com.walke.demo.photo_album_3d.Photo3DActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainIView<List<String>>,MainAdapter.OnItemClickListener {


    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private List<Class> mActivitys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = ((RecyclerView) findViewById(R.id.main_recyclerView));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initPresenter(new MainPresenter());
        mPresenter.attachView(this);
        mPresenter.getMainData("1001");  // 对应--首页

        initActivitys();

         // 验证View中的context能否强转为Activity，发现地址一样(同一个东西)
        Log.i("walke:   MainActivity", "   onCreate:-------> this；"+this);
        Context context = findViewById(R.id.textView).getContext();
        Log.i("walke:   MainActivity", "   onCreate:-------> context: "+context);

    }

    private void initActivitys() {
        mActivitys = new ArrayList<>();
        mActivitys.add(Photo3DActivity.class);

    }


    @Override
    public void getDataSuccess(List<String> data) {
        for (String s : data) {
            Log.i("walke: MainActivity", "getDataSuccess:------> s: "+s);
        }
        List<String> list=new ArrayList<>();
        list.add("Photo3D");
        mMainAdapter = new MainAdapter(list);
        mMainAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    public void getDataFail(Exception exc) {
        Toast.makeText(this, "error: "+ exc.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettachView();
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(this,mActivitys.get(position)));
    }

    public void fabClick(View view) {
        switch (view.getId()){
            case R.id.main_fab1:
                mLoadingDialog = new Dialog(this,R.style.custom_dialog);
                mLoadingDialog.setContentView(R.layout.dialog_loading);
                break;
            case R.id.main_fab2:
                mLoadingDialog = new Dialog(this);
                mLoadingDialog.setContentView(R.layout.dialog_loading);
                break;
        }
        mPresenter.getMainData("1001");  // 对应--首页
    }
}

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
import com.walke.demo.apk.PhoneMacUtil;
import com.walke.demo.callback.TestInterface;
import com.walke.demo.image.ImageActivity;
import com.walke.demo.location_phone_system.GetLocationActivity;
import com.walke.demo.location_phone_system.GetLocationActivity2;
import com.walke.demo.location_phone_system.GetLocationActivity3;
import com.walke.demo.main.MainAdapter;
import com.walke.demo.main.presenter.MainPresenter;
import com.walke.demo.myclick.SpannableStringClickActivity;
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

        //mac
        String mac = PhoneMacUtil.getMac(this);
//        ((TextView) findViewById(R.id.textView)).setText(mac+"");
        Log.i("walke:   MainActivity", "   onCreate:---wifi----> mac: "+mac);
        // onCreate:---wifi----> mac: B0:55:08:EC:D9:C8    //手机上系统设置控制
        // onCreate:-------> mac: 58:02:03:04:05:06
        // 验证View中的context能否强转为Activity，发现地址一样(同一个东西)
        Log.i("walke:   MainActivity", "   onCreate:-------> this；"+this);
        Context context = findViewById(R.id.textView).getContext();
        Log.i("walke:   MainActivity", "   onCreate:-------> context: "+context);
        // onCreate:-------> this；com.walke.demo.main.views.MainActivity@d98d496
        // onCreate:-------> context: com.walke.demo.main.views.MainActivity@d98d496

        tt(new TestInterface() {
            @Override
            public int getSize() {
                return 10;
            }
        });


    }

    private void tt(TestInterface testInterface){
        int size = testInterface.getSize();
        Log.i("walke: ", " MainActivity:  tt:-------> size="+size);
    }


    private void initActivitys() {
        mActivitys = new ArrayList<>();
        mActivitys.add(Photo3DActivity.class);
        mActivitys.add(ImageActivity.class);
        mActivitys.add(SpannableStringClickActivity.class);
        mActivitys.add(GetLocationActivity.class);
        mActivitys.add(GetLocationActivity2.class);
        mActivitys.add(GetLocationActivity3.class);

    }


    @Override
    public void getDataSuccess(List<String> data) {
        for (String s : data) {
            Log.i("walke: MainActivity", "getDataSuccess:------> s: "+s);
        }
        List<String> list=new ArrayList<>();
        list.add("Photo3D");
        list.add("Image");
        list.add("富文本点击、超链接");
        list.add("GetLocationActivity");
        list.add("GetLocationActivity2");
        list.add("GetLocationActivity3");
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

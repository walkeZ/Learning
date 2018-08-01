package com.hui.mvptest.home.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.hui.mvptest.R;
import com.hui.mvptest.ScrollActivity;
import com.hui.mvptest.TestActivity;
import com.hui.mvptest.banner.custom.CustomBannerActivity;
import com.hui.mvptest.banner.view.BannerActivity;
import com.hui.mvptest.base.fragment.AppFragment;
import com.hui.mvptest.config.Datas;
import com.hui.mvptest.demo.view.DemoActivity;
import com.hui.mvptest.home.view.fragment.adapter.FirstAdapter;
import com.hui.mvptest.location.SystemLocationActivity;
import com.hui.mvptest.location.view.MVPLocateActivity;
import com.hui.mvptest.pager.VerticalPageActivity;
import com.hui.mvptest.refresh.linearlayout.ScrollActivity2;
import com.hui.mvptest.refresh.webview.RefreshWebViewActivity;
import com.hui.mvptest.widget.TitleLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2016/11/24.
 */

public class FirstFragment extends AppFragment {

    @BindView(R.id.ff_titleLayout)
    TitleLayout mTitleLayout;
    @BindView(R.id.ff_topViewPager)
    ViewPager mTopViewPager;
    @BindView(R.id.ff_vpFrameLayout)
    FrameLayout mVpFrameLayout;
    @BindView(R.id.ff_CollapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.ff_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ff_fab)
    FloatingActionButton mFab;
    Unbinder unbinder;
    private List<AppCompatActivity> mBaseActivities;

    @Override
    protected int rootLayoutId() {

        return R.layout.fragment_first;
    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    protected void initData() {
        //mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.fmc_recyclerView));
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置item分割线 yu当前Activity所用主题有关 TestTheme1(自定义了渐变分割线,但水平方向的分割线没显示)
        // AppTheme(默认主题灰色分割线垂直、水平方向均有分割线显示)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        FirstAdapter adapter = new FirstAdapter(getContext(), Datas.home);
        mRecyclerView.setAdapter(adapter);

        mBaseActivities=new ArrayList<>();
        mBaseActivities.add(new TestActivity());
        mBaseActivities.add(new ScrollActivity());
        mBaseActivities.add(new ScrollActivity2());
        mBaseActivities.add(new DemoActivity());
        mBaseActivities.add(new MVPLocateActivity());
        mBaseActivities.add(new SystemLocationActivity());
        mBaseActivities.add(new VerticalPageActivity());
        mBaseActivities.add(new RefreshWebViewActivity());
        mBaseActivities.add(new CustomBannerActivity());
        mBaseActivities.add(new BannerActivity());

        adapter.setOnItemClicklistener(new FirstAdapter.OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                toast(""+position);
                AppCompatActivity appCompatActivity = mBaseActivities.get(position);
                startActivity(new Intent(getContext(), appCompatActivity.getClass()));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

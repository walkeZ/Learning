package com.walke.xietiaozhebuju;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Walke.Z
 * on 2017/5/25. 50.
 * email：1126648815@qq.com
 *
 * Gson引用--添加依赖：
 *
 */
public class MineContentFragment extends LazyFragment {

    /*private XListView xListView;*/
    /*private MineContentDataAdapterOld mineContentDataAdapter;*/

    private Gson gson;
    private List<MineContentData.DataBean.LinklistBean> mLinklist;
    private RecyclerView mRecyclerView;


    public static MineContentFragment getFragment(String type, int page){

        MineContentFragment fragment = new MineContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        bundle.putInt("page",page);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        gson = new Gson();
        return R.layout.fragment_mine_content;
    }


    protected void initView(View rootView) {
        /*xListView = ((XListView) rootView.findViewById(R.id.fmc_xListView));
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);*/
        mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.fmc_recyclerView));
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置item分割线 yu当前Activity所用主题有关 TestTheme1(自定义了渐变分割线,但水平方向的分割线没显示)
        // AppTheme(默认主题灰色分割线垂直、水平方向均有分割线显示)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        int page = bundle.getInt("page", 1);//参数2--默认值
        String type = bundle.getString("type", "jing");//参数2--默认值
        String url = Urlutils.formatUrl(Urlutils.F1_FIRST_DATA_URL, type, page);
        loadData(url);
    }

    private void loadData(String url) {

        MineOkHttpUtils.loadData(url, new MineOkHttpUtils.loadDataListener() {

            private MineContentDataAdapter mineContentDataAdapter;

            @Override
            public void loadedData(String data) {
                if (data.equals("fail")){
//                    toast("网络加载失败");
                    Log.i("walke", "MineContentFragment loadedData: --------网络加载失败");
                    return;
                }
                MineContentData mineContentData = gson.fromJson(data, MineContentData.class);
                mLinklist = mineContentData.getData().getLinklist();
                /*mineContentDataAdapter = new MineContentDataAdapterOld(getContext(),mLinklist);
                xListView.setAdapter(mineContentDataAdapter);*/
                mineContentDataAdapter = new MineContentDataAdapter(getContext(),mLinklist);
                mRecyclerView.setAdapter(mineContentDataAdapter);
            }
        });


    }

}

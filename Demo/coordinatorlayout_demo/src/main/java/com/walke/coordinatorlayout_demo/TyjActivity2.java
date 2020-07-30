package com.walke.coordinatorlayout_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by walke on 2018/9/4.
 * email:1032458982@qq.com
 */

public class TyjActivity2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private View vHeader;
    private TextView tvTitle;
    private TextView tvLog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyj2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);


        mRecyclerView = (RecyclerView)findViewById(R.id.find_RecyclerView);
        testData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ListAdapter());

        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        vHeader =  findViewById(R.id.tyj2_header);
        tvTitle = (TextView) findViewById(R.id.tyj2_title);
        tvLog = (TextView) findViewById(R.id.tyj2_log);

        // 移动监听器
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int height = vHeader.getHeight();
                Log.i("walke:   TyjActivity2", "   onOffsetChanged:-------> height = "+height+"   verticalOffset="+verticalOffset);
//                if (verticalOffset <= -height / 2) {
                tvLog.setText("vHeaderHeight = "+height+"  verticalOffset = "+verticalOffset);
                if (verticalOffset <= -(height-270)) {
                    tvTitle.setText("walke");
                } else {
                    tvTitle.setText("发现动态");
                }
            }
        });
    }

    protected void testData()
    {
        mDatas = new ArrayList<String>();
        mDatas.add("当前页折叠缩放");
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }
    class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
    //--------------------------------


}

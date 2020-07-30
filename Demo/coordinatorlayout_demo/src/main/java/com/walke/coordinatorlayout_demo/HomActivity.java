package com.walke.coordinatorlayout_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by walke on 2018/9/4.
 * email:1032458982@qq.com
 * 参考：https://www.jianshu.com/p/5287d090e777
 */

public class HomActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private ArrayList<Class> mActivities;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = (RecyclerView)findViewById(R.id.find_RecyclerView);
        testData();
        initActivitys();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ListAdapter());
    }

    private void initActivitys() {
        mActivities = new ArrayList<>();
        mActivities.add(TyjActivity.class);
        mActivities.add(TyjActivity2.class);
    }


    protected void testData()
    {
        mDatas = new ArrayList<String>();
        mDatas.add("情景1：调养家，当前页");
        mDatas.add("情景1：调养家2，TyjActivity2");
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>
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
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getLayoutPosition()<mActivities.size())
                            startActivity(new Intent(HomActivity.this,mActivities.get(getLayoutPosition())));
                    }
                });
            }
        }
    }
    //--------------------------------

}

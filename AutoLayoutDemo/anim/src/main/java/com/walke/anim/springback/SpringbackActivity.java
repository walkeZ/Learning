package com.walke.anim.springback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;

import com.walke.anim.AppActivity;
import com.walke.anim.R;
import com.walke.anim.RCSimpleAdapter;
import com.walke.anim.config.Datas;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2018/4/16.
 * AbsListView 回弹效果
 */

public class SpringbackActivity extends AppActivity {
    @BindView(R.id.as_sbListView)
    SpringBackListView sbListView;
    @BindView(R.id.as_sbRecyclerView)
    SpringBackRecyclerView sbRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_springback);
        ButterKnife.bind(this);

        sbListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Datas.TESTS));

        sbRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RCSimpleAdapter adapter = new RCSimpleAdapter(Datas.TESTS);
        sbRecyclerView.setAdapter(adapter);

    }
}

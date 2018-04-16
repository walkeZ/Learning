package com.walke.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.walke.anim.coin_wallet.CoinWalletActivity;
import com.walke.anim.config.Datas;
import com.walke.anim.explosions.ParticleExplosionsActivity;
import com.walke.anim.fireworks.FireworksEditTextActivity;
import com.walke.anim.point.PointFallActivity;
import com.walke.anim.springback.SpringbackActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 模仿荷包启动动画: https://blog.csdn.net/u012896330/article/details/52487159
 */
public class MainActivity extends AppCompatActivity implements RCSimpleAdapter.OnAdapterItemClickListener {

    @BindView(R.id.main_recyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<AppCompatActivity> mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//默认纵向
        RCSimpleAdapter adapter = new RCSimpleAdapter(Datas.ANIM_TITLES);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnAdapterItemClickListener(this);

        initActivitys();

    }

    private void initActivitys() {
        mActivities=new ArrayList<>();
        mActivities.add(new CoinWalletActivity());
        mActivities.add(new PointFallActivity());
        mActivities.add(new SpringbackActivity());
        mActivities.add(new ParticleExplosionsActivity());
        mActivities.add(new FireworksEditTextActivity());

    }

    @Override
    public void onAdapterItemClick(int position) {
        startActivity(new Intent(this,mActivities.get(position).getClass()));
    }
}

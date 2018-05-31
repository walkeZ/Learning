package com.walke.greendao;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.walke.greendao.base.BaseActivity;
import com.walke.greendao.bean.Goods;
import com.walke.greendao.dao.GoodsHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 参考：
 * https://github.com/greenrobot/greenDAO
 * https://blog.csdn.net/qq_30379689/article/details/54410838
 */
public class GreenDaoActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<Goods> mGoodses=new ArrayList<>();
    private GoodsAdapter mAdapter;
    private int num=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new GoodsAdapter(this,mGoodses, R.layout.list_item_goods);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:// queryAll                    success
                changeData( GoodsHelper.queryAll(this));
                break;
            case R.id.button2:// queryName                    success
                List<Goods> goodses = GoodsHelper.queryByPrice(this, "19.40");
                changeData(goodses);
                break;
            case R.id.button3:// queryId1                    success
                List<Goods> gs = GoodsHelper.queryById(this, 2l);
                changeData(gs);
                break;
            case R.id.button4:// queryId2                    success
                Goods goods = GoodsHelper.queryById2(this, 2l);
                changeData(goods);
                break;
            case R.id.button5:// addOne                    success
                Goods goodsOne = new Goods();
                goodsOne.setType(Goods.TYPE_LOVE);
                goodsOne.setAddress("广东深圳");
                goodsOne.setImg_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
                goodsOne.setPrice("19.40");
                goodsOne.setSell_num(2045+num);
                goodsOne.setName("正宗梅菜扣肉 聪厨梅菜扣肉干 家宴常备方便菜虎皮红烧肉 2盒包邮" + (num++)+" 收藏下单还有豉汁排骨、鱼香茄子等秘制配料赠送");
                GoodsHelper.add(this,goodsOne);
                changeData( GoodsHelper.queryAll(this));
                break;
            case R.id.button6:// addList                   success
                addList();
                break;
            case R.id.button7:// updateOne                   success
                if (!mGoodses.isEmpty()) {
                    Goods g = mGoodses.get(0);
                    g.setName("updateOne我是修改的名字");
                    GoodsHelper.updateOne(this,g);
                    changeData( GoodsHelper.queryAll(this));
                }
                break;
            case R.id.button8:// updateList
                updateList();
                break;
            case R.id.button9:// deleteAll                   success
                GoodsHelper.deleteAll(this);
                changeData( GoodsHelper.queryAll(this));
                break;
            case R.id.button10:// deleteOne                   success
                GoodsHelper.deleteOne(this,mGoodses.get(0));
                changeData( GoodsHelper.queryAll(this));
                break;
            case R.id.button11:// deleteId
                GoodsHelper.deleteById(this,4l);
                changeData( GoodsHelper.queryAll(this));
                break;
            case R.id.button12:// deleteName                   success
                GoodsHelper.deleteByPrice(this,"19.92");
                changeData( GoodsHelper.queryAll(this));
                break;
        }
    }

    private void updateList() {
        List<Goods> goodses = GoodsHelper.queryAll(this);
        List<Goods> gs=new ArrayList<>();
        if (goodses.size()>4){
            for (int i = 0; i < 4; i++) {
                Goods g = goodses.get(i);
                g.setName("updateList多个修改 "+i);
                gs.add(g);
            }
            GoodsHelper.updateList(this,gs);
            changeData( GoodsHelper.queryAll(this));
        }

    }

    private void addList() {
        List<Goods> gs=new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            Goods goodsOne = new Goods();
            goodsOne.setType(Goods.TYPE_LOVE);
            goodsOne.setAddress("广东深圳");
            goodsOne.setImg_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
            goodsOne.setPrice("19.9"+i%3);
            goodsOne.setSell_num(2045+i);
            goodsOne.setName("正宗梅菜扣肉 聪厨梅菜扣肉干 家宴常备方便菜虎皮红烧肉 " + (i+1)+" 盒包邮 收藏下单还有豉汁排骨、鱼香茄子等秘制配料赠送");
            GoodsHelper.add(this,goodsOne);
            gs.add(goodsOne);
        }
        GoodsHelper.addList(this,gs);
        changeData( GoodsHelper.queryAll(this));
    }

    private void changeData(Goods goods) {
        mGoodses.clear();
        mGoodses.add(goods);
        mAdapter.notifyDataSetChanged();
    }

    private void changeData(List<Goods> goodses) {
        mGoodses.clear();
        mGoodses.addAll(goodses);
        mAdapter.notifyDataSetChanged();
    }
}

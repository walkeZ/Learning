package com.walke.cachedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 因为目前工程无法使用第三方，只能搞一个三级缓存了三级缓存分为内存缓存，本地缓存，网络缓存；
 * 缓存的步骤依次是网络，内存，本地，然后取的顺序为内存，本地，网络。
 * 在加载图片时引用时尽量采用弱引用避免出现图片过多产生OOM.。
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PictureLoadingAdapter mBitmapAdapter;
    private List<String> mImageInfos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = ((RecyclerView) findViewById(R.id.main_RecyclerView));
        // 设置布局管理器  3表示列数
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }


    public void start(View view) {
        initData();
    }

    private void initData() {
        mImageInfos.add("http://img.redocn.com/sheying/20140825/guangzhoujiedao_2950687.jpg");//187kb
        mImageInfos.add("http://img3.redocn.com/tupian/20151026/chengshijiedaoguaijiaofengjing_5186540.jpg");//238kb
        mImageInfos.add("http://pic2.ooopic.com/12/64/41/33bOOOPIC72_1024.jpg");//239kb
        mImageInfos.add("http://pic1.win4000.com/wallpaper/8/51bb08e7a700a.jpg");//683 kb
        mImageInfos.add("http://img0.ddove.com/upload/20100713/130128151517.jpg");//3M
        mImageInfos.add("http://res.downhot.com/d/file/p/2014/07/28/828ddfb6d09f5043321d3f9648283426.jpg");//5.2M
//        mImageInfos.add("");
//        mImageInfos.add("http://pic66.nipic.com/file/20150505/11975064_151837508000_2.jpg");
        mBitmapAdapter = new PictureLoadingAdapter(this, mImageInfos);
        mRecyclerView.setAdapter(mBitmapAdapter);


        mBitmapAdapter.setOnPictureSelectListener(new PictureLoadingAdapter.OnPictureSelectListener() {
            @Override
            public void onAdd(int position) {
                // TODO: 2018/2/5 获取手机相册图片或者启动相机拍照上传
                mBitmapAdapter.addUrl("http://pic66.nipic.com/file/20150505/11975064_151837508000_2.jpg");//732kb
            }

            @Override
            public void onDelete(final int position) {
                mBitmapAdapter.delete(position);
            }
        });
    }
}

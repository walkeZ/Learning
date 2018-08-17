package com.walke.demo.photo_album_3d;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.walke.demo.BaseMVPActivity;
import com.walke.demo.R;

/**
 * Created by walke.Z on 2018/8/10.
 */

public class Photo3DActivity extends BaseMVPActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_3d);

        int[] images = { R.mipmap.photo1, R.mipmap.photo2,
                R.mipmap.photo3, R.mipmap.photo4, R.mipmap.photo5,
                R.mipmap.photo6, R.mipmap.photo7, R.mipmap.photo8, };

        ImageAdapter adapter = new ImageAdapter(this, images);
        adapter.createReflectedImages();

        GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.gallery_flow);
        galleryFlow.setAdapter(adapter);
    }
}

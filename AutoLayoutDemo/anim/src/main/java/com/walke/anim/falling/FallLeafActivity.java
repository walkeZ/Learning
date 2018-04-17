package com.walke.anim.falling;

import android.os.Bundle;

import com.walke.anim.AppActivity;
import com.walke.anim.R;

public class FallLeafActivity extends AppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fall_leaf);
        DropAnimationView view = (DropAnimationView) findViewById(R.id.drop_animation_view);
        view.setDrawables(R.mipmap.leaf_1,
                R.mipmap.leaf_2,
                R.mipmap.leaf_3,
                R.mipmap.leaf_4,
                R.mipmap.leaf_5,
                R.mipmap.leaf_6);
        view.startAnimation();
	}

}

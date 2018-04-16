package com.walke.anim.coin_wallet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.walke.anim.AppActivity;
import com.walke.anim.R;

/**
 * Created by walke.Z on 2018/4/16.
 */

public class CoinWalletActivity extends AppActivity {


    private ImageView mCoinIv;
    private ImageView mWalletIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_wallet);

        initViews();
        setAnimation();
    }


    private void initViews() {
        mCoinIv = (ImageView) findViewById(R.id.coin_iv);
        mWalletIv = (ImageView) findViewById(R.id.wallet_iv);
        Button startBtn = (Button) findViewById(R.id.start_btn);
        assert startBtn != null;
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation();
            }
        });
    }

    private void setAnimation() {
        startCoin();
        setWallet();
    }

    /**
     *
     */
    private void startCoin() {
        Animation animationTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_top_in);//平移动画的垂直掉下

        ThreeDRotateAnimation animation3D = new ThreeDRotateAnimation();
        animation3D.setRepeatCount(10);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(800);
        animationSet.addAnimation(animation3D);
        animationSet.addAnimation(animationTranslate);
        mCoinIv.startAnimation(animationSet);
    }

    private void setWallet() {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(800);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (fraction >= 0.75) {
                    valueAnimator.cancel();
                    startWallet();
                }
            }
        });
        valueAnimator.start();
    }

    /**
     * 设置钱包的缩放动画
     */
    private void startWallet() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mWalletIv, "scaleX", 1, 1.1f, 0.9f, 1);
        objectAnimator1.setDuration(600);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mWalletIv, "scaleY", 1, 0.75f, 1.25f, 1);
        objectAnimator2.setDuration(600);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.start();
    }
}

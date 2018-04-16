package com.walke.anim.explosions;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.viewexplosion.ExplosionField;
import com.example.administrator.viewexplosion.factory.ExplodeParticleFactory;
import com.example.administrator.viewexplosion.factory.FallingParticleFactory;
import com.example.administrator.viewexplosion.factory.FlyawayFactory;
import com.example.administrator.viewexplosion.factory.VerticalAscentFactory;
import com.walke.anim.AppActivity;
import com.walke.anim.R;

/**
 * Created by walke.Z on 2018/4/16.
 * 参考：https://github.com/835127729/ViewExplosion
 */

public class ParticleExplosionsActivity extends AppActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particle_explosions);
        ExplosionField explosionField = new ExplosionField(this,new FallingParticleFactory());
        explosionField.addListener(findViewById(R.id.text));
        explosionField.addListener(findViewById(R.id.layout1));

        ExplosionField explosionField2 = new ExplosionField(this,new FlyawayFactory());
        explosionField2.addListener(findViewById(R.id.text2));
        explosionField2.addListener(findViewById(R.id.layout2));

        ExplosionField explosionField4 = new ExplosionField(this,new ExplodeParticleFactory());
        explosionField4.addListener(findViewById(R.id.text3));
        explosionField4.addListener(findViewById(R.id.layout3));

        ExplosionField explosionField5 = new ExplosionField(this,new VerticalAscentFactory());
        explosionField5.addListener(findViewById(R.id.text4));
        explosionField5.addListener(findViewById(R.id.layout4));
    }
}

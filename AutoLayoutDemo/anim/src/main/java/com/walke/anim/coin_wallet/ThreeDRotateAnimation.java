/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.walke.anim.coin_wallet;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * @author 咖枯
 * @version 1.0 2016/8/18
 * user&加注释 walke
 * 3D旋转动画
 *
 * Android动画的实现原理：
 *  https://blog.csdn.net/startfromweb/article/details/7644405
 *  https://www.cnblogs.com/kross/p/4087780.html
 *
 */
public class ThreeDRotateAnimation extends Animation {
    int centerX, centerY;
    Camera camera = new Camera();

    /** 完成初始化工作,动画开始前的一些准备工作
     * @param width
     * @param height
     * @param parentWidth
     * @param parentHeight
     */
    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width / 2;
        centerY = height / 2;
        setDuration(500);
        setInterpolator(new LinearInterpolator());
    }

    /** 主要作用是把一些属性组装成一个Transformation类,这个方法会被父类的getTransformation方法调用
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateY(360 * interpolatedTime);
        camera.getMatrix(matrix);
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        camera.restore();
    }
}

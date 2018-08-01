package com.walke.anim.point;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by walke.Z on 2018/4/16.
 */

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        //fraction 与时间有关的系数，该值由差值器计算得出，由ValueAnimator调用 animateValue
        Point startPoint = (Point)startValue;
        Point endPoint = (Point)endValue;
        int x = (int) (startPoint.x + fraction*(endPoint.x - startPoint.x));
        int y = (int) (startPoint.y + fraction*(endPoint.y - startPoint.y));

//        float x = startPoint.getX() + fraction*(endPoint.getX() - startPoint.getX());
//        float y = startPoint.getY() + fraction*(endPoint.getY() - startPoint.getY());
        return new Point(x, y);
    }
}
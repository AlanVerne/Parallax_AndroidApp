package com.av.parallax;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alan on 19 May 2017.
 */

public class ParallaxedSceneView extends View {
    private final List<Drawable3D> items = new ArrayList<>();
    private ParallaxHelper parallaxHelper;

    public ParallaxedSceneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parallaxHelper = new ParallaxHelper(this);
    }

    public void stop() {
        parallaxHelper.stop();
    }
    public void resume() {
        parallaxHelper.resume();
    }

    public void add(Drawable3D drawable3D) {
        items.add(drawable3D);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        float dx = 0, dy = 0, k = 1;

        dx = getWidth() / 2;
        dy = getHeight() / 2;

        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setAntiAlias(true);

        for (Drawable3D item : items) {
            PointF pointF = parallaxHelper.applyParallax(item.x, item.y, item.z);
            item.draw(canvas, p, pointF.x+dx, pointF.y+dy, (float) parallaxHelper.getK(item.z));
        }
    }

    public void sort() {
        Collections.sort(items, (o1, o2) -> o1.z - o2.z);
    }
}

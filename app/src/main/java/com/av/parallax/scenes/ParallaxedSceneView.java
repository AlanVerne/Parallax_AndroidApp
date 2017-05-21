package com.av.parallax.scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import com.av.parallax.drawables.Drawable3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alan on 19 May 2017.
 */

public class ParallaxedSceneView extends View {
    protected final List<Drawable3D> items = new ArrayList<>();
    protected ParallaxHelper parallaxHelper;

    public ParallaxedSceneView(Context context, AttributeSet attrs) {
        super(context, attrs);

        parallaxHelper = new ParallaxHelper(this);

        fillScene();
        sort();
    }

    public void stop() {
        parallaxHelper.stop();
    }
    public void resume() {
        parallaxHelper.resume();
    }

    public void fillScene() { }

    public void add(Drawable3D drawable3D) {
        items.add(drawable3D);
    }

    public List<Drawable3D> get(int x, int y) {
        List<Drawable3D> l = new ArrayList<>();
        for (Drawable3D item : items) {
            if (item.x == x && item.y == y) l.add(item);
        }
        return l;
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
        Collections.sort(items, (o1, o2) -> Float.compare(o1.z, o2.z));
    }
}

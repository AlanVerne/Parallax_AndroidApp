package com.av.parallax;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Alan on 19 May 2017.
 */

public class Circle3D extends Drawable3D {
    private final int color;

    public Circle3D(int x, int y, int z, int w, int h, int c) {
        super(x, y, z, w, h);
        color = c;
    }

    @Override
    void draw(Canvas c, Paint p, float dx, float dy, float k) {
        float wkd2 = w*k/2;
        float hkd2 = h*k/2;
        //c.drawOval(new RectF(x+dx-wkd2, y+dy-hkd2, x+dx+wkd2, y+dy+hkd2), paint);
        p.setColor(color);
        c.drawOval(new RectF(dx-wkd2, dy-hkd2, dx+wkd2, dy+hkd2), p);
    }
}

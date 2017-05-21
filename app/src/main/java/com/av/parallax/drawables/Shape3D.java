package com.av.parallax.drawables;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Alan on 19 May 2017.
 */

public class Shape3D extends Path3D {
    public Shape3D(int x, int y, int z, int w, int h, int c) {
        super(x, y, z, w, h, c);
    }
    public Shape3D(int x, int y, int z, int w, int h, float rotate, int c) {
        super(x, y, z, w, h, rotate, c);
    }

    @Override
    public void draw(Canvas c, Paint p, float dx, float dy, float k) {
        p.setColor(color);

        p.setStyle(Paint.Style.FILL_AND_STROKE);

        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(w * strokeWidthK);

        c.save();

        c.translate(dx, dy);
        c.scale(k, k);

        c.drawPath(path, p);

        c.restore();

        p.setStyle(Paint.Style.FILL);
    }
}

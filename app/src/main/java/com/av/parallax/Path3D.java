package com.av.parallax;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Alan on 19 May 2017.
 */

public class Path3D extends Drawable3D {
    protected final int color;
    protected final float strokeWidthK = 1f / 5f;
    protected final Path path = new Path();

    public Path3D(int x, int y, int z, int w, int h, int c) {
        this(x, y, z, w, h, 0, c);
    }
    public Path3D(int x, int y, int z, int w, int h, float rotate, int c) {
        super(x, y, z, w, h);

        color = c;

        float nw = w * (1f - strokeWidthK);
        float nh = h * (1f - strokeWidthK);

        createPath(nw / 2f, nh / 2f);

        if (rotate != 0) {
            Matrix mMatrix = new Matrix();
            mMatrix.setRotate(rotate, 0, 0);
            path.transform(mMatrix);
        }
    }

    protected void createPath(float wd2, float hd2) {
    }

    @Override
    void draw(Canvas c, Paint p, float dx, float dy, float k) {
        p.setColor(color);

        p.setStyle(Paint.Style.STROKE);

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

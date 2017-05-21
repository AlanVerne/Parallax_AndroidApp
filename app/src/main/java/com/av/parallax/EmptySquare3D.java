package com.av.parallax;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Alan on 19 May 2017.
 */

public class EmptySquare3D extends Drawable3D {
    private final int color;
    private final int strokeWidth;

    public EmptySquare3D(int x, int y, int z, int w, int h, int c) {
        super(x, y, z, w, h);
        color = c;
        strokeWidth = w / 5;
    }

    public EmptySquare3D(int x, int y, int z, int w, int h, int strokeWidth, int c) {
        super(x, y, z, w, h);
        color = c;
        this.strokeWidth = strokeWidth;
    }

    @Override
    void draw(Canvas c, Paint p, float dx, float dy, float k) {
        float wkd2 = w * k / 2;
        float hkd2 = h * k / 2;
        //c.drawOval(new RectF(x+dx-wkd2, y+dy-hkd2, x+dx+wkd2, y+dy+hkd2), paint);
        p.setColor(color);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.MITER);
        p.setStrokeWidth(strokeWidth * k); //wkd2*2f/5);
        wkd2 = wkd2 - strokeWidth * k/2;
        hkd2 = hkd2 - strokeWidth * k/2;
        c.drawRect(new RectF(dx - wkd2, dy - hkd2, dx + wkd2, dy + hkd2), p);
        p.setStyle(Paint.Style.FILL);
    }
}

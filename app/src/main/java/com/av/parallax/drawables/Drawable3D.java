package com.av.parallax.drawables;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Alan on 19 May 2017.
 */

public abstract class Drawable3D {
    public float x, y, z, w, h;
    public int color;

    public Drawable3D(float x, float y, float z, float w, float h, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public abstract void draw(Canvas c, Paint p, float dx, float dy, float k);
}


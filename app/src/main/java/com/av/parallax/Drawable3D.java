package com.av.parallax;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Alan on 19 May 2017.
 */

public abstract class Drawable3D {
    int x, y, z, w, h;

    public Drawable3D(int x, int y, int z, int w, int h) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
    }

    abstract void draw(Canvas c, Paint p, float dx, float dy, float k);
}


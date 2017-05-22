package com.av.parallax.drawables;

import android.graphics.Paint;

/**
 * Created by Alan on 19 May 2017.
 */

public abstract class Drawable3D implements IDrawable3D {
    public float x, y, z;

    public Drawable3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


package com.av.parallax.drawables;

import android.graphics.Canvas;

import com.av.parallax.primitives.IFlatPrimitiveDrawer;

/**
 * Created by Alan on 22 May 2017.
 */

public class FlatPrimitive3D extends Drawable3D {
    protected int color;
    protected IFlatPrimitiveDrawer fp;

    public FlatPrimitive3D(float x, float y, float z, int color, IFlatPrimitiveDrawer fp) {
        super(x, y, z);
        this.color = color;
        this.fp = fp;
    }

    @Override
    public void draw(Canvas c, float x, float y, float k) {
        c.save(Canvas.MATRIX_SAVE_FLAG);
        c.translate(x, y);
        c.scale(k, k);
        fp.draw(c, color);
        c.restore();
    }
}

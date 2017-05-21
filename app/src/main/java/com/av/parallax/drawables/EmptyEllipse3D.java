package com.av.parallax.drawables;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by Alan on 19 May 2017.
 */

public class EmptyEllipse3D extends Path3D {
    public EmptyEllipse3D(int x, int y, int z, int w, int h, int c) {
        super(x, y, z, w, h, c);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        path.addOval(new RectF(-wd2, -hd2, wd2, hd2), Path.Direction.CW);
    }
}

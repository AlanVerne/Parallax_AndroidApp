package com.av.parallax.primitives;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by Alan on 19 May 2017.
 */

public class Ellipse extends ShapeFPD {
    public Ellipse(int w, int h, int rotate) {
        super(w, h, rotate);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        path.addOval(new RectF(-wd2, -hd2, wd2, hd2), Path.Direction.CW);
    }
}

package com.av.parallax;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Alan on 19 May 2017.
 */

public class K3D extends Path3D {
    public K3D(int x, int y, int z, int w, int h, int c) {
        super(x, y, z, w, h, c);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        path.moveTo(-wd2, -hd2);
        path.lineTo(-wd2, hd2);
        path.lineTo(wd2, -hd2);
        path.moveTo(0, 0);
        path.lineTo(wd2, hd2);
    }
}

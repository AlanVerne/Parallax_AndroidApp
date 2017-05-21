package com.av.parallax.drawables;

/**
 * Created by Alan on 19 May 2017.
 */

public class N3D extends Path3D {
    public N3D(int x, int y, int z, int w, int h, int c) {
        super(x, y, z, w, h, c);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        path.moveTo(-wd2, hd2);
        path.lineTo(-wd2, -hd2);
        path.lineTo(wd2, hd2);
        path.lineTo(wd2, -hd2);
    }
}

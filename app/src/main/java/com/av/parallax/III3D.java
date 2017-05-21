package com.av.parallax;

/**
 * Created by Alan on 19 May 2017.
 */

public class III3D extends Path3D {
    public III3D(int x, int y, int z, int w, int h, int c) {
        super(x, y, z, w, h, c);
    }
    public III3D(int x, int y, int z, int w, int h, float rotate, int c) {
        super(x, y, z, w, h, rotate, c);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        path.moveTo(-strokeWidthK * w * 2.f, hd2);
        path.lineTo(-strokeWidthK * w * 2.f, -hd2);
        path.moveTo(0, hd2);
        path.lineTo(0, -hd2);
        path.moveTo(strokeWidthK * w * 2.f, hd2);
        path.lineTo(strokeWidthK * w * 2.f, -hd2);
    }
}

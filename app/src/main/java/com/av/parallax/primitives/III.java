package com.av.parallax.primitives;

/**
 * Created by Alan on 19 May 2017.
 */

public class III extends PathFPD {
    public III(int w, int h, int rotate) {
        super(w, h, rotate);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        float w = wd2*2/(1-strokeWidthK);
        path.moveTo(-strokeWidthK * w * 2.f, hd2);
        path.lineTo(-strokeWidthK * w * 2.f, -hd2);
        path.moveTo(0, hd2);
        path.lineTo(0, -hd2);
        path.moveTo(strokeWidthK * w * 2.f, hd2);
        path.lineTo(strokeWidthK * w * 2.f, -hd2);
    }
}

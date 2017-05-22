package com.av.parallax.primitives;

/**
 * Created by Alan on 19 May 2017.
 */

public class Triangle extends ShapeFPD {
    public Triangle(int w, int h, int rotate) {
        super(w, h, rotate);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        path.moveTo(-wd2, -hd2);
        path.lineTo(0, hd2);
        path.lineTo(wd2, -hd2);
        path.close();
    }
}

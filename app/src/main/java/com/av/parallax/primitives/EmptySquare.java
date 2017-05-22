package com.av.parallax.primitives;

/**
 * Created by Alan on 22 May 2017.
 */

public class EmptySquare extends PathFPD {
    public EmptySquare(int w, int h, int rotate) {
        super(w, h, rotate);
    }

    @Override
    protected void createPath(float wd2, float hd2) {
        path.moveTo(-wd2, -hd2);
        path.lineTo(-wd2, hd2);
        path.lineTo(wd2, hd2);
        path.lineTo(wd2, -hd2);
        path.close();
    }
}

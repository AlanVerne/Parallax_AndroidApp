package com.av.parallax.primitives;

/**
 * Created by Alan on 19 May 2017.
 */

public class ShapeFPD extends PathFPD {
    public ShapeFPD(int w, int h, int rotate) {
        super(w, h, rotate);
    }

    protected void initPaint(int w) {
        paint = Paints.getStrokeAndFillPaint(w*strokeWidthK);
    }
}

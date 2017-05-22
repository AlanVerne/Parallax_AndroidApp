package com.av.parallax.primitives;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.av.parallax.drawables.Drawable3D;

/**
 * Created by Alan on 19 May 2017.
 */

public class Square extends ShapeFPD {
    public Square(int w, int h, int rotate) {
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

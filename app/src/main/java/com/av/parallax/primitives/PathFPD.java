package com.av.parallax.primitives;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

import com.av.parallax.drawables.Drawable3D;
import com.av.parallax.drawables.IDrawable3D;

/**
 * Created by Alan on 19 May 2017.
 */

public class PathFPD implements IFlatPrimitiveDrawer {
    protected final float strokeWidthK = 1f / 5f;
    protected final Path path = new Path();
    protected Paint paint;

    public PathFPD(int w, int h, int rotate){
        float nw = w * (1f - strokeWidthK);
        float nh = h * (1f - strokeWidthK);

        initPaint(w);

        createPath(nw / 2f, nh / 2f);

        if (rotate != 0) {
            Matrix mMatrix = new Matrix();
            mMatrix.setRotate(rotate, 0, 0);
            path.transform(mMatrix);
        }
    }

    protected void initPaint(int w) {
        paint = Paints.getStrokePaint(w*strokeWidthK);
    }

    protected void createPath(float wd2, float hd2) {
    }

    public void draw(Canvas c, int color) {
        paint.setColor(color);
        c.drawPath(path, paint);
    }
}

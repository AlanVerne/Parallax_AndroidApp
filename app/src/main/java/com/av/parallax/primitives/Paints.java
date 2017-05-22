package com.av.parallax.primitives;

import android.graphics.Paint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alan on 22 May 2017.
 */

public class Paints {
    private static final Map<Float, Paint> strokePaints = new HashMap<>();
    public static Paint getStrokePaint(Float w) {
        Paint p = strokePaints.get(w);
        if (p != null) return p;

        p = newPaint(w);
        p.setStyle(Paint.Style.STROKE);
        strokePaints.put(w, p);
        return p;
    }

    private static final Map<Float, Paint> strokeAndFillPaints = new HashMap<>();
    public static Paint getStrokeAndFillPaint(Float w) {
        Paint p = strokeAndFillPaints.get(w);
        if (p != null) return p;

        p = newPaint(w);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        strokeAndFillPaints.put(w, p);
        return p;
    }

    private static Paint newPaint(Float w) {
        Paint p = new Paint();
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(w);
        return p;
    }
}

package com.av.parallax.scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.av.parallax.primitives.Ellipse;
import com.av.parallax.primitives.EmptyEllipse;
import com.av.parallax.primitives.EmptySquare;
import com.av.parallax.primitives.IFlatPrimitiveDrawer;
import com.av.parallax.primitives.III;
import com.av.parallax.primitives.K;
import com.av.parallax.primitives.N;
import com.av.parallax.primitives.Square;
import com.av.parallax.primitives.T;
import com.av.parallax.primitives.Triangle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Alan on 21 May 2017.
 */

public class Demo1 extends ParallaxedSceneView {
    private static final Map<IFlatPrimitiveDrawer, Integer> PRIMITIVES = new HashMap<>();
    private static final Map<Integer, IFlatPrimitiveDrawer> ORDERED_PRIMITIVES = new TreeMap<>();
    private int primitivesSum = 0;

    private boolean refillScene = false;

    public Demo1(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener((v, event) -> {
            refillScene = true;
            return true;
        });
    }

    protected void fillPrimitivesMap(int size) {
        PRIMITIVES.put(new III(size, size, 0), 4);
        PRIMITIVES.put(new III(size, size, 90), 4);
        PRIMITIVES.put(new Square(size, size, 0), 8);
        PRIMITIVES.put(new EmptySquare(size, size, 0), 8);
        PRIMITIVES.put(new Ellipse(size, size, 0), 8);
        PRIMITIVES.put(new EmptyEllipse(size, size, 0), 8);
        PRIMITIVES.put(new Triangle(size, size, 0), 2);
        PRIMITIVES.put(new Triangle(size, size, 90), 2);
        PRIMITIVES.put(new Triangle(size, size, 180), 2);
        PRIMITIVES.put(new Triangle(size, size, 270), 2);

        for (Map.Entry<IFlatPrimitiveDrawer, Integer> entry : PRIMITIVES.entrySet()) {
            primitivesSum += entry.getValue();
            ORDERED_PRIMITIVES.put(primitivesSum, entry.getKey());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (refillScene) {
            items.clear();
            fillScene();
            sort();
            invalidate();
            refillScene = false;
        }
        super.draw(canvas);
    }

    @Override
    public void fillScene() {
        int grid = 80;
        int r = 80/3;

        if (primitivesSum == 0) fillPrimitivesMap(r);

        Random rand = new Random();

        int w = 4;
        int h = 9;

//        add(new EmptySquare3D(0, 0, 0, grid*6, grid*2, r/5, 0xffffffff));

//        add(new EmptySquare3D(0, 0, -r, grid*13, grid*23, r/5, 0xff55a6ff));

//        add(new EmptySquare3D(0, 0, -0*r, grid*11, grid*21, r/5, 0xffffffff));
//        add(new EmptySquare3D(0, 0, -1*r, grid*11, grid*21, r/5, 0xff55a6ff));
//        add(new EmptySquare3D(0, 0, -2*r, grid*11, grid*21, r/5, 0xff1356cc));

        for (int x = -w; x <= w; x++) {
            for (int y = -h; y <= h; y++) {
                if (y == 0 && x >= -2 && x <= 2) {
                    if (x == -2) addFP(x * grid, y * grid, 0, 0xffffffff, new N(r, r, 0));
                    if (x == -1) addFP(x * grid, y * grid, 0, 0xffffffff, new EmptySquare(r, r, 0));
                    if (x == 0) addFP(x * grid, y * grid, 0, 0xffffffff, new K(r, r, 0));
                    if (x == 1) addFP(x * grid, y * grid, 0, 0xffffffff, new III(r, r, 0));
                    if (x == 2) addFP(x * grid, y * grid, 0, 0xffffffff, new T(r, r, 0));
                    continue;
                }

                int primitiveI = rand.nextInt(primitivesSum + primitivesSum/2) + 1;
                if (primitiveI > primitivesSum) continue;

                IFlatPrimitiveDrawer primitive;
                while ((primitive = ORDERED_PRIMITIVES.get(primitiveI++)) == null);

                int z = -(rand.nextInt(4)) * r/2;
                int dz = -(rand.nextInt(4)) * r/2;

                addFP(x * grid, y * grid, z+dz*3, 0xff072677, primitive);
                addFP(x * grid, y * grid, z+dz*2, 0xff1356cc, primitive);
                addFP(x * grid, y * grid, z+dz, 0xff55a6ff, primitive);
                if (rand.nextInt(10) == 0) addFP(x * grid, y * grid, z, 0xffffffff, primitive);
            }
        }
    }
}

package com.av.parallax.scenes;

import android.content.Context;
import android.util.AttributeSet;

import com.av.parallax.drawables.Ellipse3D;
import com.av.parallax.drawables.EmptyEllipse3D;
import com.av.parallax.drawables.EmptySquare3D;
import com.av.parallax.drawables.III3D;
import com.av.parallax.drawables.K3D;
import com.av.parallax.drawables.N3D;
import com.av.parallax.drawables.Square3D;
import com.av.parallax.drawables.T3D;
import com.av.parallax.drawables.Triangle3D;

import java.util.Random;

/**
 * Created by Alan on 21 May 2017.
 */

public class Demo1 extends ParallaxedSceneView {
    public Demo1(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener((v, event) -> {
            items.clear();
            fillScene();
            sort();
            invalidate();
            return true;
        });
    }

    @Override
    public void fillScene() {
        Random rand = new Random();

        int grid = 80;
        int r = 80 / 3;

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
                    if (x == -2) add(new N3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == -1) add(new EmptySquare3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == 0) add(new K3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == 1) add(new III3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == 2) add(new T3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    continue;
                }

//                if (Math.abs(y) <= 1 && Math.abs(x) <= 3) {
//                    continue;
//                }

                int z = -(rand.nextInt(4)) * r/2;
                int dz = -(rand.nextInt(4)) * r/2;

                if (rand.nextInt(4) == 0) {
                    add(new Ellipse3D(x * grid, y * grid, z+dz*3, r, r, 0xff072677));
                    add(new Ellipse3D(x * grid, y * grid, z+dz*2, r, r, 0xff1356cc));
                    add(new Ellipse3D(x * grid, y * grid, z+dz, r, r, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) add(new Ellipse3D(x * grid, y * grid, z, r, r, 0xffffffff));
                } else if (rand.nextInt(4) == 0) {
                    add(new Square3D(x * grid, y * grid, z+dz*3, r, r, 0xff072677));
                    add(new Square3D(x * grid, y * grid, z+dz*2, r, r, 0xff1356cc));
                    add(new Square3D(x * grid, y * grid, z+dz, r, r, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) add(new Square3D(x * grid, y * grid, z, r, r, 0xffffffff));
                } else if (rand.nextInt(4) == 0) {
                    add(new EmptyEllipse3D(x * grid, y * grid, z+dz*3, r, r, 0xff072677));
                    add(new EmptyEllipse3D(x * grid, y * grid, z+dz*2, r, r, 0xff1356cc));
                    add(new EmptyEllipse3D(x * grid, y * grid, z+dz, r, r, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) add(new EmptyEllipse3D(x * grid, y * grid, z, r, r, 0xffffffff));
                } else if (rand.nextInt(4) == 0) {
                    add(new EmptySquare3D(x * grid, y * grid, z+dz*3, r, r, 0xff072677));
                    add(new EmptySquare3D(x * grid, y * grid, z+dz*2, r, r, 0xff1356cc));
                    add(new EmptySquare3D(x * grid, y * grid, z+dz, r, r, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) add(new EmptySquare3D(x * grid, y * grid, z, r, r, 0xffffffff));
                } else if (rand.nextInt(8) == 0) {
                    float rotate = rand.nextInt(4) * 90;
                    add(new Triangle3D(x * grid, y * grid, z+dz*3, r, r, rotate, 0xff072677));
                    add(new Triangle3D(x * grid, y * grid, z+dz*2, r, r, rotate, 0xff1356cc));
                    add(new Triangle3D(x * grid, y * grid, z+dz, r, r, rotate, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) add(new Triangle3D(x * grid, y * grid, z, r, r, rotate, 0xffffffff));
                } else if (rand.nextInt(8) == 0) {
                    float rotate = rand.nextBoolean() ? 90 : 0;
                    add(new III3D(x * grid, y * grid, z+dz*3, r, r, rotate, 0xff072677));
                    add(new III3D(x * grid, y * grid, z+dz*2, r, r, rotate, 0xff1356cc));
                    add(new III3D(x * grid, y * grid, z+dz, r, r, rotate, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) add(new III3D(x * grid, y * grid, z, r, r, rotate, 0xffffffff));
                } else if (rand.nextInt(12) == 0) {
                    add(new N3D(x * grid, y * grid, z+dz*3, r, r, 0xff072677));
                    add(new N3D(x * grid, y * grid, z+dz*2, r, r, 0xff1356cc));
                    add(new N3D(x * grid, y * grid, z+dz, r, r, 0xff55a6ff));
                } else if (rand.nextInt(12) == 0) {
                    add(new K3D(x * grid, y * grid, z+dz*3, r, r, 0xff072677));
                    add(new K3D(x * grid, y * grid, z+dz*2, r, r, 0xff1356cc));
                    add(new K3D(x * grid, y * grid, z+dz, r, r, 0xff55a6ff));
                }
            }
        }
    }
}

package com.av.parallax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ParallaxedSceneView parallaxedSceneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        parallaxedSceneView = (ParallaxedSceneView) findViewById(R.id.psv);

        fillScene();
    }

    @Override
    protected void onResume() {
        super.onResume();
        parallaxedSceneView.resume();
    }

    @Override
    public void onStop() {
        super.onStop();
        parallaxedSceneView.stop();
    }

    private void fillScene() {
        Random rand = new Random();

        int grid = 80;
        int r = 80 / 3;

        int w = 4;
        int h = 9;

//        parallaxedSceneView.add(new EmptySquare3D(0, 0, 0, grid*6, grid*2, r/5, 0xffffffff));

//        parallaxedSceneView.add(new EmptySquare3D(0, 0, -r, grid*13, grid*23, r/5, 0xff55a6ff));

//        parallaxedSceneView.add(new EmptySquare3D(0, 0, -0*r, grid*11, grid*21, r/5, 0xffffffff));
//        parallaxedSceneView.add(new EmptySquare3D(0, 0, -1*r, grid*11, grid*21, r/5, 0xff55a6ff));
//        parallaxedSceneView.add(new EmptySquare3D(0, 0, -2*r, grid*11, grid*21, r/5, 0xff1356cc));

        for (int x = -w; x <= w; x++) {
            for (int y = -h; y <= h; y++) {
                if (y == 0 && x >= -2 && x <= 2) {
                    if (x == -2) parallaxedSceneView.add(new N3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == -1) parallaxedSceneView.add(new EmptySquare3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == 0) parallaxedSceneView.add(new K3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == 1) parallaxedSceneView.add(new III3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    if (x == 2) parallaxedSceneView.add(new T3D(x * grid, y * grid, 0, r, r, 0xffffffff));
                    continue;
                }
//                if (Math.abs(y) <= 1 && Math.abs(x) <= 3) {
//                    continue;
//                }

                int z = -rand.nextInt(3) * grid;

                if (rand.nextInt(3) == 0) {
                    parallaxedSceneView.add(new Circle3D(x * grid, y * grid, z, r, r, 0xff072677));
                    parallaxedSceneView.add(new Circle3D(x * grid, y * grid, z * 3 / 4, r, r, 0xff1356cc));
                    parallaxedSceneView.add(new Circle3D(x * grid, y * grid, z * 2 / 4, r, r, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) parallaxedSceneView.add(new Circle3D(x * grid, y * grid, z / 4, r, r, 0xffffffff));
                } else if (rand.nextInt(3) == 0) {
                    parallaxedSceneView.add(new Square3D(x * grid, y * grid, z, r, r, 0xff072677));
                    parallaxedSceneView.add(new Square3D(x * grid, y * grid, z * 3 / 4, r, r, 0xff1356cc));
                    parallaxedSceneView.add(new Square3D(x * grid, y * grid, z / 2, r, r, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) parallaxedSceneView.add(new Square3D(x * grid, y * grid, z / 4, r, r, 0xffffffff));
                } else if (rand.nextInt(3) == 0) {
                    parallaxedSceneView.add(new EmptySquare3D(x * grid, y * grid, z, r, r, 0xff072677));
                    parallaxedSceneView.add(new EmptySquare3D(x * grid, y * grid, z * 3 / 4, r, r, 0xff1356cc));
                    parallaxedSceneView.add(new EmptySquare3D(x * grid, y * grid, z / 2, r, r, 0xff55a6ff));
                    if (rand.nextInt(30) == 0) parallaxedSceneView.add(new EmptySquare3D(x * grid, y * grid, z / 4, r, r, 0xffffffff));
                } else if (rand.nextInt(6) == 0) {
                    float rotate = rand.nextInt(4) * 90;
                    parallaxedSceneView.add(new Triangle3D(x * grid, y * grid, z, r, r, rotate, 0xff072677));
                    parallaxedSceneView.add(new Triangle3D(x * grid, y * grid, z * 3 / 4, r, r, rotate, 0xff1356cc));
                    parallaxedSceneView.add(new Triangle3D(x * grid, y * grid, z / 2, r, r, rotate, 0xff55a6ff));
                    if (rand.nextInt(30) == 0)  parallaxedSceneView.add(new Triangle3D(x * grid, y * grid, z/4, r, r, 0xffffffff));
                } else if (rand.nextInt(6) == 0) {
                    float rotate = rand.nextBoolean() ? 90 : 0;
                    parallaxedSceneView.add(new III3D(x * grid, y * grid, z, r, r, rotate, 0xff072677));
                    parallaxedSceneView.add(new III3D(x * grid, y * grid, z * 3 / 4, r, r, rotate, 0xff1356cc));
                    parallaxedSceneView.add(new III3D(x * grid, y * grid, z / 2, r, r, rotate, 0xff55a6ff));
                    if (rand.nextInt(30) == 0)  parallaxedSceneView.add(new K3D(x * grid, y * grid, z/4, r, r, 0xffffffff));
                } else if (rand.nextInt(12) == 0) {
                    parallaxedSceneView.add(new N3D(x * grid, y * grid, z, r, r, 0xff072677));
                    parallaxedSceneView.add(new N3D(x * grid, y * grid, z * 3 / 4, r, r, 0xff1356cc));
                    parallaxedSceneView.add(new N3D(x * grid, y * grid, z / 2, r, r, 0xff55a6ff));
                } else if (rand.nextInt(12) == 0) {
                    parallaxedSceneView.add(new K3D(x * grid, y * grid, z, r, r, 0xff072677));
                    parallaxedSceneView.add(new K3D(x * grid, y * grid, z * 3 / 4, r, r, 0xff1356cc));
                    parallaxedSceneView.add(new K3D(x * grid, y * grid, z / 2, r, r, 0xff55a6ff));
                }
            }
        }

        parallaxedSceneView.sort();
    }
}

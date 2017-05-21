package com.av.parallax;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.av.parallax.drawables.Drawable3D;
import com.av.parallax.scenes.ParallaxedSceneView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ParallaxedSceneView parallaxedSceneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        parallaxedSceneView = (ParallaxedSceneView) findViewById(R.id.psv);

        startTimer();
    }

    // --

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

    // --

    private final Timer timer = new Timer();
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int elapsedTime = msg.what;
            List<Drawable3D> l = parallaxedSceneView.get(3 * 80, 8 * 80);
            for (Drawable3D d : l) {
                if (elapsedTime == 0 || elapsedTime == 8*10 || elapsedTime == 11*10) {
                    d.z = 40;
                }
                else {
                    d.z = d.z * 0.9f;
                }
                if (d.z > 20) d.color = 0xffff0088;
                else d.color = 0xff55a6ff;
            }
            l = parallaxedSceneView.get(2 * 80, 8 * 80);
            for (Drawable3D d : l) {
                if (elapsedTime == 4*10 || elapsedTime == 12*10) d.z = 40;
                else d.z = d.z * 0.9f;
                if (d.z > 20) d.color = 0xffff0088;
                else d.color = 0xff55a6ff;
            }
            l = parallaxedSceneView.get(1 * 80, 8 * 80);
            for (Drawable3D d : l) {
                if (elapsedTime == 2*10 || elapsedTime == 6*10 || elapsedTime == 10*10 || elapsedTime == 14*10) d.z = 40;
                else d.z = d.z * 0.9f;
                if (d.z > 20) d.color = 0xff00ffff;
                else d.color = 0xff55a6ff;
            }
        }
    };
    private int elapsedTime = 0;

    protected void startTimer() {
//        isTimerRunning = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                elapsedTime += 1; //increase every sec
                if (elapsedTime >= 16*10) elapsedTime = 0;
                mHandler.obtainMessage(elapsedTime).sendToTarget();
            }
        }, 0, 1000 / 100);
    }
}

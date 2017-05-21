package com.av.parallax.scenes;

import android.content.Context;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alan on 1 Feb 2017.
 */

public class ParallaxHelper {
    private static final String TAG = "ParallaxHelper";

    private static final double NS2S = 1.0f / 1000000000.0f;

    private final View v;

    private final SensorManager sensorManager;
    private final Sensor sensor;

    private double phoneDistance = 3000;
    private double angleX = 0, angleY = 0;
    private double userX = 0, userY = 0, userZ = 0;
    private double w, h;

    //    private final double[] deltaRotationVector = new double[4];
    private double timestamp = 0;

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (setDeviceAngles(event)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) v.postInvalidateOnAnimation();
                else v.postInvalidate();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };


//    private static final double NS2S = 1.0f / 1000000000.0f;
//    private final SensorEventListener sensorEventListener = new SensorEventListener() {
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//            if (timestamp != 0) {
//                final double dT = (event.timestamp - timestamp) * NS2S;
//                double dAX = event.values[0];
//                double dAY = event.values[1];
//                angleX += dAX * dT;
//                angleY += dAY * dT;
//            }
//            timestamp = event.timestamp;
//        }
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int i) { }
//    };


    public ParallaxHelper(View v) {
        this.v = v;
        this.w = v.getWidth();
        this.h = v.getHeight();

        angleX = 0;
        angleY = 0;

        userX = w / 2;
        userY = h / 2;

        sensorManager = (SensorManager) v.getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        v.addOnLayoutChangeListener((v1, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            this.w = right - left;
            this.h = bottom - top;
        });
    }


    public void resume() {
        timestamp = 0;
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stop() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    int c100 = 0;

    private class VectorAnimation {
        long animationStart = -1;
        double dax, day;

        public VectorAnimation(long animationStart, double dax, double day) {
            this.animationStart = animationStart;
            this.dax = dax;
            this.day = day;
        }
    }

    private List<VectorAnimation> animations = new ArrayList<>();
    private double[][] as = new double[10][2];
    private int aCountdown = 10;

    public boolean setDeviceAngles(SensorEvent event) {
        // This time step's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        if (timestamp != 0) {
            final double dT = (event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            double axisX = event.values[0];
            double axisY = event.values[1];
//            double axisZ = event.values[2];

            // Calculate the angular speed of the sample
//            double omegaMagnitude = Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

            // Normalize the rotation vector if it's big enough to get the axis
//            if (omegaMagnitude > 80) {
//                axisX /= omegaMagnitude;
//                axisY /= omegaMagnitude;
////                axisZ /= omegaMagnitude;
//            }

            // Integrate around this axis with the angular speed by the time step
            // in order to get a delta rotation from this sample over the time step
            // We will convert this axis-angle representation of the delta rotation
            // into a quaternion before turning it into the rotation matrix.
//            double thetaOverTwo = omegaMagnitude * dT / 2.0f;
//            double sinThetaOverTwo = Math.sin(thetaOverTwo);
//            double cosThetaOverTwo = Math.cos(thetaOverTwo);
//            deltaRotationVector[0] = sinThetaOverTwo * axisX;
//            deltaRotationVector[1] = sinThetaOverTwo * axisY;
//            deltaRotationVector[2] = sinThetaOverTwo * axisZ;
//            deltaRotationVector[3] = cosThetaOverTwo;

            //Log.i(TAG, "Deltas: " + axisX + " " + axisY + ", " + deltaRotationVector[0] + " " + deltaRotationVector[1]);

            double maxDA = Math.PI / 8.0;

            angleX += Math.max(-maxDA, Math.min(maxDA, axisX * dT));
            angleY += Math.max(-maxDA, Math.min(maxDA, axisY * dT));

            double angleRange = Math.PI / 4.0;
            angleX = Math.max(-angleRange, Math.min(angleRange, angleX));
            angleY = Math.max(-angleRange, Math.min(angleRange, angleY));

            aCountdown--;
            long currentTimeMillis = System.currentTimeMillis();
            if (aCountdown < 0) {
                aCountdown = 30;
                for (int i = 0; i < as.length - 1; i++) {
                    as[i][0] = as[i + 1][0];
                    as[i][1] = as[i + 1][1];
                }
                as[9][0] = angleX;
                as[9][1] = angleY;

                boolean goBack = true;

                for (int i = 1; i < as.length; i++) {
                    if (Math.abs(as[0][0] - as[i][0]) > 0.05 || Math.abs(as[0][1] - as[i][1]) > 0.05) {
                        goBack = false;
                        break;
                    }
                }

                if (goBack) {
                    int ndax = 0, nday = 0;
//                    for (VectorAnimation animation : animations) {
//                        ndax += animation.dax;
//                        nday += animation.day;
//                    }
                    if (animations.isEmpty()) {
                        animations.add(new VectorAnimation(currentTimeMillis, -ndax - angleX, -nday - angleY));
                    }
                }

//                if (axisX*dT < 0.0005 && axisY*dT < 0.0005) {
//                    double d = dT;
//                    if (goBackV >= d) goBackV -= d;
//                    else goBackV = 0;
//
//                    double k = Math.min(1, goBackV);
//                    k *= k;
//                    angleX *= 0.99 + k * 0.01;
//                    angleY *= 0.99 + k * 0.01;
//                }
            }

            double ax = angleX;
            double ay = angleY;

            Iterator<VectorAnimation> iterator = animations.iterator();
            while (iterator.hasNext()) {
                VectorAnimation animation = iterator.next();
                double l = (double)(currentTimeMillis - animation.animationStart) / 1000;
                if (l > 1) {
                    angleX += animation.dax;
                    angleY += animation.day;
                    ax += animation.dax;
                    ay += animation.day;
                    iterator.remove();
                } else {
                    l = (1 - Math.cos(l * Math.PI)) / 2;
                    ax += animation.dax * l;
                    ay += animation.day * l;
                }
            }

//            Log.i(TAG, "Angles: " + angleX*180/Math.PI + ", " + angleY*180/Math.PI + " dT:" + dT);

            userX = Math.sin(-ay) * phoneDistance;// + w/2.0;
            userY = Math.sin(-ax) * phoneDistance;// + h/2.0;
            userZ = Math.cos(-(Math.sqrt(ax * ax + ay * ay))) * phoneDistance;

            timestamp = event.timestamp;
//            double[] deltaRotationMatrix = new double[9];
//            //SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);

            return true;
        }
        timestamp = event.timestamp;
//        double[] deltaRotationMatrix = new double[9];
//        //SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);

        return false;
    }


    public double getK(double z) {
        return userZ / (userZ - z);
    }

    public PointF applyParallax(double x, double y, double z) {
        if (userZ == 0) return new PointF((float) x, (float) y);
        double k = userZ / (userZ - z);
        return new PointF((float) (userX + (x - userX) * k), (float) (userY + (y - userY) * k));
    }
}

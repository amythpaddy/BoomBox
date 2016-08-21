package com.caragiz_studioz.boombox;

/**
 * Created by caragiz on 08-08-2016.
 */
public interface AccelerometerListener {

    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);

}

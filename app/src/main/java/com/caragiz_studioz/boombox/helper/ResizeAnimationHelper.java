package com.caragiz_studioz.boombox.helper;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by caragiz on 10/9/16.
 */
public class ResizeAnimationHelper extends Animation {

    final int targetHeight;
    View view;
    int startHeight;

    public ResizeAnimationHelper(View view, int targetHeight, int startHeight) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.startHeight = startHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight = (int) (targetHeight * interpolatedTime);
        view.getLayoutParams().height = newHeight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}

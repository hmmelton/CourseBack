package com.hmmelton.textrack.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by harrison on 8/28/15.
 * This class is a custom animation that fades in and slides down the title View on the
 * SignInActivity.
 */
public class SignInTitleAnimation extends Animation {

    private View mView; // view to be faded in
    private int mPadding; // final bottom padding of view

    /**
     * Constructor
     * @param v view to be faded in
     */
    public SignInTitleAnimation(View v) {
        mView = v;
        // current and final bottom padding
        mPadding = v.getPaddingBottom();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        mView.setAlpha(interpolatedTime); // alpha changes from 0.0 to 1.0 as time progresses

        // extra padding is shrunk until it is equal to 0
        int extraPadding = (int) ((1 - interpolatedTime) * 40);
        mView.setPadding(0, 0, 0, mPadding + extraPadding);
    }
}

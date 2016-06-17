package com.earcs.grabm.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;

import com.earcs.grabm.R;

/**
 * @author Sachith Dickwella
 */
public abstract class Utils {

    public static RoundedBitmapDrawable createCircleBitmap(Resources resources, Bitmap bitmap) {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap);
        roundedBitmapDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
        return roundedBitmapDrawable;
    }
}

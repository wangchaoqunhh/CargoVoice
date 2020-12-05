package com.lwb.cargovoice.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class FabBehavior extends FloatingActionButton.Behavior {
//    private boolean visible = true;//是否可见

    public FabBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target,
                                       int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll
                (coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int
                                       dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 0) {
            //show
//            visible = false;
            onHide(child);
        } else if (dyConsumed < 0) {
            //hide
//            visible = true;
            onShow(child);
        }
    }

    public static void onHide(FloatingActionButton fab) {
        if (fab != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab
                    .getLayoutParams();
            fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator
                    (new AccelerateInterpolator(3));
        }
    }

    public static void onShow(FloatingActionButton fab) {
        if (fab != null) {
            fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
        }
    }
}


package com.ysnows.doublescrollview;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

import java.lang.ref.WeakReference;

/**
 * Created by xianguangjin on 2016/12/30.
 */

public class PageTwoBehavior extends CoordinatorLayout.Behavior {
    private boolean isExpanded = false;
    private boolean isScrolling = false;
    private WeakReference<View> dependentView;
    private Scroller scroller;
    private Handler handler;

    public PageTwoBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        handler = new Handler();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//        Log.d("PageTwoBehavior", "layoutDependsOn");
        if (dependency.getId() == R.id.pageOne) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        child.layout(0, 0, parent.getWidth(), child.getMeasuredHeight());
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d("PageTwoBehavior", "onDependentViewChanged");
        child.setTranslationY(dependency.getMeasuredHeight() + dependency.getTranslationY());
        return true;
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.d("PageTwoBehavior", "dy:" + dy);


    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("PageTwoBehavior", "dyConsumed:" + dyConsumed);
        Log.d("PageTwoBehavior", "dyUnconsumed:" + dyUnconsumed);

        if (dyUnconsumed > 0) {
            View dependentView = getDependentView();
            float newTranslate = dependentView.getTranslationY() - dyUnconsumed;
            dependentView.setTranslationY(newTranslate);


        } else {

        }

    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        View dependentView = getDependentView();
        float translationY = dependentView.getTranslationY();
        Log.d("PageTwoBehavior", "translationY:" + translationY);
        if (translationY < -180) {
            dependentView.setTranslationY(-dependentView.getMeasuredHeight());
        }

    }

    private View getDependentView() {
        return dependentView.get();
    }


}

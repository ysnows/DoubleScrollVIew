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

public class PageBehavior extends CoordinatorLayout.Behavior {
    private static final int PAGE_ONE = 1;
    private static final int PAGE_TWO = 2;
    private WeakReference<View> dependentView;
    private Scroller scroller;
    private Handler handler;
    private int status = PAGE_ONE;//记录状态
    private float gap;

    public PageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        handler = new Handler();
        gap = context.getResources().getDimensionPixelSize(R.dimen.gap);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //设定依赖视图为id是R.id.pageOne的View
        if (dependency.getId() == R.id.pageOne) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        //child的布局
        child.layout(0, 0, parent.getWidth(), child.getMeasuredHeight());
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //让child随着依赖视图的属性变动而变动
        child.setTranslationY(dependency.getMeasuredHeight() + dependency.getTranslationY());
        return true;
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //只监测Vertical方向的动作
        scroller.abortAnimation();//停止动画
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("PageBehavior", "dyConsumed:" + dyConsumed);
        Log.d("PageBehavior", "dyUnconsumed:" + dyUnconsumed);

        if (status == PAGE_ONE) {
            if (dyUnconsumed > 0) {
                View dependentView = getDependentView();
                float newTranslate = dependentView.getTranslationY() - dyUnconsumed;
                dependentView.setTranslationY(newTranslate);
            }
        } else if (status == PAGE_TWO) {
            if (dyUnconsumed < 0) {
                View dependentView = getDependentView();
                float newTranslate = dependentView.getTranslationY() - dyUnconsumed;
                dependentView.setTranslationY(newTranslate);
            }
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        View dependentView = getDependentView();
        float translationY = dependentView.getTranslationY();
        Log.d("PageBehavior", "translationY:" + translationY);
        if (status == PAGE_ONE) {
            if (translationY < -gap) {
                scroller.startScroll(0, (int) translationY, 0, (int) (-dependentView.getMeasuredHeight() - translationY));
                status = PAGE_TWO;
            } else {
                scroller.startScroll(0, (int) translationY, 0, (int) (-translationY));
            }
        } else if (status == PAGE_TWO) {
            if (translationY < -dependentView.getMeasuredHeight() + gap) {
                scroller.startScroll(0, (int) translationY, 0, (int) (-dependentView.getMeasuredHeight() - translationY));

            } else {
                scroller.startScroll(0, (int) translationY, 0, (int) (-translationY));
                status = PAGE_ONE;
            }
        }
        handler.post(new Running());
    }

    private View getDependentView() {
        return dependentView.get();
    }

    private class Running implements Runnable {
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                View dependentView = getDependentView();
                dependentView.setTranslationY(scroller.getCurrY());
                handler.post(this);
            }
        }
    }


}

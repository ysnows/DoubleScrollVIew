package ysnows.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by ysnow on 2015/4/20.
 */
public class PageTwoWebView extends WebView {
    public float oldY;
    private int t;
    private float oldX;

    public PageTwoWebView(Context context) {
        super(context);
    }

    public PageTwoWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageTwoWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float Y = ev.getY();
                float Ys = Y - oldY;
                float X = ev.getX();
                float gapHorizontal = X - oldX;

                /** 说明:
                 *如果是横向移动,就让父控件重新获得触摸事件
                 */
                if (Math.abs(gapHorizontal) > 120) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }


                if (Ys > 0 && t == 0) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                oldY = ev.getY();
                oldX = ev.getX();

                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);

                break;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        this.t = t;
        super.onScrollChanged(l, t, oldl, oldt);
    }


}

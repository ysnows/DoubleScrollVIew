package ysnow.ysnowsslidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by ysnow on 2015/4/20.
 */
public class YsnowScrollView extends ScrollView {
    public float oldY;
    private int t;

    public YsnowScrollView(Context context) {
        super(context);
    }

    public YsnowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YsnowScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float Y = ev.getY();
                float Ys = Y - oldY;
                //Ys>0表示正在向下滑动->t==0表示一定滑动到了顶部
                if (Ys >0 &&t==0) {
                    //然后让顶级那个scrolLview滑动滑动事件
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                //同样是获得滑动事件->记录位置
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                oldY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);

                break;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
this.t=t;
        int duoshao=t-oldt;
//        Log.e("TAG", duoshao + "DUOSHAO");
//        Log.e("TAG", t + "T");
//        Log.e("TAG", oldt + "OLDT");
        if (t-oldt==0) {
        }


        super.onScrollChanged(l, t, oldl, oldt);
    }


}

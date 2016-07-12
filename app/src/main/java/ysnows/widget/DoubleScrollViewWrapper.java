package ysnows.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import ysnow.ysnowsslidingmenu.R;
import ysnows.widget.utils.UiUtils;

/**
 * Created by ysnow on 2015/3/3.
 */
public class DoubleScrollViewWrapper extends ScrollView {
    private int heitht;


    private PageOne pageOne;
    private View pageTwo;
    private boolean isSetted = false;
    private boolean ispageOne = true;

    public DoubleScrollViewWrapper(Context context) {
        super(context);
        init(context, null, 0);

    }

    public DoubleScrollViewWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public DoubleScrollViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DoubleScrollViewWrapper);
        int padding = a.getInteger(R.styleable.DoubleScrollViewWrapper_doublescrollviewwrapper_padding, 0);
        heitht = UiUtils.getScreenHeight(context) - UiUtils.getStatusBarHeight(context) - UiUtils.dp2px(context, padding);
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isSetted) {
            /** 说明:
             *得到里面LinearLayout的控件
             */
            LinearLayout wrapper = (LinearLayout) getChildAt(0);

            pageOne = (PageOne) wrapper.getChildAt(0);
            pageTwo = wrapper.getChildAt(1);

            if (pageTwo instanceof PageTwoWebView) {
                WebSettings settings = ((PageTwoWebView) pageTwo).getSettings();
                settings.setJavaScriptEnabled(true);
                ((PageTwoWebView) pageTwo).setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        ((PageTwoWebView) pageTwo).loadUrl(url);
                        return true;
                    }
                });
            }

            /** 说明:
             *设置两个子View的高度设为当前View的高度
             */
            pageOne.getLayoutParams().height = heitht;
            pageTwo.getLayoutParams().height = heitht;
            isSetted = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            /** 说明:
             *手指抬起的时候判断是否翻页
             */
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                int creteria = heitht / 5;//滑动多少距离,然后才翻页
                if (ispageOne) {
                    if (scrollY <= creteria) {
                        //返回PageOne
                        this.smoothScrollTo(0, 0);
                    } else {
                        //显示PageTwo
                        this.smoothScrollTo(0, heitht);

                        if (pageTwo instanceof PageTwoWebView) {
                            ((PageTwoWebView) pageTwo).loadUrl("https://github.com/ysnows");
                        } else if (pageTwo instanceof PageTwoScrollView) {

                        }
                        this.setFocusable(false);
                        ispageOne = false;
                    }
                } else {
                    int scrollpadding = heitht - scrollY;
                    if (scrollpadding >= creteria) {
                        this.smoothScrollTo(0, 0);
                        ispageOne = true;
                    } else {
                        this.smoothScrollTo(0, heitht);
                        if (pageTwo instanceof PageTwoWebView) {
                            ((PageTwoWebView) pageTwo).loadUrl("https://github.com/ysnows");
                        }
                    }
                }

                return true;
        }
        return super.onTouchEvent(ev);
    }


    public void closeMenu() {
        if (ispageOne) return;
        this.smoothScrollTo(0, 0);
        ispageOne = true;
    }

    public void openMenu() {
        if (!ispageOne) return;
        this.smoothScrollTo(0, heitht);
        ispageOne = false;
    }


}

package ysnow.ysnowsslidingmenu.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import ysnow.ysnowsslidingmenu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetailFragment extends Fragment {


    private WebView webview;

    public GoodsDetailFragment() {
        // Required empty public constructor
        //
    }

    private static GoodsDetailFragment fragment = null;

    public static GoodsDetailFragment newInstance() {
        if (fragment == null) {
            fragment = new GoodsDetailFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);
//        return inflater.inflate(R.layout.fragment_goods_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview = (WebView) view.findViewById(R.id.webview);
        webview.loadUrl("https://github.com/ysnows");

    }
}

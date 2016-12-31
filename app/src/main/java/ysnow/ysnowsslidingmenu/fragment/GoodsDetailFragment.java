package ysnow.ysnowsslidingmenu.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.youth.banner.Banner;

import java.util.ArrayList;

import ysnow.ysnowsslidingmenu.R;
import ysnow.ysnowsslidingmenu.utils.GlideImageLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetailFragment extends Fragment {


    private WebView webview;
    private Banner banner;
    private ArrayList<String> list = new ArrayList<>();

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
//        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);

        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b3.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b1.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b2.jpg");
        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = (Banner) view.findViewById(R.id.banner);

        banner.setImages(list).setImageLoader(new GlideImageLoader()).start();
        webview = (WebView) view.findViewById(R.id.webview);
        webview.loadUrl("https://github.com/ysnows");
//
    }
}

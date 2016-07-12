package ysnow.ysnowsslidingmenu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ysnow.ysnowsslidingmenu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetailFragment extends Fragment {


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
        View view = inflater.inflate(R.layout.fragment_goods_detail, container, false);

        return view;
    }

}

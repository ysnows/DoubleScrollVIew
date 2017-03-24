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
public class ContentDetailFragment extends Fragment {


    public ContentDetailFragment() {
        // Required empty public constructor
    }


    private static ContentDetailFragment fragment = null;

    public static ContentDetailFragment newInstance() {
        if (fragment == null) {
            fragment = new ContentDetailFragment();
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_detail, container, false);
    }

}

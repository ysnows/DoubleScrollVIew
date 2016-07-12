package ysnow.ysnowsslidingmenu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import ysnow.ysnowsslidingmenu.fragment.CommentFragment;
import ysnow.ysnowsslidingmenu.fragment.ContentDetailFragment;
import ysnow.ysnowsslidingmenu.fragment.GoodsDetailFragment;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        MinePagerAdapter minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(minePagerAdapter);
        tabs.setupWithViewPager(viewpager);

    }


    /**
     * ViewPager的PagerAdapter
     */
    public class MinePagerAdapter extends FragmentPagerAdapter {
        Fragment[] fragments = new Fragment[]{GoodsDetailFragment.newInstance(), ContentDetailFragment.newInstance(), CommentFragment.newInstance()};
        String[] titles = new String[]{"商品", "详情", "评价"};

        public MinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}


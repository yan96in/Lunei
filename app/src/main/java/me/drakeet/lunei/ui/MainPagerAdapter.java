package me.drakeet.lunei.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import me.drakeet.lunei.AppConfig;
import me.drakeet.lunei.ui.hot.HotFragment;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/17 18:59
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    String[] titles = { "HOT", "NEW", "NEAR", "ME" };


    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override public Fragment getItem(int position) {
        return new HotFragment();
    }


    @Override public int getCount() {
        return AppConfig.PAGE_COUNT;
    }


    @Override public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
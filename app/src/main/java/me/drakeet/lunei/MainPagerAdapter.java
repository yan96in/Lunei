package me.drakeet.lunei;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        return new Fragment();
    }


    @Override public int getCount() {
        return AppConfig.PAGE_COUNT;
    }


    @Override public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
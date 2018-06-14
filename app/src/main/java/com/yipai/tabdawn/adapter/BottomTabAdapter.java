package com.yipai.tabdawn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 90449 on 2018/6/14.
 */

public class BottomTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles;
    public BottomTabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public BottomTabAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < fragments.size()) {
            fragment = fragments.get(position);
        } else {
            fragment = fragments.get(0);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}

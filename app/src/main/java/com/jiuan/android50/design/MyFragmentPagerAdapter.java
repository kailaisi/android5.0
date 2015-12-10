package com.jiuan.android50.design;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * vp的适配器
 * Created by wu on 2015/12/7.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private Context mContext;
    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        mContext = context;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "TAB" + position;
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}

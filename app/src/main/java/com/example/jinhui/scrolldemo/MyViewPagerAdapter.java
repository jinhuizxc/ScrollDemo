package com.example.jinhui.scrolldemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2018/10/22.
 */


public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList;


    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(ArrayList<Fragment> fragmentList){
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

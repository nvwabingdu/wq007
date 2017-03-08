package com.example.administrator.intent.F3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/28.
 */
public class FragmenPagerAdapter extends FragmentPagerAdapter{
private ArrayList<Fragment> list;
    private String[] name;
    public FragmenPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] name) {
        super(fm);
        this.list=list;
        this.name=name;
    }
   //哪一页显示
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }
}

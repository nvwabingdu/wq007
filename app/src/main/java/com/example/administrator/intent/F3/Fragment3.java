package com.example.administrator.intent.F3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.intent.F1.FragmenPagerAdapter;
import com.example.administrator.intent.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Fragment3 extends Fragment {
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] name=new String[]{"收藏","历史"};
    private ArrayList<Fragment> fragm_list=new ArrayList<>();

    private FragmentPagerAdapter fragmentPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment3,null);

        viewPager= (ViewPager) view.findViewById(R.id.f3_v3_);
        tabLayout= (TabLayout) view.findViewById(R.id.fragment3_tablayout);

        fragmentPagerAdapter=new FragmenPagerAdapter(getFragmentManager(),getData(),name);
        viewPager.setAdapter(fragmentPagerAdapter);
        //预加载
//      viewPager.setOffscreenPageLimit(0);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public ArrayList<Fragment> getData(){
        for(int i=0;i<name.length;i++){
            Fragment3_item f=new Fragment3_item();
            Bundle bundle=new Bundle();
            bundle.putString("name",name[i]);
            f.setArguments(bundle);
            fragm_list.add(f);

        }
        return fragm_list;
    }
}

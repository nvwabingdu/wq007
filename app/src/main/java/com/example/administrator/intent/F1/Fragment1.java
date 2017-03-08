package com.example.administrator.intent.F1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.intent.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/2/27.
 */
public class Fragment1 extends Fragment {
    //必须写一个方法
    //oncreatview
    private ViewPager viewPager;
    private ArrayList<Fragment> fragm_list=new ArrayList<>();
    private FragmentPagerAdapter fragmentPagerAdapter;
    private String[] name=new String[]{"头条","社会","国内","国际","娱乐","体育","军事","科技","财经"};
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null);
        viewPager= (ViewPager) view.findViewById(R.id.f1_v1_);
        tabLayout= (TabLayout) view.findViewById(R.id.tablayout);

        fragmentPagerAdapter=new FragmenPagerAdapter(getFragmentManager(),getData(),name);
        viewPager.setAdapter(fragmentPagerAdapter);
        //预加载
//      viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    public ArrayList<Fragment> getData(){
        for(int i=0;i<name.length;i++){
            Fragment1_item f=new Fragment1_item();
            Bundle bundle=new Bundle();
            bundle.putString("name",name[i]);
            f.setArguments(bundle);
            fragm_list.add(f);
        }
        return fragm_list;
    }
}

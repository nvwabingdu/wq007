package com.example.administrator.intent.LeadActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.intent.BaseActivity;
import com.example.administrator.intent.LogoActivity.Logo_activity;
import com.example.administrator.intent.R;

/**
 * Created by Administrator on 2017/2/27.
 */
public class LeadActivity extends BaseActivity{
    private Logo_activity logo_activity;
    private SharedPreferences preferences;
    private ViewPager viewPager;
    private int page;
    //创建两个变量 一个抬起  一个按下
    Float upX;
    Float downX;
    @Override
    protected void addLayout() {
        setContentView(R.layout.lendactivity);
    }

    @Override
    protected void addView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);

    }
    @Override
    protected void goStart() {
//数据源图片
        ImageView[] views = new ImageView[4];
        for (int i = 0; i < views.length; i++) {
            views[i] = new ImageView(this);
        }
        views[0].setBackgroundResource(R.drawable.wq81);
        views[1].setBackgroundResource(R.drawable.wq82);
        views[2].setBackgroundResource(R.drawable.wq83);
        views[3].setBackgroundResource(R.drawable.wq84);

        //设置适配器
        viewPager.setAdapter(new viewpager_adapter(views));


        //手势
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取点击事件
                switch (event.getAction()) {
                    //常量按下
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        break;
                    //常量弹起
                    case MotionEvent.ACTION_UP:
                        upX = event.getX();
                        if (page==3&&downX - upX > 200) {
                            startActivity(new Intent(LeadActivity.this, Logo_activity.class));
                        }
                        break;
                }
                return false;
            }
        });

//viewpager的监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                page=position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

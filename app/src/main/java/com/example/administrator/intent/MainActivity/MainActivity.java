package com.example.administrator.intent.MainActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.intent.BaseActivity;
import com.example.administrator.intent.F1.Fragment1;
import com.example.administrator.intent.F2.Fragment2;
import com.example.administrator.intent.F3.Fragment3;
import com.example.administrator.intent.F4.Fragment4;
import com.example.administrator.intent.R;
import com.example.administrator.intent.ThemeManager;

public class MainActivity extends BaseActivity implements ThemeManager.OnThemeChangeListener{
    private FragmentManager fragmentManager;//fragment管理者
    private FragmentTransaction transaction;//fragmet管理事务
    private int now_count=-1;
    private Fragment[] fragments=new Fragment[4];
    private ImageView[] imageViews =new ImageView[4];
    private TextView[] tvs=new TextView[4];

    private ActionBar supportActionBar;
    @Override
    protected void addLayout() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void addView() {
        imageViews[0]= (ImageView) findViewById(R.id.main_img1);
        imageViews[1]= (ImageView) findViewById(R.id.main_img2);
        imageViews[2]= (ImageView) findViewById(R.id.main_img3);
        imageViews[3]= (ImageView) findViewById(R.id.main_img4);

        tvs[0]= (TextView) findViewById(R.id.tt1);
        tvs[1]= (TextView) findViewById(R.id.tt2);
        tvs[2]= (TextView) findViewById(R.id.tt3);
        tvs[3]= (TextView) findViewById(R.id.tt4);

        ThemeManager.registerThemeChangeListener(this);
        supportActionBar = getSupportActionBar();
        down_layout=findViewById(R.id.bottow_layout);
    }



    @Override
    protected void goStart() {
        imageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViews[0].setImageResource(R.drawable.vt);
                imageViews[1].setImageResource(R.drawable.vy);
                imageViews[2].setImageResource(R.drawable.vq);
                imageViews[3].setImageResource(R.drawable.vu);
                for(int i=0;i<tvs.length;i++){
                    tvs[i].setTextColor(0xff02f3fc);
            }
                tvs[0].setTextColor(0xfff85959);
                choiceFragment(0);
            }
        });
        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViews[0].setImageResource(R.drawable.vs);
                imageViews[1].setImageResource(R.drawable.vz);
                imageViews[2].setImageResource(R.drawable.vq);
                imageViews[3].setImageResource(R.drawable.vu);
                for(int i=0;i<tvs.length;i++){
                    tvs[i].setTextColor(0xff02f3fc);
                }
                tvs[1].setTextColor(0xfff85959);
                choiceFragment(1);
                ThemeManager.setThemeMode(ThemeManager.getThemeMode() == ThemeManager.ThemeMode.DAY
                        ? ThemeManager.ThemeMode.NIGHT : ThemeManager.ThemeMode.DAY);
            }
        });
        imageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViews[0].setImageResource(R.drawable.vs);
                imageViews[1].setImageResource(R.drawable.vy);
                imageViews[2].setImageResource(R.drawable.vr);
                imageViews[3].setImageResource(R.drawable.vu);
                for(int i=0;i<tvs.length;i++){
                    tvs[i].setTextColor(0xff02f3fc);
                }
                tvs[2].setTextColor(0xfff85959);
                choiceFragment(2);
            }
        });
        imageViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViews[0].setImageResource(R.drawable.vs);
                imageViews[1].setImageResource(R.drawable.vy);
                imageViews[2].setImageResource(R.drawable.vq);
                imageViews[3].setImageResource(R.drawable.vv);
                for(int i=0;i<tvs.length;i++){
                    tvs[i].setTextColor(0xff02f3fc);
                }
                tvs[3].setTextColor(0xfff85959);
                choiceFragment(3);
            }
        });

        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        fragments[0]=new Fragment1();
        transaction.add(R.id.main_layout,fragments[0]);
        transaction.commit();
        now_count=0;

    }

//显示与隐藏的方法
    public void choiceFragment(int btn_index){
        if(now_count!=btn_index){
            transaction=fragmentManager.beginTransaction();
            if(fragments[btn_index]==null){
                fragments[btn_index]=newFragment(btn_index);
                transaction.add(R.id.main_layout,fragments[btn_index]);
            }
            else{
                transaction.show(fragments[btn_index]);
            }
            transaction.hide(fragments[now_count]);
            transaction.commit();
            now_count=btn_index;
        }
    }

//new出一个fragment
    public Fragment newFragment(int btn_index){
        switch (btn_index) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();
        }
        return null;
    }





private View down_layout;

    public void initTheme() {

//        tv.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
//        btn_theme.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        down_layout.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.backgroundColor)));
        // 设置标题栏颜色
        if(supportActionBar != null){
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.colorPrimary))));
        }
        // 设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.colorPrimary)));
        }
    }

    @Override
    public void onThemeChanged() {
        initTheme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterThemeChangeListener(this);
    }

}

package com.example.administrator.intent.LogoActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.intent.BaseActivity;
import com.example.administrator.intent.LeadActivity.LeadActivity;
import com.example.administrator.intent.MainActivity.MainActivity;
import com.example.administrator.intent.R;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Logo_activity extends BaseActivity{
    private SharedPreferences sp;
    private Button button;

    @Override
    protected void addLayout() {
        setContentView(R.layout.logolayout);
    }

    @Override
    protected void addView() {
        button= (Button) findViewById(R.id.logo_btn);
    }
    @Override
    protected void goStart() {
//第一次登陆跳转到引导页面
        sp = this.getSharedPreferences("wq", Activity.MODE_WORLD_READABLE);
        if (get().equals("第一次安装程序")) {
            save();
            startActivity(new Intent(Logo_activity.this, LeadActivity.class));
        } else {
              //用模板模式就需要下面方法
             //setContentView(R.layout.logolayout);
            //跳转主页面
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Logo_activity.this, MainActivity.class));
                }
            });
        }
    }
    //共享参数的保存
    public void save() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("one", "程序安装");
        //提交才能保存
        editor.commit();
    }
    //共享参数的设置
    public String get() {
        //第二个参数会有默认值
        String str = sp.getString("one", "第一次安装程序");
        return str;
    }
}

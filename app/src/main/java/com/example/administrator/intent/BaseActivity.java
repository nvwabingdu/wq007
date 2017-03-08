package com.example.administrator.intent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/27.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout();
        addView();
        goStart();
    }
    protected abstract void addLayout();
    protected abstract void addView();
    protected abstract void goStart();
}

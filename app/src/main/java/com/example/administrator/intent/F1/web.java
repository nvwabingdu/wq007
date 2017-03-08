package com.example.administrator.intent.F1;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.intent.BaseActivity;
import com.example.administrator.intent.F3.DB_C;
import com.example.administrator.intent.F3.User;
import com.example.administrator.intent.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/2.
 */
public class web extends BaseActivity {
    private WebView webView;
    private ProgressBar bar;
    private ImageView imgbut;

    @Override
    protected void addLayout() {
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void addView() {
        webView = (WebView) findViewById(R.id.load_webview);
        bar = (ProgressBar) findViewById(R.id.load_Bar);
        collation_img = (ImageButton) findViewById(R.id.collation_img);
        imgbut = (ImageView) findViewById(R.id.web_btn);
    }

    String url;
    String title;
    String imgurl;

    @Override
    protected void goStart() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        imgurl = intent.getStringExtra("img");
        webView.loadUrl(url);
        // progressbar处理
        bar();
        //网页加载后续处理 网址发生改变
        urlchange();
        //网页加载的功能
        gongneng();
        //获取网页标题 和网址 用于收藏
        dbc = new DB_C(this);
        gettitle_url();

        //网页分享
        sharweb();

    }

    //网页分享
    private void sharweb() {
        UMImage image = new UMImage(web.this, imgurl);//网络图片

        final UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription("XiaoWang_share");//描述

        imgbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(web.this)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.QZONE, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.ALIPAY)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {
                                Toast.makeText(web.this, "开始分享", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                Toast.makeText(web.this, "分享成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(web.this, "分享失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {
                                Toast.makeText(web.this, "取消分享", Toast.LENGTH_SHORT).show();
                            }
                        }).open();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //进度条
    private void bar() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
    }

    //网页加载后续处理 网址发生改变
    private void urlchange() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    //网页设置，是否可以点击等事件
    private void gongneng() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // User settings
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
    }

    //复写onback方法，网页连续返回
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //获取网页标题和网址  用于收藏实现
    private DB_C dbc;
    private ImageButton collation_img;
    private boolean flag = false;

    public void gettitle_url() {
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, final String title) {
                super.onReceivedTitle(view, title);
                //历史记录
                if (repetat(dbc.showAll(), webView.getUrl()) == false) {
                    dbc.add(title, webView.getUrl(), "是否");
                }
                //收藏的监听
                collation_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flag == false) {
                            collation_img.setImageResource(R.drawable.notification_rating_bar_progress);
                            //收藏
                            if (repetat(dbc.showAll(), webView.getUrl()) == false) {
                                dbc.add(title, webView.getUrl(), "是");
                            }
                            Toast.makeText(web.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            flag = true;
                        } else if (flag == true) {
                            collation_img.setImageResource(R.drawable.notification_rating_bar_bg);
                            Toast.makeText(web.this, "取消收藏", Toast.LENGTH_SHORT).show();
                            if (repetat(dbc.showAll(), webView.getUrl()) == false) {
                                dbc.add(title, webView.getUrl(), "否");
                            }
                            //取消收藏
                            dbc.deleteAll(dbc.showAll());
                            flag = false;
                        }
                    }
                });
            }
        };
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(wvcc);

        // 创建WebViewClient对象
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                webView.loadUrl(url);
                // 消耗掉这个事件。Android中返回True的即到此为止吧,事件就不会冒泡传递了，我们称之为消耗掉
                return true;
            }
        };
        webView.setWebViewClient(wvc);
    }

    //去掉重复
    public boolean repetat(ArrayList<User> list, String url) {
        if(list.size()<1){
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUrl().equals(url)) {
                return true;
            }
        }
        Log.d("totot", list.toString());
        return false;
    }
}

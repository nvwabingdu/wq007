package com.example.administrator.intent.F1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.intent.MainActivity.MainActivity;
import com.example.administrator.intent.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/28.
 */
public class Fragment1_item extends Fragment {
    private String name;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private RecyClerViewAdapter recycleviewAdapter;

    private View f1,f2;

    private String[] words = new String[]{"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing"};

    //顶部刷新之后显示
    private View toplayout;
    private TextView toptext;
    long first_time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_item, null);

        //那个什么set_recyclerview循环控件;
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //帧布局
        f1=view.findViewById(R.id.f1_i1_first);
        f2=view.findViewById(R.id.f1_i1_after);

        //顶部刷新
        toplayout=view.findViewById(R.id.frament_items_toplayout);
        toptext= (TextView) view.findViewById(R.id.frament_items_toplayout_text);

        toplayout.setVisibility(View.GONE);

        //设置adapter
        recycleviewAdapter = new RecyClerViewAdapter(getContext());
        recyclerView.setAdapter(recycleviewAdapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //适配器

        //swipeRefreshLayout下拉
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        set_SwipeRefreshLayout();

        //
        name = getArguments().getString("name");
        Log.d("nannn",name);
        retrofit(wenben(name), true);


        return view;
    }

    //recyclerview方法
    private void set_recyclerview() {
        //recyclerview控件
        //设置布局管理器 此处可设置为列表new LinearLayoutManager(getContext())
        // 表格new GridLayoutManager(getContext(),2)
        // 瀑布流   new StaggeredGridLayoutManager()
        //recycleviewAdapter=new RecyClerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        recyclerView.setAdapter(recycleviewAdapter);
        //回调
        recycleviewAdapter.getjiekou(new RecyClerViewAdapter.jiekou() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "url", Toast.LENGTH_SHORT).show();
            }
        });
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //        recyclerView.addItemDecoration(new DividerItemDecoration(
        //        getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
    }

    //文本加入网址
    private String wenben(String name) {
        switch (name) {
            case "头条":
                return words[0];
            case "社会":
                return words[1];
            case "国内":
                return words[2];
            case "国际":
                return words[3];
            case "娱乐":
                return words[4];
            case "体育":
                return words[5];
            case "军事":
                return words[6];
            case "科技":
                return words[7];
            case "财经":
                return words[8];
//          case "时尚":
//              return words[8];
        }
        return null;
    }

    //下拉控件  swiperefreshlayout
    public void set_SwipeRefreshLayout() {
        //代码设置
        //swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.newinfo_swiperefresh);
        //设置下拉刷新的进度条颜色（最多四种）
        swipeRefreshLayout.setColorSchemeResources(R.color.bule, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                retrofit(wenben(name), true);
                //top顶部的刷新效果
                first_time=System.currentTimeMillis()+(long) 4000;
                toptext.setText("更新了"+new Random().nextInt(5)+"条新消息");
                toplayout.setVisibility(View.VISIBLE);
            }
        });
    }

    //网址请求
    public void retrofit(String str, final boolean isupdate) {
        //网址
        //解析工具
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/toutiao/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //准备请求网络
        Jiekou serveice = retrofit.create(Jiekou.class);
        //通过回调获得结果
        Call<F_class> call = serveice.getBean(str, "8b41621da64fb5a7db660117a62cfa36");
        //请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<F_class>() {
            @Override
            public void onResponse(Call<F_class> call, final Response<F_class> response) {
                if (isupdate) {
                    recycleviewAdapter.updateList(response.body().getResult().getData());
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    recycleviewAdapter.addList(response.body().getResult().getData());
                }
                if( recycleviewAdapter.getlist().size()<=10){
                    f1.setVisibility(View.VISIBLE);
                    f2.setVisibility(View.INVISIBLE);
                }else {
                f1.setVisibility(View.INVISIBLE);
                f2.setVisibility(View.VISIBLE);
                    if(System.currentTimeMillis()+(long)1500<=first_time){
                        toplayout.setVisibility(View.GONE);
                    }
                }



                //item点击事件
                recycleviewAdapter.getjiekou(new RecyClerViewAdapter.jiekou() {
                    @Override
                    public void onItemClick(View view, int position) {
                        MainActivity mainactivity = (MainActivity) getActivity();
                        Intent intent = new Intent(mainactivity, web.class);
                        intent.putExtra("url", response.body().getResult().getData().get(position).getUrl());
                        intent.putExtra("title",response.body().getResult().getData().get(position).getTitle());
                        intent.putExtra("img",response.body().getResult().getData().get(position).getThumbnail_pic_s());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<F_class> call, Throwable t) {
                Log.d("msg", t + "");
            }
        });
    }


    //通过接口去规定请求方式和请求后的数据格式
    //--》GET请求（注解）
    //index?type=top&key=APPKEY
    public interface Jiekou {
        @GET("index")
        Call<F_class> getBean(@Query("type") String type, @Query("key") String key);
    }

}

package com.example.administrator.intent.F3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.intent.F1.web;
import com.example.administrator.intent.MainActivity.MainActivity;
import com.example.administrator.intent.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/5.
 */
public class Fragment3_item extends Fragment{
    private RecyclerView recyclerView;

    private RecyClerViewAdapter recycleviewAdapter;
    private  String name;

    private ArrayList<User> list=new ArrayList<>();
    private DB_C db_c;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3_item,null);

     //找布局
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment3_item_Recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //分别设置收藏和历史的集合
        db_c=new DB_C(getContext());
        name = getArguments().getString("name");






        Log.d("sss1",db_c.showAll().toString());
        if(name.equals("收藏")){
            for(int i=0;i<db_c.showAll().size();i++){
                if(db_c.showAll().get(i).getYes_no().equals("是")){
                    list.add(db_c.showAll().get(i));
                   Log.d("sss1",db_c.showAll().get(i).toString());
                }
            }
        }
     if(name.equals("历史")){
            for(int i=0;i<db_c.showAll().size();i++){
                    list.add(db_c.showAll().get(i));
                Log.d("sss2",db_c.showAll().get(i).toString());
            }
        }


        //设置adapter
        recycleviewAdapter = new RecyClerViewAdapter(list,getContext());
        recyclerView.setAdapter(recycleviewAdapter);
        recycleviewAdapter.notifyDataSetChanged();


        //设置Item增加、移除动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recycleviewAdapter.getjiekou(new RecyClerViewAdapter.jiekou() {
            @Override
            public void onItemClick(View view, int position) {
               MainActivity mainActivity= (MainActivity) getActivity();
                Intent intent=new Intent(mainActivity,web.class);
                intent.putExtra("url",db_c.showAll().get(position).getUrl());
                startActivity(intent);
            }
        });

        return view;
    }
}

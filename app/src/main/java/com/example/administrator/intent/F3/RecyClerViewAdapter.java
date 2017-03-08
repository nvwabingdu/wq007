package com.example.administrator.intent.F3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.intent.F1.F_class;
import com.example.administrator.intent.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public class RecyClerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<User> list;
    private Context context;

    //构造
    public RecyClerViewAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //返回数据的条数 和listview一样
    @Override
    public int getItemCount() {
        return list.size();
    }


    //创建子布局  返回内部类的对象holder
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder=null;
        if(viewType==1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item1,null);
            holder=new MyViewHolder(view);}
          return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    //设置布局上的值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       //点击之后回调方法  传话：我点击了你要干嘛
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jie!=null) {
                    jie.onItemClick(v, position);
                }
            }
        });
        //判断是哪个类的实例
            MyViewHolder holder1= (MyViewHolder) holder;
            holder1.title.setText(list.get(position).getTitle());
            holder1.url.setText(list.get(position).getUrl());
    }

    //内部类找ID
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,url;
        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.f3_adapter_title);
            url= (TextView) itemView.findViewById(R.id.f3_adapter_url);
        }
    }










    //回调    先创建接口
    public interface jiekou{
        void onItemClick(View view, int position);
    }


    //注册接口
    private jiekou jie;
    public void getjiekou(jiekou jie){
        this.jie=jie;
    }
}


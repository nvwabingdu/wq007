package com.example.administrator.intent.F1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.intent.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public class RecyClerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<F_class.ResultBean.DataBean> list;
    private Context context;

    public RecyClerViewAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    //构造
    public RecyClerViewAdapter(List<F_class.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //添加数据
    public void addList(List<F_class.ResultBean.DataBean> list){
        if(this.list.containsAll(list)){
            return;
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    //更新数据
    public void updateList( List<F_class.ResultBean.DataBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    //获取集合
    public List<F_class.ResultBean.DataBean> getlist(){
        return  this.list;
    }


    //创建子布局  返回内部类的对象holder
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder=null;
//        if(viewType==1){
//            //R.layout.item2,parent,false充满
//            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item1,null);
//            holder=new MyViewHolder(view);
//        }
        if(viewType==2){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,null);
            holder=new MyViewHolder2(view);
        }else{
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item3,null);
            holder=new MyViewHolder3(view);
        }
        return holder;
    }

     //在holder上绑定控件
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
//        if(holder instanceof MyViewHolder1) {
//            MyViewHolder holder1= (MyViewHolder) holder;
//            holder1.tv_title.setText(list.get(position).getTitle());
//            holder1.tv_autor_name.setText(list.get(position).getAuthor_name());
//            holder1.tv_date.setText(list.get(position).getDate());
//
//            Picasso.with(context)
//                    .load(list.get(position).getThumbnail_pic_s())
//                     .placeholder(R.drawable.errloading)
//                    .into(((MyViewHolder) holder1).image_s);
//        }
        if(holder instanceof MyViewHolder2) {
            MyViewHolder2 holder2= (MyViewHolder2) holder;
            holder2.tv_title.setText(list.get(position).getTitle());
            holder2.tv_autor_name.setText(list.get(position).getAuthor_name());
            holder2.tv_date.setText(list.get(position).getDate());

            Picasso.with(context)
                    .load(list.get(position).getThumbnail_pic_s())
                    .placeholder(R.drawable.errloading)
                    .error(R.drawable.errloading)
                    .into(((MyViewHolder2) holder2).image_s);
            Picasso.with(context)
                    .load(list.get(position).getThumbnail_pic_s02())
                    .error(R.drawable.errloading)
                    .placeholder(R.drawable.errloading)
                    .into(((MyViewHolder2) holder2).image_s2);
            Picasso.with(context)
                    .load(list.get(position).getThumbnail_pic_s03())
                    .placeholder(R.drawable.errloading)
                    .error(R.drawable.errloading)
                    .into(((MyViewHolder2) holder2).image_s3);
        }
        else{
            MyViewHolder3 holder3= (MyViewHolder3) holder;
            holder3.tv_title.setText(list.get(position).getTitle());
            holder3.tv_autor_name.setText(list.get(position).getAuthor_name());
            holder3.tv_date.setText(list.get(position).getDate());

           Picasso.with(context)
                   .load(list.get(position).getThumbnail_pic_s())
                   .placeholder(R.drawable.errloading)
                   .error(R.drawable.errloading)
                   .into(((MyViewHolder3) holder3).image_s3);
        }
    }



  //返回数据的条数 和listview一样
    @Override
    public int getItemCount() {
        return list.size();
    }

    //复写设置类型方法
    @Override
    public int getItemViewType(int position) {
        if(position%3==0){
            return 1;
        }else {
            return 2;
        }
    }

    //内部类布局二
    class MyViewHolder2 extends RecyclerView.ViewHolder{
        TextView tv_title,tv_autor_name,tv_date;
        ImageView image_s,image_s2,image_s3;
        public MyViewHolder2(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.item2_title);
            tv_autor_name= (TextView) itemView.findViewById(R.id.item2_author_name);
            tv_date= (TextView) itemView.findViewById(R.id.item2_date);

            image_s= (ImageView) itemView.findViewById(R.id.item2_img1);
            image_s2= (ImageView) itemView.findViewById(R.id.item2_img2);
            image_s3= (ImageView) itemView.findViewById(R.id.item2_img3);
        }
    }
    //内部类布局三
    class MyViewHolder3 extends RecyclerView.ViewHolder{
        TextView tv_title,tv_autor_name,tv_date;
        ImageView image_s3;
        public MyViewHolder3(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.item3_title);
            tv_autor_name= (TextView) itemView.findViewById(R.id.item3_author_name);
            tv_date= (TextView) itemView.findViewById(R.id.item3_date);

            image_s3= (ImageView) itemView.findViewById(R.id.item3_img);
        }
    }



    //回调    先创建接口
    public interface jiekou{
        void onItemClick(View view,int position);
    }


    //注册接口
    private jiekou jie;
    public void getjiekou(jiekou jie){
        this.jie=jie;
    }
}


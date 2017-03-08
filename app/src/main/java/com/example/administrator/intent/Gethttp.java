package com.example.administrator.intent;

import android.util.Log;

import com.example.administrator.intent.F1.F_class;

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
public class Gethttp {

    private  String str;

    public void retrofit(String name) {
        //网址
        //解析工具
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/toutiao/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //准备请求网络
        Jiekou serveice = retrofit.create(Jiekou.class);
        //通过回调获得结果
        Call<F_class> call = serveice.getBean(name,"8b41621da64fb5a7db660117a62cfa36");
        //请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<F_class>() {
            @Override
            public void onResponse(Call<F_class> call, final Response<F_class> response) {
                str=response.body().toString();
                Log.d("msg1",str);
            }
            @Override
            public void onFailure(Call<F_class> call, Throwable t) {

            }
        });
    }
    //通过接口去规定请求方式和请求后的数据格式
    //--》GET请求（注解）
    //index?type=top&key=APPKEY
    public interface Jiekou{
        @GET("index")
        Call<F_class> getBean(@Query("type") String type, @Query("key") String key);
    }


    public String toStr(){
        return str;
    }
}

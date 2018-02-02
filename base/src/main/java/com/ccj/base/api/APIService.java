package com.ccj.base.api;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 调用后台的接口,架构网络层采用Retroft+Rxjava+gson
 * Created by ccj on 2016/7/1.
 *
 */
public class APIService {

    private static final String TAG = "APIService";

    //get请求
    public static final String URL_GANK_IO = "http://gank.io";//gank.io 中的妹子API


    /**
     * 基础地址
     * 初始化 retroft
     */
    protected static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl( URL_GANK_IO )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

   // protected static final RetrofitRequest apiManager = sRetrofit.create(RetrofitRequest.class);



    /**********************仿照上面的方法,进行请求数据****************************/




}

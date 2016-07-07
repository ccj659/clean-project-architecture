package com.efly.flyhelper.api;

import android.util.Log;

import com.efly.flyhelper.bean.User;
import com.efly.flyhelper.bean.WeatherData;
import com.efly.flyhelper.utils.TLog;

import java.util.HashMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/1.
 *
 */
public class APIService {

    private static final String TAG = "APIService";
    public static final String URL_HOST ="http://123.234.82.23" ;//服务器


    /**
     * 基础地址
     */
    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(URL_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();
    private static final RetrofitRequest apiManager = sRetrofit.create(RetrofitRequest.class);





    /**
     * 获取登录数据
     * @param city
     * @return
     */
    public static Observable<User> userLogin(String format, String city) {
        HashMap<String,String> hashMap =new HashMap<>();
        hashMap.put("UserPhone", format);
        hashMap.put("UserPassWord", city);
        TLog.log(hashMap.toString());
        Log.e(TAG,hashMap.toString());
        Observable<User> ss = apiManager.userLogin(hashMap);
        return  ss;
    }

    /**
     * 获取天气数据
     * @param city
     * @return
     */

    public static Observable<WeatherData> getWeatherData(String format, String city) {
        Observable<WeatherData> ss = apiManager.getWeatherData(format, city, "ad1d20bebafe0668502c8eea5ddd0333");
        return  ss;
    }





}

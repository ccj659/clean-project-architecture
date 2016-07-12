package com.efly.flyhelper.api;

import com.efly.flyhelper.bean.User;
import com.efly.flyhelper.bean.WeatherData;

import java.util.HashMap;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 *
 * Created by ccj on 2016/7/6.
 */
public interface RetrofitRequest {


    boolean isTest=true; //是否在测试环境下
    //发布之前更改
    String BASE_URL_TEST = "/flyapptest/";//测试服务器
    String BASE_URL_OFFICAL = "/flyapp/";//正式服务器

    String BASE_URL = isTest?BASE_URL_TEST:BASE_URL_OFFICAL;//发布服务器


    /**
     * 登录返回(json post)
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<User> userLogin(@Body HashMap<String, String> body);

    /**
     * 获取天气数据(测试)
     * @param cityname
     * @param key
     * @return
     */

    @GET("/weather/index")
    Observable<WeatherData> getWeatherData(@Query("format") String format, @Query("cityname") String cityname, @Query("key") String key);

    
    
    
}

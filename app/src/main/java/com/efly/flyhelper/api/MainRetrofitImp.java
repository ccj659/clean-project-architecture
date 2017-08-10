package com.efly.flyhelper.api;

import com.efly.flyhelper.bean.Meizhi;
import com.efly.flyhelper.bean.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chenchangjun on 17/8/10.
 */

public interface MainRetrofitImp {



    /**
     * 获取天气数据(测试)
     * @param cityname
     * @param key
     * @return
     */

    @GET("/weather/index")
    Observable<WeatherData> getWeatherData(@Query("format") String format, @Query("cityname") String cityname, @Query("key") String key);


    @GET("/api/data/福利/{date}")
    Observable<Meizhi> getMeiZhi(@Path("date") String date);
}

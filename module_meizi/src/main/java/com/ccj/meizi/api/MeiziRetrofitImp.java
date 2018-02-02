package com.ccj.meizi.api;


import com.ccj.meizi.bean.Meizhi;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chenchangjun on 17/8/10.
 */

public interface MeiziRetrofitImp {

    @GET("/api/data/福利/{date}")
    Observable<Meizhi> getMeiZhi(@Path("date") String date);



    @GET("/api/random/data/福利/10")
    Observable<Meizhi> getMeiZhi();
}

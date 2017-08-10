package com.ccj.login.api;

import com.ccj.base.api.RetrofitRequest;
import com.ccj.base.bean.User;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by chenchangjun on 17/8/10.
 */

public interface LoginRetrofitImp extends RetrofitRequest {


    /**
     * 登录返回(json post)
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<User> userLogin(@Body HashMap<String, String> body);

}

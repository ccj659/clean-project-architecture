package com.ccj.login.api;

import com.ccj.base.api.APIService;
import com.ccj.base.bean.User;
import com.ccj.base.utils.TLog;

import java.util.HashMap;

import rx.Observable;

/**
 * 继承
 * Created by chenchangjun on 17/8/10.
 */

public class LoginAPIServiceImp extends APIService {


    protected static final LoginRetrofitImp loginApiManager = sRetrofit.create(LoginRetrofitImp.class);

    /**
     * 登录,返回,我这边用的是json格式的post,大家可以进行选择
     * @param city
     * @return
     */
    public static Observable<User> userLogin(String format, String city) {
        HashMap<String,String> hashMap =new HashMap<>();
        hashMap.put("UserPhone", format);
        hashMap.put("UserPassWord", city);
        TLog.log(hashMap.toString());
        Observable<User> ss = loginApiManager.userLogin(hashMap);
        return  ss;
    }



}

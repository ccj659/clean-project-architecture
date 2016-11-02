package com.efly.flyhelper.api;

import com.efly.flyhelper.bean.Meizhi;
import com.efly.flyhelper.bean.Patch;
import com.efly.flyhelper.bean.User;
import com.efly.flyhelper.utils.TLog;

import java.io.File;
import java.util.HashMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * 调用后台的接口,架构网络层采用Retroft+Rxjava+gson
 * Created by ccj on 2016/7/1.
 *
 */
public class APIService {

    private static final String TAG = "APIService";
    public static final String URL_HOST ="http://123.234.82.23" ;//服务器端口

    //get请求
    public static final String URL_GANK_IO ="http://gank.io" ;//gank.io 中的妹子API


    /**
     * 基础地址
     * 初始化 retroft
     */
    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(URL_GANK_IO==null?URL_HOST:URL_GANK_IO)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final RetrofitRequest apiManager = sRetrofit.create(RetrofitRequest.class);
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
        Observable<User> ss = apiManager.userLogin(hashMap);
        return  ss;
    }

    /**
     *
     * @param versionCode 当前versionCode来校验,判断应该返回那个补丁包
     * @return
     */
    public static Observable<Patch> getPatch(String versionCode) {
        HashMap<String,String> hashMap =new HashMap<>();
        hashMap.put("VersionCode", versionCode);
        TLog.logI(hashMap.toString());
        Observable<Patch> ss = apiManager.getPatch(hashMap);
        return  ss;
    }

    /**
     *
     *
     * @return
     */
    public static Observable<Meizhi> getMeiZhi(String date) {
        Observable<Meizhi> ss = apiManager.getMeiZhi( date);
        TLog.logI(date);
        return  ss;
    }

    /**
     *下载补丁包
     * @param downUrl
     * @return
     */
    public static Observable<File> downPatch(String downUrl) {

        Observable<File> ss = apiManager.downPatch(downUrl);
        TLog.log(downUrl.toString());
        return ss;
    }

    /**********************仿照上面的方法,进行请求数据****************************/





}

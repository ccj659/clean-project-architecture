package com.ccj.base.api;


/**
 *
 * Created by ccj on 2016/7/6.
 */
public interface RetrofitRequest {


    boolean isTest=false; //是否在测试环境下
    //发布之前更改
    String BASE_URL_TEST = "/flyapptest/";//测试服务器
    String BASE_URL_OFFICAL = "/flyapp/";//正式服务器

    String BASE_URL = isTest?BASE_URL_TEST:BASE_URL_OFFICAL;//发布服务器

}

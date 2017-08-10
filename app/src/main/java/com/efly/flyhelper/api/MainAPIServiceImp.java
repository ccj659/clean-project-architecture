package com.efly.flyhelper.api;

import com.ccj.base.api.APIService;
import com.ccj.base.utils.TLog;
import com.efly.flyhelper.bean.Meizhi;

import rx.Observable;

/**
 * 继承
 * Created by chenchangjun on 17/8/10.
 */

public class MainAPIServiceImp extends APIService {


    protected static final MainRetrofitImp apiManager = sRetrofit.create(MainRetrofitImp.class);

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

}

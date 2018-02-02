package com.ccj.meizi.api;

import com.ccj.base.api.APIService;
import com.ccj.base.utils.TLog;
import com.ccj.meizi.bean.Meizhi;

import rx.Observable;

/**
 * 继承
 * Created by chenchangjun on 17/8/10.
 */

public class MeiziAPIServiceImp extends APIService {


    protected static final MeiziRetrofitImp apiManager = sRetrofit.create(MeiziRetrofitImp.class);

    /**
     * @return
     */
    public static Observable<Meizhi> getMeiZhi(String date) {
        Observable<Meizhi> ss = apiManager.getMeiZhi( date);
        TLog.logI(date);
        return  ss;
    }



    /**
     * @return
     */
    public static Observable<Meizhi> getMeiZhi() {
        Observable<Meizhi> ss = apiManager.getMeiZhi( );
        return  ss;
    }
}

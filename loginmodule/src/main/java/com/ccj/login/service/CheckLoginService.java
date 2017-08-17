package com.ccj.login.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.utils.router.LoginModuleService;
import com.ccj.base.utils.router.RouterConstants;

/**
 *     * 实现接口,
 * Created by chenchangjun on 17/8/14.
 */
@Route(path = RouterConstants. LOGIN_SERVICE_IMPL)
public class CheckLoginService implements LoginModuleService{

    /**
     * 实例化服务,面向接口编程
     * @return
     */
    @Override
    public boolean checkLoginState() {
        //可自行在loginModule
        return false;
    }

    @Override
    public void init(Context context) {

    }
}

package com.ccj.login;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterService;

/**
 * Created by chenchangjun on 17/8/9.
 */
@Route(path = RouterConstants.LOGIN_SERVICE_IMPL)
public class LoginServiceImpl implements RouterService {


    @Override
    public void init(Context context) {
        Log.i("LoginServiceImpl","LoginServiceImpl init");
    }

    @Override
    public String start(String name) {
        return "LoginServiceImpl started";
    }


}

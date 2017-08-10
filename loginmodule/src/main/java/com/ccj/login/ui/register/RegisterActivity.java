package com.ccj.login.ui.register;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.eventbus.EventUtils;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.login.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chenchangjun on 17/8/9.
 */
@Route(path = RouterConstants.LOGIN_REGISTER_FRAGMENT)
public class RegisterActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_fragment_register);
        EventBus.getDefault().post(new EventUtils.StringEvent("hello, I am  from com.ccj.login.RegisterActivity.btn_event EventBus"));

    }




}

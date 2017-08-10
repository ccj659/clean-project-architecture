package com.ccj.base.utils.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * Created by chenchangjun on 17/8/9.
 */
@Route(path = "/xxx/xxx")
public class DegradeServiceImpl implements DegradeService {


    @Override
    public void onLost(Context context, Postcard postcard) {

    }

    @Override
    public void init(Context context) {

    }
}

package com.ccj.video.service;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterService;

/**
 * Created by chenchangjun on 17/8/9.
 */
@Route(path = RouterConstants.VIDEO_SERVICE_IMPL)
public class VideoServiceImpl implements RouterService {


    @Override
    public void init(Context context) {
        Log.i("VideoServiceImpl","VideoServiceImpl init");
    }

    @Override
    public String start(String name) {
        return "VideoServiceImpl started";
    }


}

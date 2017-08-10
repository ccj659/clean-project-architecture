package com.ccj.base.utils.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;

/**
 * Created by chenchangjun on 17/8/9.
 */
@Route(path = "/router/RounterSerialization")
public class RounterSerialization implements SerializationService {


    @Override
    public <T> T json2Object(String json, Class<T> clazz) {
        return null;
    }

    @Override
    public String object2Json(Object instance) {
        return null;
    }

    @Override
    public void init(Context context) {

    }
}

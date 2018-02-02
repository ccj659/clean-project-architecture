package com.ccj.base.utils.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;

import java.lang.reflect.Type;

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

    /**
     * Parse json to object
     *
     * @param input json string
     * @param clazz object type
     * @return instance of object
     */
    @Override
    public <T> T parseObject(String input, Type clazz) {
        return null;
    }

    @Override
    public void init(Context context) {

    }
}

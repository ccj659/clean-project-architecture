package com.ccj.base.adapter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类似于 view和 viewgroup 的概念 这里是 viewgroup
 * Created by chenchangjun on 18/1/4.
 */

public abstract class AdapterGroupBean<T> extends AdapterBean implements Serializable {


    //唯一标示!

    public abstract List getList();

    public abstract String getTitle();

    public abstract T getRedirect_data();


}

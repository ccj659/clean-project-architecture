package com.ccj.base.adapter.bean;

import java.io.Serializable;

/**
 * 数据bean
 * 为了不同viewItem 分别对应不同的bean,</br>
 * 避免不同item的字段都杂糅的同一个类中的情况,比如{ mobile中的 CommonRowsBean}</br>
 * 根据Holder建立一个新的bean, 自定义的bean要继承AdapterBean,在bean中添置所需字段,供holder调用,比如(mobile中 BrandRcvAdapter中的holder)</br>
 * <p>
 * Created by chenchangjun on 17/12/28.
 */

public abstract class AdapterBean implements Serializable {

    //唯一标示!


    public abstract void setCell_type(int cell_type);


    public abstract int getCell_type();

}

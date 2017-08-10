package com.ccj.base.view.kaleadapter.item;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * @author ccj
 * @date 2017/3/21
 */
public interface AdapterItem<T> {

    /**
     * @return item布局文件的layoutId
     */
    @LayoutRes
    int getLayoutResId();

    /**
     * 初始化views
     */
    void bindViews(final View root);

    /**
     * 设置view的参数
     * 设置监听等,只执行一次
     */
    void setViews();

    /**
     * 根据数据来设置item的内部views
     *
     * @param t    数据list内部的model
     * @param position 当前adapter调用item的位置
     */
    void handleData(T t, int position);

}  
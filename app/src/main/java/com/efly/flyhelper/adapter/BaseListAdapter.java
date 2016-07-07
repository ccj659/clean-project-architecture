package com.efly.flyhelper.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BaseListAdapter<T> extends android.widget.BaseAdapter {


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

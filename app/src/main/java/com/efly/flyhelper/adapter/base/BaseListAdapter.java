package com.efly.flyhelper.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.efly.flyhelper.bean.BaseBean;

import java.util.ArrayList;

/**
 * listadapter基类  也可以用recycleview 代替listview
 * Created by ccj on 2016/7/5.
 */
public class BaseListAdapter<T extends ArrayList<BaseBean>> extends android.widget.BaseAdapter {
    protected ArrayList T;
    protected Context context;

    public BaseListAdapter(Context context, ArrayList t) {
        this.context = context;
        T = t;
    }


    @Override
    public int getCount() {
        return T.size();
    }

    @Override
    public Object getItem(int position) {
        return T.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

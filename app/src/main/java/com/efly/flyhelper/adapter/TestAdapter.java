package com.efly.flyhelper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ccj.base.adapter.BaseListAdapter;

import java.util.ArrayList;

/**
 * 采用继承关系,
 * Created by Administrator on 2016/7/5.
 */
public class TestAdapter extends BaseListAdapter {


    public TestAdapter(Context context, ArrayList t) {
        super(context, t);//调用父类方法
    }


    /**
     * 重写此方法即可,
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return super.getView(position, convertView, parent);
    }


    class ViewHolder {


    }
}

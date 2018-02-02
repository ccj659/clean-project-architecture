package com.ccj.meizi.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ccj.base.adapter.CommonRcvAdapter;
import com.ccj.base.adapter.bean.AdapterBean;
import com.ccj.base.adapter.item.AdapterItem;
import com.ccj.meizi.holder.MeiziItemHolder;

import java.util.List;


public class MeiziRcvAdapter extends CommonRcvAdapter<AdapterBean> {


    private static final String TAG = "MeiziRcvAdapter";

    public MeiziRcvAdapter(@Nullable List data, Activity mActivity) {
        super(data, mActivity);
    }


    /**
     * 根据getCell_type得到 VIEW_TYPE
     *
     * @param demoModel
     * @return
     */
    @Override
    public Object getItemType(AdapterBean demoModel) {
        return demoModel.getCell_type();

    }

    /**
     * 根据viewType创建Holder
     *
     * @param type 通过{@link #getItemType(Object)}得到的type
     * @return
     */
    @NonNull
    @Override
    public AdapterItem createItem(Object type) {
        Log.d(TAG, "createItem " + type + " view");
        return new MeiziItemHolder( mActivity);

    }
}

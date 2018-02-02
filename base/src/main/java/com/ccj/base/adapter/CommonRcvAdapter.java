package com.ccj.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ccj.base.adapter.item.AdapterItem;
import com.ccj.base.adapter.util.IAdapter;
import com.ccj.base.adapter.util.ItemTypeUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ccj
 * @date 2017/3/23
 */
public abstract class CommonRcvAdapter<T> extends RecyclerView.Adapter<CommonRcvAdapter.RcvAdapterItem> implements IAdapter<T> {

    private List<T> mDataList;

    private Object mType;

    private ItemTypeUtil mUtil;

    private int currentPos;

    public Activity mActivity;


    public CommonRcvAdapter(@Nullable List<T> data, Activity mActivity) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mDataList = data;
        mUtil = new ItemTypeUtil();
        this.mActivity = mActivity;
    }


    public T getItem(int position) {
        if (position < 0 || position > getItemCount() - 1) {
            return null;
        }
        return mDataList.get(position);
    }


    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public void setData(@NonNull List<T> data) {
        mDataList = data;
    }

    @Override
    public List<T> getData() {
        return mDataList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * instead by{@link #getItemType(Object)}
     * <p>
     * 通过数据得到obj的类型的type
     * 然后，通过{@link ItemTypeUtil}来转换位int类型的type
     */
    @Deprecated
    @Override
    public int getItemViewType(int position) {
        this.currentPos = position;
        mType = getItemType(mDataList.get(position));
        return mUtil.getIntType(mType);
    }

    @Override
    public Object getItemType(T t) {
        return -1; // default
    }

    @Override
    public RcvAdapterItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RcvAdapterItem(parent.getContext(), parent, createItem(mType));
    }

    @Override
    public void onBindViewHolder(RcvAdapterItem holder, int position) {

        holder.item.handleData(getConvertedData(mDataList.get(position), mType), position);


    }

    @NonNull
    @Override
    public Object getConvertedData(T data, Object type) {
        return data;
    }

    @Override
    public int getCurrentPosition() {
        return currentPos;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 内部用到的viewHold
    ///////////////////////////////////////////////////////////////////////////

    public static class RcvAdapterItem extends RecyclerView.ViewHolder {

        protected AdapterItem item;

        boolean isNew = true; // debug中才用到

        RcvAdapterItem(Context context, ViewGroup parent, AdapterItem item) {
            super(LayoutInflater.from(context).inflate(item.getLayoutResId(), parent, false));
            this.item = item;
            this.item.bindViews(itemView);
            this.item.setViews();
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // For debug
    ///////////////////////////////////////////////////////////////////////////

    private void debug(RcvAdapterItem holder) {
        boolean debug = false;
        if (debug) {
            holder.itemView.setBackgroundColor(holder.isNew ? 0xffff0000 : 0xff00ff00);
            holder.isNew = false;
        }
    }

}

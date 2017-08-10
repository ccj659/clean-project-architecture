package com.ccj.base.view.kaleadapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ccj.base.view.kaleadapter.item.AdapterItem;
import com.ccj.base.view.kaleadapter.util.IAdapter;
import com.ccj.base.view.kaleadapter.util.ItemTypeUtil;

import java.util.ArrayList;
import java.util.HashMap;
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
    private int headerCount=0;//// TODO: 17/3/23 这个的作用什么？

    //item点击计数，显示错过的好价浮层时用到
    public int itemClickCount;

    public Activity mActivity ;


    public CommonRcvAdapter(@Nullable List<T> data, Activity mActivity) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mDataList = data;
        mUtil = new ItemTypeUtil();
        this.mActivity=mActivity;
    }

    public CommonRcvAdapter(List data) {
    }


    public T getItem(int position) {
        //Log.e("onItemClick",",mDataList-"+mDataList.toString());

        if (position < headerCount || position > getItemCount() - 1) {
            return null;
        }

        return mDataList.get(position - headerCount);
    }

    public <T> void setHeadBanner(List<T> lists, int headerCount) {
        this.headerCount=headerCount;

    }
    /**
     * 配合RecyclerView的pool来设置TypePool
     * @param typePool
     */
    public void setTypePool(HashMap<Object, Integer> typePool) {
        mUtil.setTypePool(typePool);
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
        debug(holder);
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

   public   static class RcvAdapterItem extends RecyclerView.ViewHolder {

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

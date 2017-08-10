package com.ccj.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleAdapter.MyViewHolder>  {


    protected List<T> tList;
    protected Context context;

    public ItemClickListener onItemClickListener;
    public ItemLongClickListener onItemLongClickListener;

    public BaseRecycleAdapter(List<T> tList, Context context) {
        this.tList = tList;
        this.context = context;
    }
    public void setOnItemClickListener(ItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void setOnItemLongClickListener(ItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener=onItemLongClickListener;
    }
    public void setData(List<T> list){
        this.tList = list;
        notifyDataSetChanged();
    }

   /* @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(
                R.layout.item_bitmap_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }*/

    @Override
    public int getItemCount() {
        return tList==null?0:tList.size();
    }


   public abstract class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}

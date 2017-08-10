package com.efly.flyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ccj.base.adapter.BaseRecycleAdapter;
import com.efly.flyhelper.R;
import com.efly.flyhelper.bean.Meizhi;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 * 一提到妹子,就有无穷的写代码的动力~
 */

public class MeiZhiAdapter extends BaseRecycleAdapter<Meizhi.ResultsBean> {
    
    
    
    public MeiZhiAdapter(List<Meizhi.ResultsBean> meizhis, Context context) {
        super(meizhis, context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiZhiAdapter.ConstantHolder(LayoutInflater.from(context).inflate(
                R.layout.item_meizhi_adapter,parent,false));
    }


    @Override
    public void onBindViewHolder(final BaseRecycleAdapter.MyViewHolder holder, final int position) {
        MeiZhiAdapter.ConstantHolder constantHolder =(MeiZhiAdapter.ConstantHolder)holder;
        if (onItemClickListener!=null){
            constantHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            });
        }
       // TLog.log(tList.get(position).toString());
        Picasso .with(context)
                .load(tList.get(position).url)
                .error(R.mipmap.load_img_error)
                .into(constantHolder.image);

    }

    class ConstantHolder extends MyViewHolder{
        ImageView image;
        public ConstantHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image);
        }
    }
    
    
    
    
}

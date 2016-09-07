package com.efly.flyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.efly.flyhelper.R;
import com.efly.flyhelper.adapter.base.BaseRecycleAdapter;
import com.efly.flyhelper.bean.UserDetail;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ConstantAdapter extends BaseRecycleAdapter<UserDetail> {


    public ConstantAdapter(List<UserDetail> users, Context context) {
        super(users, context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConstantHolder(LayoutInflater.from(context).inflate(
                R.layout.item_constant_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(final BaseRecycleAdapter.MyViewHolder holder, final int position) {
        ConstantHolder constantHolder =(ConstantHolder)holder;
        if (onItemClickListener!=null){
            constantHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
        }
        constantHolder.textView.setText(tList.get(position).Users_PersonName);
    }

    class ConstantHolder extends MyViewHolder{
        TextView textView;
        public ConstantHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv_user_name);
        }
    }

}

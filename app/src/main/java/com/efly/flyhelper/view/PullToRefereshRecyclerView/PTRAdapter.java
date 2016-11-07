package com.efly.flyhelper.view.PullToRefereshRecyclerView;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.efly.flyhelper.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * @param <T>
 */

public abstract class PTRAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    public static final int TYPE_DATA = 0;//正常布局
    public static final int TYPE_FOOTER = 1;//尾布局
    public static final int TYPE_HEADER = 2;//头布局

    public static final int FOOTER_VIEW_RESID = R.layout.view_loadmore;

    private static final String s1 = "继续下拉";
    private static final String s2 = "松手以刷新";

    @IntDef({TYPE_DATA, TYPE_FOOTER, TYPE_HEADER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemType {}

    List list;
    Context context;
    TextView textView;
    private View footerView;
    protected int footerViewID,headerViewID;

    protected boolean haveFooterView = false;//有尾布局
    protected boolean haveHeaderView = false;//有尾布局

    //添加头布局
    public void setHeaderView(int headerViewID){
        this.headerViewID = headerViewID;
        haveHeaderView = true;
    }

    //检测是否有头布局
    public boolean haveHeaderView(){
        return haveHeaderView;
    }

    public void setFooterViewAlpha(float f){
        if(footerView!=null)
            footerView.setAlpha(f);
    }

    public void setFooterText(CharSequence charSequence){
        if(textView!=null)
            textView.setText(charSequence);
    }

    protected onClickListener listener;

    public void setOnClickListener(onClickListener listener){
        this.listener = listener;
    }

    public boolean haveFooterView() {
        return haveFooterView;
    }



    private int dp2px(int dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    public void setFooterView(int resID){
        footerViewID = resID;
        haveFooterView = true;
    }

    @Override
    public int getItemViewType(int position) {
        if(haveHeaderView() && position == 0)//有头布局且位置为0
            return TYPE_HEADER;
        else if(haveFooterView() && position == getItemCount()-1)
            return TYPE_FOOTER;
        else
            return TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return (haveHeaderView?1:0) + (haveFooterView?1:0) + list.size();
    }

    public class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_loadMore);
            footerView = itemView;
            footerView.setAlpha(0f);
        }
    }

    public interface onClickListener{
        void onItemClickListener(int position);

        boolean onItemLongClickListener(int position);
    }
}

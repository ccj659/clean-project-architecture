package com.efly.flyhelper.view.PullToRefereshRecyclerView;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.efly.flyhelper.utils.TLog;

public abstract class EndlessRecyclerOnScrollListener extends
        RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerOnScrollListener.class
            .getSimpleName();

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }


    //public abstract void onLoadMore(int currentPage);
    public abstract void onLoadMore();

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {


    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        //得到当前显示的最后一个item的view
        View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount()-1);
        //得到lastChildView的bottom坐标值
        int lastChildBottom = lastChildView.getBottom();
        //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
        int recyclerBottom =  recyclerView.getBottom()-recyclerView.getPaddingBottom();;
        //通过这个lastChildView得到这个view当前的position值
        int lastPosition  = recyclerView.getLayoutManager().getPosition(lastChildView);

        //判断lastChildView的bottom值跟recyclerBottom
        //判断lastPosition是不是最后一个position
        //如果两个条件都满足则说明是真正的滑动到了底部


        //此处当滑到底部时候,因为底部tabhost,lastChildBottom 为1010 recyclerBottom为1020.
        // 发现lastChildBottom <= recyclerBottom 即到达底部.当故,我做了修改.
        if(lastChildBottom <= recyclerBottom &&lastPosition == recyclerView.getLayoutManager().getItemCount()-1 ){
            TLog.logI("onScrolled ");
            onLoadMore();
    }

    }


}
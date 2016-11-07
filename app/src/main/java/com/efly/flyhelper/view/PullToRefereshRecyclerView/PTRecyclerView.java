package com.efly.flyhelper.view.PullToRefereshRecyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/5.
 */

public class PTRecyclerView extends RecyclerView {

    private static final String TAG = "PTRecyclerView";

    private int loadmore_h;//上拉刷新需要的高度

    private boolean flag = false;//flag，是否能刷新

    private boolean refreshing = false;//正在刷新中

    private PTRAdapter adapter;

    private LinearLayoutManager manager;

    private loadMore loadMore;

    public PTRecyclerView(Context context) {
        super(context);
    }

    public PTRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PTRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PTRecyclerView.loadMore getLoadMore() {
        return loadMore;
    }

    public void setLoadMore(loadMore loadMore) {
        this.loadMore = loadMore;
    }

    public void refreshed() {
        isRefreshing(false);
    }

    public void isRefreshing(boolean state) {
        if(adapter == null)
            return;
        if (state) {
            adapter.setFooterViewAlpha(1);
        } else {
            adapter.setFooterText("松手以加载");
            adapter.setFooterViewAlpha(0);
        }
        refreshing = state;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        Log.i(TAG, "state " + state);
        if (flag && state == RecyclerView.SCROLL_STATE_IDLE &&
                manager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
            //不滑动&&加载更多完全显示 的时候
            if (!refreshing) {
                Log.i(TAG, "尝试上拉加载ing");
                isRefreshing(true);
                if (loadMore != null)
                    loadMore.OnRefresh(this);
            } else {
                Log.w(TAG, "已经在加载了");
            }
        } else if (flag && state == RecyclerView.SCROLL_STATE_IDLE && //不滑动
                manager.findLastVisibleItemPosition() == adapter.getItemCount() - 1 && //加载更多不完全显示
                manager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 2) { //加载更多上一项完全显示
            Log.i(TAG, "需要回弹到位置" + (manager.findFirstVisibleItemPosition() - 1));
            smoothScrollToPosition(Math.max(0,manager.findFirstVisibleItemPosition() - 1));
        }
    }

    public void setAdapter(PTRAdapter adapter) {
        super.setAdapter(adapter);
        this.adapter = adapter;
    }

    /*private void smoothScrollUntilFooterViewInvisible(int position) {
        while (manager.findLastCompletelyVisibleItemPosition() < adapter.getItemCount() - 1
                && manager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
            //不能完全看到FooterView的时候
            Log.e("TAG", manager.findLastCompletelyVisibleItemPosition()
                    + " " + manager.findLastVisibleItemPosition()
                    + " " + (getAdapter().getItemCount() - 1)
                    + " " + position);
            if (position < 0)
                position = 0;
            smoothScrollToPosition(position);
            smoothScrollUntilFooterViewInvisible(position - 1);
        }
    }*/

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (adapter!=null && adapter.haveFooterView()) {
            //有尾布局
            if (getLayoutManager() instanceof LinearLayoutManager) {
                if (manager == null)
                    manager = (LinearLayoutManager) getLayoutManager();
                flag = (manager.findLastVisibleItemPosition() ==
                        adapter.getItemCount() - 1);//准备显示footerView

                if (flag && getScrollState() != RecyclerView.SCROLL_STATE_DRAGGING) {
                    stopScroll();//停止滑动
                    stopNestedScroll();
                }

                if (manager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount()-1&&adapter.getItemCount()>8) {//最后一行完全显示
                    adapter.setFooterViewAlpha(1);
                    Log.w(TAG,"最后一行完全显示");
                }else
                    adapter.setFooterViewAlpha(0);


            }

        }
    }

    public interface loadMore {
        void OnRefresh(PTRecyclerView recyclerView);
    }

}

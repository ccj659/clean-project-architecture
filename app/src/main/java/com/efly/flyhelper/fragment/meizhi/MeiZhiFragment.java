package com.efly.flyhelper.fragment.meizhi;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccj.base.adapter.ItemClickListener;
import com.ccj.base.base.BaseApplication;
import com.ccj.base.base.BaseFragment;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.view.CustomItemAnimator;
import com.ccj.base.view.recycleview.OnLoadNextListener;
import com.ccj.base.view.recycleview.SuperRecycleView;
import com.efly.flyhelper.R;
import com.efly.flyhelper.adapter.MeiZhiAdapter;
import com.efly.flyhelper.bean.Meizhi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MeiZhiFragment extends BaseFragment<MeiZhiContract.Presenter> implements MeiZhiContract.View,SwipeRefreshLayout.OnRefreshListener,ItemClickListener,
        OnLoadNextListener{


    private static final String TAG = MeiZhiFragment.class.getSimpleName();
    @BindView(R.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    SuperRecycleView recyclerView;
    private View view;
    private ArrayList<Meizhi.ResultsBean> meiZhi;
    private int page = 0;
    private MeiZhiAdapter constantAdapter;
    private MeiZhiPresenter meiZhiPresenter;
    private Dialog dialog;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_meizi, null);
        }
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(meiZhiPresenter==null){
            meiZhiPresenter = new MeiZhiPresenter(this);
            meiZhiPresenter.start();
        }


    }


    @Override
    public void initView() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color.holo_blue_bright, android.R.color.holo_purple);

        constantAdapter = new MeiZhiAdapter(meiZhi, getActivity());
        //mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setAdapter(constantAdapter);
    }


    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        if(swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(false);
        }

    }


    @Override
    public void showMeiZhiList(ArrayList<Meizhi.ResultsBean>  meizhiList) {
        meiZhi=meizhiList;
        constantAdapter.setData(meizhiList);
        constantAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        BaseApplication.showShortToast(error);
    }

    @Override
    public void navigateToMeiZhiDetail(String url) {

        if (dialog!=null){
            dialog.show();
            return;
        }

        dialog= DialogCreator.createMeiZhiDetailDialog(getContext(), url, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void setListener() {
        recyclerView.setLoadNextListener(this);
        constantAdapter.setOnItemClickListener(this);
        constantAdapter.setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }



    @Override
    public void onRefresh() {
        meiZhiPresenter.refresh();

    }

    @Override
    public void onLoadNext() {
        meiZhiPresenter.loadMoreMeizhi(--page);

    }



    @Override
    public void onItemClick(int position) {
        navigateToMeiZhiDetail(meiZhi.get(position).url);

    }





    @Override
    public void autoShowOrHideToolbar(boolean show) {

    }
}

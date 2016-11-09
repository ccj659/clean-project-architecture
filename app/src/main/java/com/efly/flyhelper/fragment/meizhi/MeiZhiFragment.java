package com.efly.flyhelper.fragment.meizhi;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.efly.flyhelper.R;
import com.efly.flyhelper.adapter.MeiZhiAdapter;
import com.efly.flyhelper.adapter.base.ItemClickListener;
import com.efly.flyhelper.base.BaseApplication;
import com.efly.flyhelper.base.BaseFragment;
import com.efly.flyhelper.bean.Meizhi;
import com.efly.flyhelper.utils.DialogCreator;
import com.efly.flyhelper.utils.TLog;
import com.efly.flyhelper.view.CustomItemAnimator;
import com.efly.flyhelper.view.PullToRefereshRecyclerView.EndlessRecyclerOnScrollListener;
import com.efly.flyhelper.view.PullToRefereshRecyclerView.HeaderViewRecyclerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MeiZhiFragment extends BaseFragment<MeiZhiContract.Presenter> implements MeiZhiContract.View {


    private static final String TAG = MeiZhiFragment.class.getSimpleName();
    @Bind(R.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private View view;
    private ArrayList<Meizhi.ResultsBean> meiZhi;
    private int page = 0;
    private MeiZhiAdapter constantAdapter;
    private HeaderViewRecyclerAdapter stringAdapter;
    private MeiZhiPresenter meiZhiPresenter;
    private LinearLayoutManager linearLayoutManager;
    private Dialog dialog;
    private StaggeredGridLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_use, null);
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
        constantAdapter = new MeiZhiAdapter(meiZhi, getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

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
    }

    @Override
    public void showError(String error) {
        BaseApplication.showShortToast(error);
    }

    @Override
    public void navigateToMeiZhiDetail(String url) {
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

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {


            @Override
            public void onLoadMore() {
                TLog.logI("onLoadMore");
                meiZhiPresenter.loadMoreMeizhi(--page);
            }
        });

        constantAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "onClick-->" + position, Toast.LENGTH_SHORT).show();
                navigateToMeiZhiDetail(meiZhi.get(position).url);
            }
        });

        swipeRefreshLayout
                .setColorSchemeResources(android.R.color.holo_orange_light,
                        android.R.color.holo_green_light,
                        android.R.color.holo_blue_bright,
                        android.R.color.holo_purple);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    public void refresh() {
        meiZhiPresenter.refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}

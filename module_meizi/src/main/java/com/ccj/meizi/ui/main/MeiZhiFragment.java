package com.ccj.meizi.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.RouterConstants;
import com.ccj.base.base.BaseFragment;
import com.ccj.base.utils.ToastUtil;
import com.ccj.base.view.SuperRecyclerView;
import com.ccj.base.view.list.OnLoadNextListener;
import com.ccj.meizi.R;
import com.ccj.meizi.R2;
import com.ccj.meizi.adapter.MeiziRcvAdapter;
import com.ccj.meizi.bean.Meizhi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */
@Route(path = RouterConstants.MEIZI_MUDULE_FRAGMENT_HOME_MEIZI)
public class MeiZhiFragment extends BaseFragment<MeiZhiContract.Presenter> implements MeiZhiContract.View, SwipeRefreshLayout.OnRefreshListener,
        OnLoadNextListener {


    private static final String TAG = MeiZhiFragment.class.getSimpleName();
    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.recycler_view)
    SuperRecyclerView recyclerView;
    private View view;
    private ArrayList<Meizhi.MeiziItemBean> meiZhi = new ArrayList<>();
    private int page = 0;
    private MeiZhiPresenter meiZhiPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MeiziRcvAdapter adapter;

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
        if (meiZhiPresenter == null) {
            meiZhiPresenter = new MeiZhiPresenter(this);
            meiZhiPresenter.start();
        }


    }


    @Override
    public void initView() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color.holo_blue_bright, android.R.color.holo_purple);


        adapter = new MeiziRcvAdapter(meiZhi, getActivity());
        //mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);


    }


    @Override
    public void showProgress() {
        recyclerView.setLoadingState(true);

        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        recyclerView.setLoadingState(false);

    }


    @Override
    public void showMeiZhiList(ArrayList<Meizhi.MeiziItemBean> newMeizi) {
        meiZhi.clear();
        meiZhi.addAll(newMeizi);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String error) {
        ToastUtil.show(error);
    }

    @Override
    public void navigateToMeiZhiDetail(String url) {


    }

    @Override
    public void setListener() {
        recyclerView.setLoadNextListener(this);
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
    public void autoShowOrHideToolbar(boolean show) {

    }
}

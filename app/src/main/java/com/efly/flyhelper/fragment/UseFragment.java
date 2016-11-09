package com.efly.flyhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efly.flyhelper.R;
import com.efly.flyhelper.adapter.MeiZhiAdapter;
import com.efly.flyhelper.api.APIService;
import com.efly.flyhelper.base.BaseFragment;
import com.efly.flyhelper.bean.Meizhi;
import com.efly.flyhelper.utils.DateStringUtils;
import com.efly.flyhelper.utils.TLog;
import com.efly.flyhelper.view.CustomItemAnimator;
import com.efly.flyhelper.view.PullToRefereshRecyclerView.HeaderViewRecyclerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/7.
 */
public class UseFragment extends BaseFragment {

    private static final String TAG = UseFragment.class.getSimpleName();
    @Bind(R.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private View view;
    private ArrayList<Meizhi.ResultsBean> meiZhi;
    private int page = 0;
    private MeiZhiAdapter constantAdapter;
    private HeaderViewRecyclerAdapter stringAdapter;

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
        initView();
        if (meiZhi == null) {
            initData();
        }
    }

    public void loadMeizhi(String date) {
        swipeRefreshLayout.setRefreshing(true);
        Observable<Meizhi> userObservable = APIService.getMeiZhi(date);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Meizhi>() {
                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.log(e.getMessage().toString());
                        //dismissLoading();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(Meizhi getIpInfoResponse) {
                        TLog.log(getIpInfoResponse.toString());
                        ArrayList<Meizhi.ResultsBean>  meiZhiTemp = (ArrayList<Meizhi.ResultsBean>) getIpInfoResponse.results;
                        if (page>=0){
                            meiZhi = meiZhiTemp;
                        }else {
                            meiZhi.addAll(meiZhiTemp) ;
                        }
                        constantAdapter.setData(meiZhi);
                    }
                });
    }

    private void initData() {
        TLog.logI(DateStringUtils.getBeforeStringDate(page));
        loadMeizhi(DateStringUtils.getBeforeStringDate(page));



    }

    public void initView() {
        constantAdapter = new MeiZhiAdapter(meiZhi, getActivity());


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new CustomItemAnimator());
        stringAdapter = new HeaderViewRecyclerAdapter(constantAdapter);
        recyclerView.setAdapter(constantAdapter);
        createLoadMoreView();
       /* recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                TLog.logI("onLoadMore");
                loadMeizhi(DateStringUtils.getBeforeStringDate(--page));
            }
        });*/

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light,
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

    private void createLoadMoreView() {
        View loadMoreView = LayoutInflater
                .from(getContext())
                .inflate(R.layout.view_load_more, recyclerView, false);
        stringAdapter.addFooterView(loadMoreView);
    }


    void refresh() {

        meiZhi.clear();
        page = 0;
        loadMeizhi(DateStringUtils.getBeforeStringDate(page));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

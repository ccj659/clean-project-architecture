package com.efly.flyhelper.fragment.meizhi;

import com.efly.flyhelper.adapter.base.BaseRecycleAdapter;
import com.efly.flyhelper.api.APIService;
import com.efly.flyhelper.bean.Meizhi;
import com.efly.flyhelper.utils.DateStringUtils;
import com.efly.flyhelper.utils.TLog;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MeiZhiPresenter  implements MeiZhiContract.Presenter {

    private MeiZhiContract.View view;
    private int page = 0;
    private ArrayList<Meizhi.ResultsBean> meiZhi;
    private BaseRecycleAdapter<Meizhi.ResultsBean> constantAdapter;


    public MeiZhiPresenter(MeiZhiContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.initView();
        view.setListener();
        refresh();
    }

    @Override
    public void refresh() {
        loadMeizhi(0);
    }

    @Override
    public void loadMoreMeizhi(int pager) {
        loadMeizhi(pager);
    }

    @Override
    public void loadMeizhi(final int page) {
        view.showProgress();
        if (page>=0&&meiZhi!=null){
            meiZhi.clear();
        }

        String date = DateStringUtils.getBeforeStringDate(page);
        Observable<Meizhi> userObservable = APIService.getMeiZhi(date);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Meizhi>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.log(e.getMessage().toString());
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(Meizhi getIpInfoResponse) {
                        TLog.log(getIpInfoResponse.toString());
                        ArrayList<Meizhi.ResultsBean> meiZhiTemp = (ArrayList<Meizhi.ResultsBean>) getIpInfoResponse.results;

                        if (getIpInfoResponse.error==true){
                            view.showError("请求错误");

                        }

                        if (page >= 0) {
                            meiZhi = meiZhiTemp;
                        } else {
                            meiZhi.addAll(meiZhiTemp);
                        }
                        view.showMeiZhiList(meiZhi);
                        //constantAdapter.setData(meiZhi);
                    }
                });
    }

    @Override
    public void onDestroy() {
        view=null;
    }
}

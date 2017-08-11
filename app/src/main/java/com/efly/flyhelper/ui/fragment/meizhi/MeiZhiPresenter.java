package com.efly.flyhelper.ui.fragment.meizhi;

import com.ccj.base.utils.DateStringUtils;
import com.ccj.base.utils.TLog;
import com.efly.flyhelper.api.MainAPIServiceImp;
import com.efly.flyhelper.bean.Meizhi;

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
    private ArrayList<Meizhi.ResultsBean> meiZhi;


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
        Observable<Meizhi> userObservable = MainAPIServiceImp.getMeiZhi(date);


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
                    }
                });
    }

    @Override
    public void onDestroy() {
        view=null;
    }
}

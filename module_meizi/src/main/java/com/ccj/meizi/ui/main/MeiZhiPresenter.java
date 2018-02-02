package com.ccj.meizi.ui.main;

import com.ccj.base.utils.TLog;
import com.ccj.meizi.api.MeiziAPIServiceImp;
import com.ccj.meizi.bean.Meizhi;

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
    private ArrayList<Meizhi.MeiziItemBean> meiZhi;


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

        Observable<Meizhi> userObservable = MeiziAPIServiceImp.getMeiZhi();


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
                        ArrayList<Meizhi.MeiziItemBean> meiZhiTemp = (ArrayList<Meizhi.MeiziItemBean>) getIpInfoResponse.results;

                        if (getIpInfoResponse.error){
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

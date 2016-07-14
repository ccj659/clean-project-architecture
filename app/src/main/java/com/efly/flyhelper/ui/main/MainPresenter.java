package com.efly.flyhelper.ui.main;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView=mainView;
    }


    @Override
    public void start() {
        mainView.initToolBar();
        mainView.initTabHost();
    }

    @Override
    public void onDestroy() {
        if (mainView!=null){
            mainView=null;
        }
    }
}

package com.efly.flyhelper.ui.main;

import com.efly.flyhelper.ui.main.MainContract;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView=mainView;

    }




    public void setTabHost() {
    }


}

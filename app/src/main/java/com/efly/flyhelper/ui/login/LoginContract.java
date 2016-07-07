package com.efly.flyhelper.ui.login;

import com.efly.flyhelper.base.BasePresenter;
import com.efly.flyhelper.base.BaseView;

/**
 * Created by Administrator on 2016/7/7.
 */
public interface LoginContract {


    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showError(String error);

        void navigateToMain();
        void navigateToRegister();
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
        void onDestroy();
    }
}

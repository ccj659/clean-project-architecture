package com.ccj.login.ui.login;


import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.ccj.base.bean.User;

/**
 * 登录关联接口类
 *
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

    interface Model{
        void saveUserInfo(User user);
        void saveLoginState(Boolean isLogin);
        void saveRememberPass(User user);

    }

}

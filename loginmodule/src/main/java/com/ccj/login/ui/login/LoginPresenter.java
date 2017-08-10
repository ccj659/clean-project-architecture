package com.ccj.login.ui.login;


import com.ccj.base.bean.User;
import com.ccj.base.utils.TLog;
import com.ccj.login.api.LoginAPIServiceImp;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * login的presenter层 进行对view 和 model 的控制,
 * Created by ccj on 2016/7/7.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {
        loginView.showProgress();
        Observable<User> userObservable = LoginAPIServiceImp.userLogin(username, password);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        loginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.log(e.getMessage().toString());
                        loginView.hideProgress();
                        loginView.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(User getIpInfoResponse) {
                        TLog.log(getIpInfoResponse.toString());
                        loginView.navigateToMain();
                    }
                });
    }

    @Override
    public void start() {

    }


    @Override
    public void onDestroy() {
        TLog.log("-->loginPresenter  onDestroy");
    }


}

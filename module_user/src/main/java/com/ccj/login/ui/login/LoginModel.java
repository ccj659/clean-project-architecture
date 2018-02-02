package com.ccj.login.ui.login;


import com.ccj.base.bean.User;

/**
 * Created by Administrator on 2016/7/8.
 *  这里的login 涉及到的业务逻辑比较少
 *  请求网络 采用了rxjava +retroft+gsons
 *  相当于 model层.
 *  如果处理的出具多,就采用此model ,就像图片保存显示等等.
 *
 */



public class LoginModel implements LoginContract.Model {


    interface SaveFinishListener{
        void onError(String msg);
        void onSucess(String msg);
        void onProgress(String msg);
    }

    public LoginModel() {

    }

    public void setOnSaveFinishListener(SaveFinishListener onSaveFinishListener){

    }


    @Override
    public void saveUserInfo(User user) {

    }

    @Override
    public void saveLoginState(Boolean isLogin) {

    }

    @Override
    public void saveRememberPass(User user) {

    }




}

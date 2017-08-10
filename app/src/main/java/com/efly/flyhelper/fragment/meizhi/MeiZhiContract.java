package com.efly.flyhelper.fragment.meizhi;


import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.ccj.base.bean.User;
import com.efly.flyhelper.bean.Meizhi;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MeiZhiContract {

    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showMeiZhiList(ArrayList<Meizhi.ResultsBean> meizhiList);
        void showError(String error);
        void navigateToMeiZhiDetail(String url);
        void setListener();
    }

    interface Presenter extends BasePresenter {
        void loadMeizhi(int pager);
        void loadMoreMeizhi(int pager);
        void  refresh();
    }

    interface Model{
        void saveUserInfo(User user);
        void saveLoginState(Boolean isLogin);
        void saveRememberPass(User user);

    }


}

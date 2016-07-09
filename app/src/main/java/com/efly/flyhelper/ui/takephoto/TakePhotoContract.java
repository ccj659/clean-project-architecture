package com.efly.flyhelper.ui.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;

import com.efly.flyhelper.base.BasePresenter;
import com.efly.flyhelper.base.BaseView;

/**
 * 业务联系接口类 面向接口编程 进行解耦 内聚
 * 参照传统的google_mvp
 * Created by ccj on 2016/7/8.
 */
public interface TakePhotoContract {

    interface View extends BaseView{
        void initView();
        void showProgress();
        void hideProgress();
        void showBitmap(Bitmap bitmap);

    }
    interface Presenter extends BasePresenter{
        void savePhoto(Intent data);
    }

    interface Model {
        void savePhoto(Intent data);
    }
}

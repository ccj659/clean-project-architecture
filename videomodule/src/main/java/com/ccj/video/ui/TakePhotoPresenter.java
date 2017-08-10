package com.ccj.video.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.ccj.base.utils.eventbus.EventUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ccj on 2016/7/8.
 */
public class TakePhotoPresenter implements TakePhotoContract.Presenter {

    private static final String TAG ="Tlog-->" ;
    private TakePhotoContract.View view;
    private TakePhotoModel takePhotoModel;

    /**
     * 在activity或fragment中初始化presenter
     * @param view
     */
    public TakePhotoPresenter(TakePhotoContract.View view) {
        this.view = view;
        takePhotoModel = new TakePhotoModel();
        EventBus.getDefault().register(this);
    }

    /**
     * 实现保存图片业务的方法
     * @param data
     */
    @Override
    public void savePhoto(Intent data) {
        view.showProgress();
        takePhotoModel.savePhoto(data);

    }

    /**
     * presenter 初始化
     */
    @Override
    public void start() {
        view.initView();
    }

    @Override
    public void onDestroy() {
        if (view!=null){
            view=null;
        }
        EventBus.getDefault().unregister(this);
    }


/***********************************下方的订阅也可以放在activity中订阅,注意首先注册订阅****************************************/


    /**
     * 无论发布者在那个线程,在主线程中订阅
     * @param event
     */
    @Subscribe//(threadMode = ThreadMode.MAIN)
    public void onEvent(EventUtils.ObjectEvent event) {
        Log.e(TAG, "onEventMainThread getObjectEvent" + event.getMsg());
        Bitmap bitmap=(Bitmap)event.getMsg();
        view.showBitmap(bitmap);
        view.hideProgress();
    }


    /**
     * 和发布者在同一线程订阅
     * @param event
     */

   /* public void  onEvent(EventUtils.ObjectEvent event){
        Log.e(TAG, "getObjectEvent" + event.getMsg());
    }
*/


}

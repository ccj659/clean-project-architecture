package com.efly.flyhelper.ui.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.efly.flyhelper.utils.EventUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by ccj on 2016/7/8.
 */
public class TakePhotoPresenter implements TakePhotoContract.Presenter {

    private static final String TAG ="Tlog-->" ;
    private TakePhotoContract.View view;
    private TakePhotoModel takePhotoModel;
    private Bitmap bitmap;

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
        Log.e(TAG, "event" + data.getData().toString());

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
    public void onEventMainThread(EventUtils.ObjectEvent event) {
        Log.e(TAG, "onEventMainThread getObjectEvent" + event.getMsg());
        Bitmap bitmap=(Bitmap)event.getMsg();
        view.showBitmap(bitmap);
        view.hideProgress();
    }


    /**
     * 和发布者在同一线程订阅
     * @param event
     */

    public void  onEvent(EventUtils.ObjectEvent event){
        Log.e(TAG, "getObjectEvent" + event.getMsg());
    }



}

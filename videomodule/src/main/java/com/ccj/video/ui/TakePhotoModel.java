package com.ccj.video.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.ccj.base.base.BaseApplication;
import com.ccj.base.utils.BitmapUtil;
import com.ccj.base.utils.eventbus.EventUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/8.
 */
public class TakePhotoModel implements TakePhotoContract.Model {
    private Bitmap bitmap = null;

    private Observable<String> saveObservable;

    /**
     * rxjava 进行异步操作 eventBus进行时间传递
     *
     * @param data
     */
    @Override
    public void savePhoto(final Intent data) {
        saveObservable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {//通知调用  并返回string
                return savePic(data);//此方法在io线程中调用 并返回
            }
        });


        saveObservable.subscribeOn(Schedulers.io())//observable在调度中的IO线程中进行调度进行
                .observeOn(AndroidSchedulers.mainThread())//在主线程中进行观察
                .subscribe(new Observer<String>() { //订阅观察者
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        EventBus.getDefault().post(new EventUtils.ObjectEvent(e.getMessage().toString()));
                    }

                    @Override
                    public void onNext(String s) {//带参数的下一步,在此就是当
                        EventBus.getDefault().post(new EventUtils.ObjectEvent(bitmap));

                    }
                });


    }

    private String savePic(Intent data) {
        if (data == null || data.getData() == null) {
            return null;
        }
        Uri selectedImage = data.getData();
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            Bitmap temp = BitmapFactory
                    .decodeStream(BaseApplication.getContext().getContentResolver().openInputStream(selectedImage), null, opt);

            bitmap = BitmapUtil.comp(temp);//图片压缩
            return "ok";
        } catch (FileNotFoundException e) {
            Log.e("cameraBitmap", "-----FileNotFoundException---" + selectedImage.toString());

        } catch (NullPointerException e) {
            Log.e("cameraBitmap", "-----NullPointerException---" + selectedImage.toString());
        }
        return null;
    }


    @Override
    public void start() {


    }

    @Override
    public void onDestroy() {
        saveObservable=null;

    }
}

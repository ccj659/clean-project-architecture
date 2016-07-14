package com.efly.flyhelper.ui.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.efly.flyhelper.base.BaseApplication;
import com.efly.flyhelper.utils.BitmapUtil;
import com.efly.flyhelper.utils.EventUtils;
import com.efly.flyhelper.utils.TLog;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/8.
 */
public class TakePhotoModel implements TakePhotoContract.Model {
    private Bitmap bitmap = null;

    private Observable<String> saveObservable;
    private Subscription saveSubscription;

    /**
     * rxjava 进行异步操作 eventBus进行时间传递
     *
     * @param data
     */
    @Override
    public void savePhoto(final Intent data) {
        TLog.log("savePhoto", "data-->" + data.getData().toString());
        Log.e("Tlog-->", "data-->" + data.getData().toString());
        saveObservable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {//通知调用  并返回string
                return savePic(data);//此方法在io线程中调用 并返回
            }
        });

        saveSubscription = saveObservable
                .subscribeOn(Schedulers.io())//observable在调度中的IO线程中进行调度进行
                .observeOn(AndroidSchedulers.mainThread())//在主线程中进行观察
                .subscribe(new Observer<String>() { //订阅观察者
                    @Override
                    public void onCompleted() {
                        Log.e("Tlog-->", "onCompleted-->");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Tlog-->", "Throwable-->" + e.getMessage().toString());
                        EventBus.getDefault().post(new EventUtils.ObjectEvent(e.getMessage().toString()));
                    }

                    @Override
                    public void onNext(String s) {//带参数的下一步,在此就是当
                        Log.e("Tlog-->", "s-->" + s);
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
            bitmap = BitmapUtil.getInstance().comp(temp);//图片压缩
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


    }
}

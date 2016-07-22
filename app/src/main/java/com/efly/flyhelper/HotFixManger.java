package com.efly.flyhelper;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.dodola.rocoofix.RocooFix;
import com.efly.flyhelper.utils.EventUtils;

import java.io.File;
import java.util.concurrent.Callable;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 热修复管理类,热修复采用Rocoofix(andfix和nvwa补强版,而且正在反馈更新)
 * Created by ccj on 2016/7/22.
 */

public class HotFixManger {

    private static final String TAG = "HotFixManger-->";
    private static String dexPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/patch.jar");
    private static Observable<String> saveObservable;
    private static Subscription saveSubscription;
    private boolean NeedRepaire;

    public static void init(Context context) {
        try {
            RocooFix.init(context);
           // installPach(context);
            downLoadPatch(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void installPach(Context context) {
        if (!TextUtils.isEmpty(dexPath)) {
            RocooFix.applyPatch(context, dexPath);
            System.out.println(" installPach" + dexPath);
        } else {
            System.out.println(" no  installPach");
        }
    }





    private static void downLoadPatch(final Context context){
        Log.e(TAG, "DownLoadPatch-->" );
        saveObservable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {//通知调用  并返回string
                return requestHttp();//此方法在io线程中调用 并返回
            }
        });

        saveSubscription = saveObservable
                .subscribeOn(Schedulers.io())//observable在调度中的IO线程中进行调度进行
                .observeOn(AndroidSchedulers.mainThread())//在主线程中进行观察
                .subscribe(new Observer<String>() { //订阅观察者
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted-->");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Throwable-->" + e.getMessage().toString());
                        EventBus.getDefault().post(new EventUtils.ObjectEvent(e.getMessage().toString()));
                    }

                    @Override
                    public void onNext(String s) {//带参数的下一步,在此就是当
                        Log.e(TAG, "s-->" + s);
                        installPach(context);
                    }
                });


    }

    private static String requestHttp() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "ok";
    }


    public boolean checkBugs(Context context){

        if (NeedRepaire){
            Log.e(TAG, "noNeedRepaire-->" );
            deletePatch();
        }else {
            Log.e(TAG, "RocooFix.loadPatch-->" );
            RocooFix.applyPatch(context, dexPath);
        }
        return true;
    }

    private boolean deletePatch() {
        File file=new File(dexPath);
        if (!file.exists()) {
            Log.e(TAG, dexPath + " is null");
            return true;
        }else {
            file.delete();
            Log.e(TAG, dexPath + " is delete");
        }

        return false;
    }




}

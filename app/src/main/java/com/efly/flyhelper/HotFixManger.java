package com.efly.flyhelper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.dodola.rocoofix.RocooFix;
import com.efly.flyhelper.api.APIService;
import com.efly.flyhelper.bean.Patch;
import com.efly.flyhelper.utils.EventUtils;
import com.efly.flyhelper.utils.TLog;

import java.io.File;
import java.util.concurrent.Callable;

import dalvik.system.BaseDexClassLoader;
import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 热修复管理类,热修复采用Rocoofix(andfix和nvwa补强版,而且正在反馈更新)
 * 此处的patch,为了安全 可以进行md5加密,在此,不做描述.
 * 1.在实际项目中, 进入app 请求服务器,看是否有补丁,以及补丁的版本,
 * 2.然后根据版本进行判断, 下载 调用 RocooFix.applyPatch(context, dexPath);
 * 3.同时 记录版本信息, 保存到 sharepreference, 然后悄悄的进行,
 * 4.下次启动再请求网络,得到数据,根据数据判断补丁版本是否对应, 然后进行上述操作~,
 * Created by ccj on 2016/7/22.
 */

public class HotFixManger {
    private static final String TAG = "HotFixManger-->";
    private static String dexPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/patch.jar");
    private static Observable<String> saveObservable;
    private static Subscription saveSubscription;
    ClassLoader classLoader;
    BaseDexClassLoader baseDexClassLoader;

    public static void init(Context context) {
        try {
            RocooFix.init(context);
            // installPach(context);
            downLoadPatch(context);//模拟下载
            //getPatch(context);需自己配置请求,以及返回参数处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 模拟过程
     *
     * @param context
     */
    private static void downLoadPatch(final Context context) {
        Log.e(TAG, "DownLoadPatch-->");
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

    private static void installPach(Context context) {
        if (!TextUtils.isEmpty(dexPath)) {
            RocooFix.applyPatch(context, dexPath);
            System.out.println(" installPach" + dexPath);
        } else {
            System.out.println(" no  installPach");
        }
    }

    /**
     * 模拟下载过程,请将patch,放在储存卡的根目录下
     *
     * @return
     */

    private static String requestHttp() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 结合retrofit访问网络, 需自己配置请求,以及返回参数处理
     * 例如返回file和code 如果code!=200,就删除patch;反之,就下载到储存卡根目录.然后installPach即可
     *  此次采用 静态修复,即下次启动生效
     * @param context
     */
    private static void getPatch(final Context context) {
        Observable<Patch> userObservable = APIService
                .getPatch(getVersionCode(context) + "");
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Patch>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.log(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Patch getInfoResponse) {
                        TLog.log(getInfoResponse.toString());
                       //todo logic

                        if(getInfoResponse.code==200){
                            //将补丁下载到目录dexPath
                        }else {
                            //deletePatch
                        }

                    }
                });


    }

    private boolean deletePatch() {
        File file = new File(dexPath);
        if (!file.exists()) {
            Log.e(TAG, dexPath + " is null");
            return true;
        } else {
            file.delete();
            Log.e(TAG, dexPath + " is delete");
        }
        return false;
    }

    public void onDestroy() {

    }


}

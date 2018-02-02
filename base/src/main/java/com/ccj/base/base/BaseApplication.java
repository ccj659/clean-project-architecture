package com.ccj.base.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.utils.SharedPreferenceUtil;

/**
 * 获取上下文,Toast,以及各种初始化
 * Created by Administrator on 2016/7/5.
 */
public class BaseApplication extends Application {

    private static String lastToast = "";
    private static long lastToastTime;
    private static Context context;
    private static Resources resource;
    private static BaseApplication baseApplication;

    public static synchronized BaseApplication getInstance() {
        return baseApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
        context = getBaseContext();
        baseApplication = this;
        SharedPreferenceUtil.initSharedPreference(getApplicationContext());
    }

    /**
     * ARouter 在每个模式下都需要,此时,
     * 由于每个module的application只有在module模式下才启用,所以可以这样设置-->
     * 可以将各module都继承BaseApplication
     */
    private void initARouter() {
        //if (BuildConfig.DEBUG) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.printStackTrace(); // 打印日志的时候打印线程堆栈
       // }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }


    /**
     * 防抖动 弹窗
     *
     * @param message
     * @param duration
     * @param icon
     * @param gravity
     */
    public static void showToast(String message, int duration, int icon,
                                 int gravity) {
       Toast.makeText(getContext(),message,duration).show();
    }




    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        BaseApplication.context = context;
    }

    public static Resources getResource() {
        return resource;
    }

    public static void setResource(Resources resource) {
        BaseApplication.resource = resource;
    }


    public static void showShortToast(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

}

package com.ccj.base.utils.router;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;

//https://github.com/alibaba/ARouter
public class RouterUtils {



    public static void inject(Object obj){
        ARouter.getInstance().inject(obj);

    }

    /**
     * 跳转
     * 得到 跳转对象
     * @param path
     * @return
     */
    public static Object navigation(String path) {
        // 构建标准的路由请求
        return  ARouter.getInstance().build(path).navigation();
    }



    public void startActivityForResult(Activity activity,String path,int requestCode,String argKey,Object argValue) {
        ARouter.getInstance().build(path).
                withObject(argKey, argValue).
                navigation(activity, requestCode);
    }

    public void startActivityForCallback() {
// 使用两个参数的navigation方法，可以获取单次跳转的结果
        //ARouter.getInstance().build("/test/1").navigation(this, new NavigationCallback() );
    }


}



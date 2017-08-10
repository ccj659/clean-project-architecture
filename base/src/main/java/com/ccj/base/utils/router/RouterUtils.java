package com.ccj.base.utils.router;

import com.alibaba.android.arouter.launcher.ARouter;

//https://github.com/alibaba/ARouter
public class RouterUtils {



    public static Object navigation(String path) {
        // 构建标准的路由请求
        return  ARouter.getInstance().build(path).navigation();
    }



    public void startActivityForResult() {
        //ARouter.getInstance().build("/home/main").navigation(this, 5);

    }

    public void startActivityForCallback() {
// 使用两个参数的navigation方法，可以获取单次跳转的结果
        //ARouter.getInstance().build("/test/1").navigation(this, new NavigationCallback() );
    }
}

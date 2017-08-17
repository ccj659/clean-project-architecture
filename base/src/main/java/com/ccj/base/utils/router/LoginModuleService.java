package com.ccj.base.utils.router;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * 示例:子模块间调用方法
 * Created by chenchangjun on 17/8/14.
 */

public interface LoginModuleService extends IProvider {


     boolean checkLoginState();

}

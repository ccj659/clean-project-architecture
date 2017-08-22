# Android 组件化

项目地址[https://github.com/ccj659/clean-project-architecture](https://github.com/ccj659/clean-project-architecture) 


## 前言

随着业务的增多,迭代版本的增加, 

模块化开发, 业务解耦, 业务独立进行测试,编译,运行,想想都惊喜~

如果不想忍受超长的编译时间,不想忍受类之间的强耦合,受够了满屏的不相干的文件,那么.....

为了你的"代码洁癖",还有项目的未来, 组件化, 势在必行.....




## Android 业务组件化

项目地址[https://github.com/ccj659/clean-project-architecture](https://github.com/ccj659/clean-project-architecture) 

类似于UML类图中聚合的概念,如下图所示,

![image.png](http://upload-images.jianshu.io/upload_images/1848340-0df202dfbe34f43f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

组件可以自己行动,也可以组成一个整体运行.
关于基础组件,可以同时放在一个base包下, 也可以将base分不同的包,比如 数据库lib, 资源lib,等等...这些其实可以自己定义.
### 优点
便于开发，团队成员只关注自己的开发的小模块，降低耦合性，后期维护方便等。各自可以按照自己的代码风格开发，最后组装，成一个 app。

每个模块都可以打包成一个带版本号的@aar,对业务进行版本控制,降低了修改某一个业务造成其他业务受影响的风险.

与插件化的风险比较,组件化是几乎没有风险的,当下就可以做的一种架构. 


### 不足

在模块间 数据交换,相互依赖,可能存在难题,路由器模块还不太成熟,问题各不相同,需要各自解决.


## SHOW
![](http://upload-images.jianshu.io/upload_images/1848340-dc1f5ec9d6fcfb07.gif?imageMogr2/auto-orient/strip)



## 模块化 会遇到如下问题

1.libarary和applicaiton 之间的转换

2.路由器,如何在拿不到类名的情况下,启动,模块间相互吊起服务. 最近路由器很多~
目前的路由有[阿里巴巴的](https://github.com/alibaba/ARouter),
还有[mzule的ActivityRouter](https://github.com/mzule/ActivityRouter).

3.代码解耦,作为线程间交互桥梁. 我用的是eventBus,作为事件总线,代替handler,

4.在集合app的最后,将每个模块打包成aar,减少编译时间. 
5. 每个业务module有自己的版本号,分别release 到代码控制器中, 供主app使用.


## 组件化的构建步骤
请参照[https://github.com/ccj659/clean-project-architecture](https://github.com/ccj659/clean-project-architecture) 项目


![image.png](http://upload-images.jianshu.io/upload_images/1848340-7b563b6a628c82ae.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 1. 模块开发模式切换
1. 在`gradle.properties` 增加一个变量

```
# true代表模块开发,false代表合并到主app.
#模式切换开关
isModule=false

```

2. 在每个业务module的`build.gradle`里面添加

```
//根据isModule值进行切换 是否为lib或者app
if (isModule.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```

3. 建立两个`AndroidManifest.xml`,进行切换

大家都知道,当项目是APPlication时候,需要有category为`LAUNCHER`的入口activity.
而当项目是lib的时候,不能存在入口activity.所以要分别建立两套`AndroidManifest.xml `,还要注意,如果想要保持主题样式通用, 主app项目下的`theme`,'ico','label'等等,在module中都不能存在.
进行如下配置.
![image.png](http://upload-images.jianshu.io/upload_images/1848340-92b4a7213a9ad8d1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


4. 建立两个`AndroidManifest.xml`,进行切换
```
    sourceSets {
        main {
            if (isModule.toBoolean()) {
                manifest.srcFile 'src/main/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/release/AndroidManifest.xml'
                java {
                    //release 时 debug 目录下文件不需要合并到主工程
                    exclude '**/debug/**'
                }
            }
        }
    }
```

5. 业务组件不需要混淆代码.
一旦业务组件的代码被混淆，而这时候代码中又出现了bug，将很难根据日志找出导致bug的原因；


6. 当ismodule开关为true时,每个module可独立运行.

![image.png](http://upload-images.jianshu.io/upload_images/1848340-ddb00f60544243a3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 2. 数据路由

首先,考虑到解耦, 可以把ARouter的所有跳转都进行封装.以防以后更换路由器. 

这里,module内,可以用传统方式传递和用路由器传递都行.


## 3. 资源重复
1. module1和module2都依赖base,则gradle在编译期间,会自动去重,我们不需要管.
2. 资源名重复,解决1:在编码的时候 添加`resourcePrefix  "video"+"_"`,强制人员添加前缀(但是对drawable不支持,需用户自己增加前缀).
3.  解决2:将资源统一放在一个module(比如Base中),但是编译会增加时间.(不太复合资源解耦 原则)

4. 如果是用provide代替complile



## 4. 代码隔离-(面向接口编程)

###  基础库`Base`

1. 网络库 我们项目用的volley(10年开始的项目迭代至今),
2. 资源库 基础mipmap,drawable资源等等
3. BaseClass BaseActivity,BaseBean,BaseAdapter等等.
4. weight组件 共同的自定义view,或者第三方view.
		....
		

### 组件`Module`
####  组件间通信 `IProvider`
请参考[ARouter的文档的](https://github.com/alibaba/ARouter)`通过依赖注入解耦:服务管理(一) 暴露服务
` 进行~


举个例子, 在我的项目中[clean-project-architecture](https://github.com/ccj659/clean-project-architecture)中,`videoModule的拍照功能`需要调用`loginmodule的登录功能`,按照上述例子,就可以实现.



```
// 声明接口,其他组件通过接口来调用服务
/**
 * 示例:子模块间调用方法
 * Created by chenchangjun on 17/8/14.
 */

public interface LoginModuleService extends IProvider {


     boolean checkLoginState();

}


// 实现接口
/**
 *     * 实现接口,
 * Created by chenchangjun on 17/8/14.
 */
@Route(path = RouterConstants. LOGIN_SERVICE_IMPL)
public class CheckLoginService implements LoginModuleService{

    /**
     * 实例化服务,面向接口编程
     * @return
     */
    @Override
    public boolean checkLoginState() {
        //可自行在loginModule
        return false;
    }

    @Override
    public void init(Context context) {

    }
}


//另外一个module调用,(由接口进行隔离)
    private void takePhoto() {
        if (loginModuleService.checkLoginState()){ //模拟模块间通信,调用登录服务:如果登录就开始下一步.
            startTakePhoto();
        }else {
            Toast.makeText(this,"请登录",Toast.LENGTH_SHORT).show();
        }
    }


```




### 宿主`App`
每个module都有`Application`	,这里,为了方便, 将共同的东西抽取出来,放在了`basemodule`的BaseApplicaiotn中.
当遇到每个module可能都要有自己初始化的方法,我们可以在每个module 附带一个application.
		
		





# 遇到问题

###  1.Butterknife 的bindview()方法,library的不能存在,原因是在app和library切换的时候,注解上的变量必须是`static final`, library不能存在`switch()`.
[Butterknigher  libarary不能用,这篇文章不错](http://www.jianshu.com/p/82da8c26cc60)

**问题:**
![image.png](http://upload-images.jianshu.io/upload_images/1848340-cfa528693d699a0e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**解决**

1. 上述问题可以,将R改为R2如上图第二个变量所示.重新clean,即可.

2. butterknife的onclick事件,用下面的方式处理即可.

3. 注解问题,就像用dragger.xutils等等,能不能这种方式处理,还有待测试.


```
//package com.ccj.login.ui.login;

  /** 
     * click方法中同样使用R2，但是找id的时候使用R，
     * ibrary中是不能使用 switch- case 找id的，原因：http://www.jianshu.com/p/89687f618837
     */
    @OnClick({R2.id.iv_cancel, R2.id.btn_login, R2.id.btn_register})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_cancel) {
            finish();
        } else if (i == R.id.btn_login) {
            //mPresenter.login(tvPhone.getText().toString(), tvPassword.getText().toString());
            Toast.makeText(this,"登录测试",Toast.LENGTH_SHORT).show();
        } else if (i == R.id.btn_register) {
            navigateToRegister();
        }
    }

```



### 2.目前,路由器ARouter 没有解决`onActivityResult`的fragment分发问题.
**问题**:

当你再fragment上 进行路由

```
  ARouter.getInstance().
                build(RouterConstants.VIDEO_MUDULE_ACTIVITY).
                withString(Constants.START_LOGIN_WITH_PARAMS, "I am params from MainActivity").
                navigation();

```
在fragment中的`onActivityResult`是接收不到数据的,ARouter会在activity中调用该方法.


**解决**

在BaseActivity中重写`onActivityResult `方法.让子类继承即可.

```
 /**
     * 解决fragment onActivityResult不调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager fm = getSupportFragmentManager();
        //if (index != 0) {
        if (fm.getFragments() == null) {
            Log.w(TAG, "Activity result fragment fragmentIndex out of range: 0x"
                    + Integer.toHexString(requestCode));
            return;
        }
        for (int i = 0; i <fm.getFragments().size() ; i++) {
            Fragment frag = fm.getFragments().get(i);
            if (frag == null) {
                Log.w(TAG, "Activity result no fragment exists for fragmentIndex: 0x"
                        + Integer.toHexString(requestCode));
            } else {
                handleResult(frag, requestCode, resultCode, data);
            }
        }
        return;
        //}

    }

    /**
     * 递归调用，对所有子Fragement生效
     *
     * @param frag
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }
    
```

###3 各种编译处理插件,可能会出现问题
因为Arouter是编译期间 执行,所以当你的项目集成`dragger2,butterknife,xutils,databinding`可能会出现问题.需要各自排查



## 总结
组件化是用gradle作为组间切换工具,用Arouter作为跳转路由器 的一种 框架.

在开发中, 组件化,有利于模块业务解耦,让每人负责的业务相互独立.
在后续开发中,我们可以将不同的组件模块lib分别独立,需要的时候分别进行依赖即可.

相关代码实现请查看项目
[https://github.com/ccj659/clean-project-architecture](https://github.com/ccj659/clean-project-architecture) 




## 参考:



 [ARouter 类似于Spring的控制反转IOC.路由分发](https://github.com/alibaba/ARouter)


[创建 Android 库](https://developer.android.com/studio/projects/android-library.html?hl=zh-cn#CreateLibrary)


[http://blog.csdn.net/guiying712/article/details/55213884](http://blog.csdn.net/guiying712/article/details/55213884)

[吴小龙 Android 组件化探索与思考](http://wuxiaolong.me/2017/08/01/ModularExploree/)

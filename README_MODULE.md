# 客户端组件化探究

**项目地址** [https://github.com/ccj659/clean-project-architecture](https://github.com/ccj659/clean-project-architecture)



## 1.组件化的产生背景

随着业务的增多,迭代版本的增加,

模块化开发, 业务解耦, 业务独立进行测试,编译,运行,想想都惊喜~

如果不想忍受超长的编译时间,不想忍受类之间的强耦合,受够了满屏的不相干的文件,那么.....

为了你的"代码洁癖",还有项目的未来, 组件化, 势在必行.....


## 2.普通开发模式的弊端

1、实际业务变化非常快，但是单一工程的业务模块耦合度太高，牵一发而动全身,每次改一个地方都很小心.

2、对工程所做的任何修改都必须要编译整个工程；

3、团队协同开发存在较多的冲突.不得不花费更多的时间去沟通和协调，并且在开发过程中，任何一位成员没办法专注于自己的功能点，影响开发效率；

4、不能灵活的对业务模块进行配置和组装；



## 3.什么是组件化


	组件化是 编程思想"高内聚,低耦合"的一种体现, 是 业务独立化, 粒度最小化,可移植的功能模块.



1.页面上的每个 独立的 可视/可交互区域视为一个组件;

2.每个组件对应一个工程目录，组件所需的各种资源都在这个目录下就近维护;

3.每个组件相对独立，页面只不过是组件的容器，组件自由组合形成功能完整的界面;

4.当不需要某个组件，或者想要替换组件时，可以整个目录删除/替换。







## 4.组件化会解决 目前项目中哪些问题?

1.急需解决 项目编译时间过长问题!

2.急需团队开发,解决提交代码 牵一发动全身的问题!

3.解决开发效率低下问题.

4.解决项目越来越臃肿,分层不明确,,难以维护问题.

5.减少 学习成本.




## 5.项目如何进行组件化?





**在框架中的 项目组件化是这样的~**

![未命名文件 (6).png](http://upload-images.jianshu.io/upload_images/1848340-4011e008d6533cc2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**反映在代码目录结构上,就是如下所示~**

![image.png](http://upload-images.jianshu.io/upload_images/1848340-1681c9f78b3f90c9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**根据上述结构图,我对目前的项目做了如下调整.**

1.将项目的业务模块(主页,好价,好文,好物,个人中心,关注),作为独立的application module. 每一位业务的开发人员,只需要管理自己的module即可,避免直接或者间接修改他人代码导致的问题.而且,开发期间,自住选择必要中间件的依赖,自住开发风格(MVP,MVC,etc).

2.将中间组件(通用list_item组件,分享组件,推送组件,内置浏览器组件,通用view组件,以及通用跳转等等),作为library module进行选择性依赖.

3.将底层库(网络请求库,db库,File库,图片库等等),作为一个底层library库,独立进行版本控制,用@aar-v1.0.1 来引用,这样可以防止,他人的随意改动,提高版本切换之间的稳定性.


## 6.问题五连


#### Q1.如何将工程拆分成有机的整体,组件单独运行？

利用Android studio 自带的gradle 来管理. 我们可以使用grovxy脚本,来对 是否是组件状态进行切换.

![image.png](http://upload-images.jianshu.io/upload_images/1848340-46ab2ef7c01244f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


####  Q2.如何分别执行独立运行,合并运行的代码?
当项目是APPlication时候,需要有category为LAUNCHER的入口activity.
当项目是lib的时候,不能存在入口activity.所以要分别建立两套配置.

还要注意,如果想要保持主题样式通用, 主app下,’theme’'ico','label'等等,在module中都不能存在.

![image.png](http://upload-images.jianshu.io/upload_images/1848340-a531f85dd9d77563.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

####  Q3.代码都分离了,如何进行带参跳转,相互调用?
首先,考虑到解耦, 可以把ARouter的所有跳转都进行封装.以后待项目成熟,我会用自己的Router.
	另外,module内,可以用传统方式传递和用路由器传递都行.
![image.png](http://upload-images.jianshu.io/upload_images/1848340-8bf31d3c74218ad9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



####  Q4.是啊,分业务是好,但是代码合并后,如果避免存在资源名冲突?

1.第三方依赖的问题,依赖尽量放在base中.这样,gradle会自动去重.


2.资源名重复,在编码的时候 添加resourcePrefix "video"+"_",强制人员添加前缀(但是对drawable不支持,需用户自己增加前缀).

3.将资源统一放在一个module(比如Base中),但是编译会增加时间.(不太复合资源解耦 原则)

####  Q5.代码那么多,把之前的都解耦了,如何避免后期继续耦合?

针对这个问题, 组件之间必须针对接口编程,杜绝面向实现编程.
另外, 我们在设计一个组件的时候, 要尽量用继承封装多态去解决问题.




## 7.如何实现



**1.在`gradle.properties` 增加一个变量**

```
# true代表模块开发,false代表合并到主app.
#模式切换开关
isModule=false

```

**2.在每个业务module的`build.gradle`里面添加**

```
//根据isModule值进行切换 是否为lib或者app
if (isModule.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```

**3.建立两个`AndroidManifest.xml`,进行切换**

大家都知道,当项目是APPlication时候,需要有category为`LAUNCHER`的入口activity.
而当项目是lib的时候,不能存在入口activity.所以要分别建立两套`AndroidManifest.xml `,还要注意,如果想要保持主题样式通用, 主app项目下的`theme`,'ico','label'等等,在module中都不能存在.
进行如下配置.
![image.png](http://upload-images.jianshu.io/upload_images/1848340-92b4a7213a9ad8d1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**4.建立两个`AndroidManifest.xml`,进行切换**

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

**5.业务组件不需要混淆代码.**
一旦业务组件的代码被混淆，而这时候代码中又出现了bug，将很难根据日志找出导致bug的原因；


**6.当ismodule开关为true时,每个module可独立运行.**

![image.png](http://upload-images.jianshu.io/upload_images/1848340-ddb00f60544243a3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



## 应用效果


![compone1.gif](http://upload-images.jianshu.io/upload_images/1848340-a3af30d73a24fdaa.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




![compone2.gif](http://upload-images.jianshu.io/upload_images/1848340-06e31929d2927460.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




#总结


## 优点

1.业务module独立,降低业务的学习成本.

2.模块解耦, 代码架构更加清晰，降低项目的维护难度.

3.随着项目的代码增加,单独编译,单独运行,明显减少代码编译时间.

4.适合于团队开发.


## 不足


1.switch中 case必须是 静态常亮,所以在引用  library中 的 资源(R.id.button) 时, 需要改为if else. 这个还需要找到解决方案...


2.模块间 数据交互,比较繁琐

3.路由器模块还不太成熟

4.实际开展过程,抽丝剥茧,过程会很痛苦.

5.还存在潜在问题.

## 后期计划

 1.提高编译速度, 将 依赖 由 整个项目进行编译, 变为依赖 aar包, 减少编译时间.

 2.搭建一个私有的maven仓库，将我们开发好的组件上传到这个私有的maven仓库上，然后内部开发人员就可以像引用三方库那样轻而易举的将组件引入到项目中

3. 完善复合项目的事件路由Router,



## 参考

 [aar的使用](http://blog.csdn.net/zxw136511485/article/details/52777286)

 [http://blog.csdn.net/guiying712/article/details/55213884](http://blog.csdn.net/guiying712/article/details/55213884)

 [Android彻底组件化demo发布](https://www.jianshu.com/p/59822a7b2fad)
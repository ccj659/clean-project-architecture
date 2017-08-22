





2016/11/1 更新 用gank.io的妹子数据,准备开发一个完整app

	1.添加get请求.

	2.完成妹子Meterial Design 风格的list界面.


参考界面:
![](http://i.imgur.com/EnBxczU.gif)

-------
2016/7/23 更新  热修复相关

csdn: http://blog.csdn.net/ccj659/article/details/52004522
简书: http://www.jianshu.com/p/2301d40dbb33

[热修复]--源码级分析以及项目实践 谢谢大家指正~

----------


android架构篇
===================
mvp+rxjava+retrofit+eventBus
===================
----------

高层不应该知道低层的细节，应该是面向抽象的编程。业务的实现交给实现的接口的类。高层只负责调用。

----------
首先,要介绍一下一个项目中好架构的好处:好的软件设计必须能够帮助开发者发展和扩充解决方案，保持代码清晰健壮，并且可扩展，易于维护，而不必每件事都重写代码。面对软件存在的问题，必须遵守SOLID原则(面向对象五大原则)，不要过度工程化，尽可能降低框架中模块的依赖性。

----------
之前的一段时间,学习了一些新的技术,并把自己关注的技术整合了一下,是的,相似的技术有很多,自己择优选择,将它们的思想和技术应用到了自己的搭建的项目框架中.
限于自己能力水平有限,自己搭建的项目可能还有些不足,欢迎大家指正批评,让自己的想法和设计思想走向正轨.O(∩_∩)O谢谢~

在框架中
-------------
 *1.**项目整体框架: 利用google-clean-architecture的思想 来负责项目的整体MVP架构.***

 - MVP是模型（Model）、视图（View）、主持人（Presenter）的缩写，分别代表项目中3个不同的模块。**我以登录为例子,进行说明.**

![这里写图片描述](http://img.blog.csdn.net/20160712153716629)

  这里每个业务首先要有一个管理接口Contract,在这里面有三个接口来面向接口编程, （Model）,（View）,（Presenter）. 将三个接口放在一起便于管理.

![这里写图片描述](http://img.blog.csdn.net/20160712153741743)
```java
   /**
 * 登录关联接口类
 *
 * Created by ccj on 2016/7/7.
 */
public interface LoginContract {
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void navigateToMain();
        void navigateToRegister();
    }
    interface Presenter extends BasePresenter {
        void login(String username, String password);
        void onDestroy();
    }
    interface Model{
        void saveUserInfo(User user);
        void saveLoginState(Boolean isLogin);
        void saveRememberPass(User user);

    }

}
```
 - **模型（Model）：实现 implements LoginContract.Model** 负责处理数据的加载或者存储，比如从网络或本地数据库获取数据等；**这里的login 涉及到的业务逻辑比较少请求网络 采用了rxjava +retroft+gsons 相当于 model层. 如果处理的出具多,就采用此model ,就像图片保存显示等等.**

 - **视图（View）：采用接口的方式,让activity实现该接口,接口中有关于视图的方法,例如”initVIew()”,”showDialog()”,”hideDialog()”等等, 负责界面数据的展示，与用户进行交互；**
 ```java
 public class LoginActivity extends BaseActivity implements LoginContract.View {

  //省略bufferknife 注解
    private LoginPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter=new LoginPresenter(this);
        presenter.start();//初始化控制层
    }

	//实现于view的方法
    @Override
    public void navigateToMain() {
        Intent intent =new Intent(getBaseContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

 ```
 - **主持人（Presenter）：持有 view和model的对象,操作两者的方法.相当于协调者，是模型与视图之间的桥梁，将模型与视图分离开来,对view 和model 进行调度操作。**

```java
  /**
 * login的presenter层 进行对view 和 model 的控制,
 * Created by ccj on 2016/7/7.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {
        loginView.showProgress();
        Observable<User> userObservable = APIService.userLogin(username, password);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        loginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.log(e.getMessage().toString());
                        loginView.hideProgress();
                        loginView.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(User getIpInfoResponse) {
                        TLog.log(getIpInfoResponse.toString());
                        loginView.navigateToMain();
                    }
                });
    }

    @Override
    public void start() {

    }

```


***2.网络访问: 采用rxjava+retrofit+gson进行网络访问,并轻松的将json转为对象,结构清晰,使用方便.***

 - **在APIService中初始化retrofit**

```java
 /**
 * 调用后台的接口,架构网络层采用Retroft+Rxjava+gson
 * Created by ccj on 2016/7/1.
 *
 */
public class APIService {

    private static final String TAG = "APIService";
    public static final String URL_HOST ="http://123.234.82.23" ;//服务器端口
    /**
     * 基础地址
     * 初始化 retroft
     */
    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(URL_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();
    private static final RetrofitRequest apiManager = sRetrofit.create(RetrofitRequest.class);
    /**
     * 登录,返回,我这边用的是json格式的post,大家可以进行选择
     * @param city
     * @return
     */
    public static Observable<User> userLogin(String format, String city) {
        HashMap<String,String> hashMap =new HashMap<>();
        hashMap.put("UserPhone", format);
        hashMap.put("UserPassWord", city);
        TLog.log(hashMap.toString());
        Observable<User> ss = apiManager.userLogin(hashMap);
        return  ss;
    }

    /**********************仿照上面的方法,进行请求数据****************************/

```
 - **用retrofit访问 返回observable的对象**

```java
public interface RetrofitRequest {


    boolean isTest=true; //是否在测试环境下
    //发布之前更改
    String BASE_URL_TEST = "/flyapptest/";//测试服务器
    String BASE_URL_OFFICAL = "/flyapp/";//正式服务器

    String BASE_URL = isTest?BASE_URL_TEST:BASE_URL_OFFICAL;//发布服务器


    /**
     * 登录返回(json post)
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<User> userLogin(@Body HashMap<String, String> body);

```

***3.异步处理: 采用rxjava响应式框架进行优雅的异步处理,简化代码逻辑,并且很好的解决内存泄漏	问题.(相关模块在TakePhoto业务中)***
```java
  /**
     * rxjava 进行异步操作 eventBus进行时间传递
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
                .subscribe(new Observer<String>() {//订阅观察者
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
```


***4.事件订阅: 采用EventBus作为事件总线,进行线程间,组件之间的通信.***

```java
/**
 * 事件总线 用于组件或线程通信,可替代回调,广播等
 * Created by ccj on 2016/4/14.
 */
public class EventUtils {

    /**
     * object类型(即传统的所有类型,都可以强转进行传递事件)
     */
    public static class ObjectEvent{
        private Object object;
        public ObjectEvent(Object object) {
            // TODO Auto-generated constructor stub
            this.object = object;
        }
        public Object getMsg(){
            return object;
        }
    }

}
```

***5.代码分包: 根据业务区分进行分包,便于对代码进行管理 .***

![这里写图片描述](http://img.blog.csdn.net/20160712153822145)


***6. 工具类: TDeviceUtils设备状态的工具类,,SeriliazebleUtils 序列化工具类,SharepreferenceUtils保存工具类,***
相关请参考代码

***7.app栈管理: 基于baseActivity,很好的释放内存,管理内存.***
相关请参考代码

---
#### 待后期完成

***异常捕获(待完善)***
***测试框架Espresso/JUnit/Mockito/Robolectric (待完善)***

---


##总结

1.层次分明，各层级之间都不管对方如何实现，只关注结果；
2.在视图层(Presentation Layer)使用MVP架构，使原本臃肿的Activity(或Fragment)变得简单，其处理方法都交给了Presenter。
3.易于做测试，只要基于每个模块单独做好单元测试就能确保整体的稳定性。
4.易于快速迭代，基于代码的低耦合，只需在业务逻辑上增加接口，然后在相应的层级分别实现即可，丝毫不影响其他功能。

##Blog-link
[csdn博客,欢迎大家指正,评阅~谢谢O(∩_∩)O谢谢](http://blog.csdn.net/ccj659/article/details/51889713)

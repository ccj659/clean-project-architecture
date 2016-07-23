


-ignorewarnings                     # 忽略警告，避免打包时某些警告出现
-dontwarn
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose                            # 混淆时是否记录日志
#-printmapping topden.map
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}


-keep class * extends java.lang.annotation.Annotation { *; }
-keep class * extends android.app.Application { *; }
-keep public class * implements java.io.Serializable {*;}
-keep public class * implements android.os.Parcelable {*;}



-keepattributes Exceptions,InnerClasses,Signature
-keepattributes SourceFile,LineNumberTable,EnclosingMethod


# keep 泛型
-keepattributes *Annotation*

-keepclassmembers class * {
    public <init>(org.json.JSONObject);
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keepattributes *Annotation*
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v13.**
-keep class android.support.v13.** { *; }
-keep class android.support.v7.** { *; }



-dontwarn javax.annotation.**
-keep class javax.annotation.** { *;}


#########-------------------第三方 ----------------------------------#############
#recoo
-keep class com.dodola.** {*;}
-keep class com.lody.legend.** {*;}

#-dontwarn com.dodola.rocoo.** { *; }
#-keep class com.dodola.rocoo.** { *; }

# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }



# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}





# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions



# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**



# Gson
#-keepattributes Signature-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# 使用Gson时需要配置Gson的解析对象及变量都不混淆。不然Gson会找不到变量。
# 将下面替换成自己的实体类
-keep class com.efly.flyhelper.bean.** { *; }



# EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }


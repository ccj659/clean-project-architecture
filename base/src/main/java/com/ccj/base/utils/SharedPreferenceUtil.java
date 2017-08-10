package com.ccj.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.ccj.base.base.BaseApplication;
import com.ccj.base.bean.User;

import java.io.IOException;
import java.io.StreamCorruptedException;


public class SharedPreferenceUtil {

    // 用户名key
    public final static String KEY_NAME = "KEY_NAME";
    public final static String KEY_AUTO = "KEY_AUTO";
    public final static String KEY_LOGIN = "KEY_LOGIN";
    public final static String KEY_LEVEL = "KEY_LEVEL";
    public final static String KEY_DELIVERY = "KEY_DELIVERY";
    private static SharedPreferenceUtil spUtils;
    private static User spUser = null;
    private SharedPreferences sp;


    //

    /**
     *
     * 初始化，一般在应用启动之后就要初始化
     *
     * @param context 此处的context要用application的全局上下文,
     *                避免static强类型一直持有activity的引用,造成内存泄露
     */
    public static synchronized void initSharedPreference(Context context) {
        if (spUtils == null) {
            spUtils = new SharedPreferenceUtil(context);
        }
    }


    /**
     * 获取唯一的instance
     *
     * @return
     */

    public static synchronized SharedPreferenceUtil getInstance() {
        if (spUtils == null) {
            spUtils = new SharedPreferenceUtil(BaseApplication.getInstance());
        }

        return spUtils;
    }

    public SharedPreferenceUtil(Context context) {
        sp = context.getSharedPreferences("SharedPreferenceUtil", Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPref() {
        return sp;
    }

    public synchronized void putAutoLogin(Boolean AutoLogin) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_AUTO, AutoLogin);
        editor.commit();
    }

    public synchronized Boolean getAutoLogin() {
        Boolean flag = sp.getBoolean(KEY_AUTO, false);
        Log.i("flag", flag + "flag");
        return flag;
    }

    public synchronized void setIsLogin(Boolean AutoLogin) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_LOGIN, AutoLogin);
        editor.commit();
    }

    public synchronized Boolean getIsLogin() {
        Boolean flag = sp.getBoolean(KEY_LOGIN, false);
        return flag;
    }

    public synchronized void putUser(User user) {
        SharedPreferences.Editor editor = sp.edit();
        String str = "";
        try {
            str = SerializableUtil.objToStr(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(KEY_NAME, str);
        editor.commit();
        spUser = user;
    }


    //记录用户名
    public void setUsername(String username){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("pre_username",username);
        editor.apply();
    }

    //读取用户名
    public String getUsername(){
        return sp.getString("pre_username","");
    }


    public synchronized User getUser()
    {
        String str = sp.getString(SharedPreferenceUtil.KEY_NAME, "");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (spUser == null) {
            spUser = new User();
            //获取序列化的数据
            try {
                Object obj = SerializableUtil.strToObj(str);
                if (obj != null) {
                    spUser = (User) obj;
                    Log.e("USER", "getuser" + spUser.toString());
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spUser;
    }


    public synchronized void DeleteUser() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_NAME, "");
        editor.commit();
        spUser = null;
    }

//
//    public synchronized DeliveryMessage getDeliveryMessage() {
//        DeliveryMessage deliveryMessage = new DeliveryMessage();
//        //获取序列化的数据
//        String str = sp.getString(SharedPreferenceUtil.KEY_DELIVERY, "");
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        try {
//            Object obj = SerializableUtil.strToObj(str);
//            if (obj != null) {
//                deliveryMessage = (DeliveryMessage) obj;
//            }
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return deliveryMessage;
//
//    }
//
//    public synchronized void putDeliveryMessage(DeliveryMessage deliveryMessage) {
//        SharedPreferences.Editor editor = sp.edit();
//        String str = "";
//        try {
//            str = SerializableUtil.objToStr(deliveryMessage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        editor.putString(KEY_DELIVERY, str);
//        editor.commit();
//    }


}

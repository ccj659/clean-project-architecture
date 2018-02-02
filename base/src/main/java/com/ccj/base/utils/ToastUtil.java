package com.ccj.base.utils;

import com.ccj.base.base.BaseApplication;

public class ToastUtil {


    public static void show( String text) {
        android.widget.Toast.makeText(BaseApplication.getContext(), text, android.widget.Toast.LENGTH_SHORT).show();
    }

}

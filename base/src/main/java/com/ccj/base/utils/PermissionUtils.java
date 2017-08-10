package com.ccj.base.utils;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2016/8/5.
 * 6.0中动态申请权限的工具类
 * 回调方法{@link Activity#onRequestPermissionsResult(int, String[], int[])}应写在传入的activity内
 */
public class PermissionUtils {

    private static final String TAG = "PermissionUtils";

    public static final int REQUEST_PERMISSION = 1;

    //申请拍照所需的权限类
    public static void RequestPermission(final Activity aty){

        boolean flag = false;//true表示有权限未获取

        if(!(aty instanceof ActivityCompat.OnRequestPermissionsResultCallback))
            throw new RuntimeException("申请权限的Activity未实现OnRequestPermissionsResultCallback接口");

        final String[] permissions = {Manifest.permission.CAMERA
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.ACCESS_COARSE_LOCATION
                        , Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.READ_EXTERNAL_STORAGE
                };

        for(String permission:permissions){
            if(ContextCompat.checkSelfPermission(aty,permission) != PackageManager.PERMISSION_GRANTED){
                flag = true;
                break;
            }
        }

        if(flag){
            AlertDialog.Builder builder = new AlertDialog.Builder(aty);
            builder.setTitle("权限申请")
                    .setMessage("请允许建管通的权限申请，否则无法正常使用工地现场、报告取证等功能")
                    .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(aty, permissions, REQUEST_PERMISSION);
                        }
                    })
                    .show();
        }
    }

    public static void RequestPermission(Activity aty, String[] permissions){
        if(!(aty instanceof ActivityCompat.OnRequestPermissionsResultCallback))
            throw new RuntimeException("申请权限的Activity未实现OnRequestPermissionsResultCallback接口");
        ActivityCompat.requestPermissions(aty, permissions, REQUEST_PERMISSION);
    }

}

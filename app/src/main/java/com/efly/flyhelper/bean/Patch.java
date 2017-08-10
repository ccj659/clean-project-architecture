package com.efly.flyhelper.bean;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/7/23.
 */
@Deprecated
public class Patch {
    /**
     * code : 200
     * msg : 查询成功
     * details : {"ID":13,"VersionCode":"3","VersionText":"3","VersionDate":"2016-07-26 10:45:48","VersionUrl":"http://123.234.82.23/FlyAppWeb/ApkDown/patch.jar","VersionType":3}
     */

    public int code;
    public String msg;
    /**
     * ID : 13
     * VersionCode : 3
     * VersionText : 3
     * VersionDate : 2016-07-26 10:45:48
     * VersionUrl : http://123.234.82.23/FlyAppWeb/ApkDown/patch.jar
     * VersionType : 3
     */

    public DetailsBean details;

    public static Patch objectFromData(String str) {

        return new Gson().fromJson(str, Patch.class);
    }

    public static class DetailsBean {
        public int ID;
        public String VersionCode;
        public String VersionText;
        public String VersionDate;
        public String VersionUrl;
        public int VersionType;

        public static DetailsBean objectFromData(String str) {

            return new Gson().fromJson(str, DetailsBean.class);
        }

        @Override
        public String toString() {
            return "DetailsBean{" +
                    "ID=" + ID +
                    ", VersionCode='" + VersionCode + '\'' +
                    ", VersionText='" + VersionText + '\'' +
                    ", VersionDate='" + VersionDate + '\'' +
                    ", VersionUrl='" + VersionUrl + '\'' +
                    ", VersionType=" + VersionType +
                    '}';
        }
    }

    /**
     * 自行设定实体类
     */

    @Override
    public String toString() {
        return "Patch{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", details=" + details +
                '}';
    }
}

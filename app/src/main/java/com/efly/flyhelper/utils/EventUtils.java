package com.efly.flyhelper.utils;


import com.efly.flyhelper.bean.User;

/**
 * 事件总线 用于组件或线程通信,可替代回调,广播等
 * Created by ccj on 2016/4/14.
 */
public class EventUtils {


    /**
     * 传递String类型的event
     *
     */
    public static class StringEvent{
        private String mMsg;
        public StringEvent(String msg) {
            // TODO Auto-generated constructor stub
            this.mMsg = msg;
        }
        public String getMsg(){
            return mMsg;
        }
    }

    public static class intEvent{
        private int mMsg;
        public intEvent(int msg) {
            // TODO Auto-generated constructor stub
            this.mMsg = msg;
        }
        public int getMsg(){
            return mMsg;
        }
    }



    public static class getUserEvent{
        private User user;
        public getUserEvent(User user) {
            // TODO Auto-generated constructor stub
            this.user = user;
        }
        public User getMsg(){
            return user;
        }

    }




    /**
     * object类型(即传统的所有类型,都可以强转进行传递事件)
     *
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

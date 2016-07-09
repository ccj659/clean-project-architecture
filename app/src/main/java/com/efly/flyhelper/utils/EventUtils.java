package com.efly.flyhelper.utils;


import com.efly.flyhelper.bean.User;

/**
 * Created by Administrator on 2016/4/14.
 */
public class EventUtils {

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
     * 传递String类型的event
     *
     */
    public static class getStringEvent {
        private String url;
        public getStringEvent(String url) {
            // TODO Auto-generated constructor stub
            this.url = url;
        }
        public String getMsg(){
            return url;
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

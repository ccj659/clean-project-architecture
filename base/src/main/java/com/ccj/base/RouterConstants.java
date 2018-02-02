package com.ccj.base;
/**
 * Created by chenchangjun on 17/8/9.
 */


public final class RouterConstants {




    /**
     * home module
     */
    public static final String HOME_MODULE_NAME="/homemodule/";
    public static final String HOME_MUDULE_FRAGMENT_HOME_HOME = HOME_MODULE_NAME+"FRAGMENT_HOME_HOME";


    /**
     * user module
     */
    public static final String USER_MODULE_NAME ="/loginmodule/";

    public static final String USER_MUDULE_FRAGMENT_HOME_USER = USER_MODULE_NAME+"FRAGMENT_HOME_USER";


    public static final String USER_MOUDLE_ACTIVITY = USER_MODULE_NAME +"LoginActivity";
    public static final String USER_SERVICE_IMPL = USER_MODULE_NAME +"LoginServiceImpl";
    public static final String USER_REGISTER_FRAGMENT = USER_MODULE_NAME +"/RegisterFragment";

    /**
     * video module
     */
    public static final String VIDEO_MODULE_NAME="/videomodule/";
    public static final String VIDEO_MUDULE_FRAGMENT_HOME_VIDEO = VIDEO_MODULE_NAME+"FRAGMENT_HOME_VIDEO";

    public static final String VIDEO_MUDULE_ACTIVITY = VIDEO_MODULE_NAME+"TakePhotoActivity";
    public static final String VIDEO_SERVICE_IMPL = VIDEO_MODULE_NAME+"VideoServiceImpl";




    /**
     * meizi module
     */
    public static final String MEIZI_MODULE_NAME="/meizimodule/";
    public static final String MEIZI_MUDULE_FRAGMENT_HOME_MEIZI = MEIZI_MODULE_NAME+"FRAGMENT_HOME_MEIZI";
    public static final String MEIZI_MUDULE_ACTIVITY_MEIZI_DETAIL = MEIZI_MODULE_NAME+"ACTIVITY_MEIZI_DETAIL";


}

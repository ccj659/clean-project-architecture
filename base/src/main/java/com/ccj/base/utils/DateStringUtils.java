package com.ccj.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2016/11/1.
 */

public class DateStringUtils {
    /**
     * 获得距离今天offset的日期
     * @param offset
     * @return
     */
    public static String getBeforeStringDate(int offset ){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd");
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.DATE,offset);
        return simpleDateFormat.format(calendar.getTime());

    }


}

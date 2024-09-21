package com.manage.greatming.greatmingmanage.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat();

    private static Date date;
    
    public TimeUtil(){
        TimeUtil.sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
    }

    public TimeUtil(String pattern){
        TimeUtil.sdf.applyPattern(pattern);
    }

    public static String GetDate(){
        date = new Date();
        return sdf.format(date);
    }

}

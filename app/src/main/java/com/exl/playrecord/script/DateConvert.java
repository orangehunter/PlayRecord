package com.exl.playrecord.script;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michael on 2016/6/30.
 */
public class DateConvert {
    public static SimpleDateFormat getDateFormatter() {
        return new SimpleDateFormat("yyyy/MM/dd a HH:mm:ss");
    }

    public static Date msToDate(long timeInMs){
        return new Date(timeInMs);
    }

    public static String msToString(long timeInMs){
        return getDateFormatter().format(msToDate(timeInMs));
    }


}

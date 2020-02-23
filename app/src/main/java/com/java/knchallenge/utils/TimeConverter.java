package com.java.knchallenge.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By Himanshu on 24-02-2020
 */
public class TimeConverter {

    public static String getTimeFromMilliSec(long milliSec){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        Date resultTime = new Date(milliSec);
        return  (sdf.format(resultTime));
    }
}

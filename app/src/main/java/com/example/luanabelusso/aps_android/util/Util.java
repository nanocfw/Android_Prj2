package com.example.luanabelusso.aps_android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marciano on 20/11/2017.
 */

public class Util {
    public static String dateTimeToStrSql(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Date strToDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

package com.example.luanabelusso.aps_android.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    public static int sorteio(int min, int max) {
        Random ran = new Random();
        if (max > 0)
            return ran.nextInt(max - min + 1) + min;
        else
            return ran.nextInt();
    }

    public static List<Integer> sorteio(int min, int max, int qntResults) {
        List<Integer> aux = new ArrayList<>();

        int temp = 0;

        if (qntResults < 2)
            aux.add(sorteio(min, max));
        else
            for (int i = 0; i < qntResults; i++) {
                while (true) {
                    temp = sorteio(min, max);
                    if (!aux.contains(temp)) {
                        aux.add(temp);
                        break;
                    }
                }
            }
        return aux;
    }

    public static int parseIntDef(String s, int defaultValue) {
        return s.matches("-?\\d+") ? Integer.parseInt(s) : defaultValue;
    }
}

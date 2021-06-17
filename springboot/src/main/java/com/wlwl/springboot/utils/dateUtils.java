package com.wlwl.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class dateUtils {
    public static List<String> getPastDate(int past) {
        List<String> dateList = new ArrayList<>();
        for (int i = past; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            Date today = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String result = format.format(today);
            dateList.add(result);
        }
        System.out.println(dateList);

        return dateList;
    }

    public static String format(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}

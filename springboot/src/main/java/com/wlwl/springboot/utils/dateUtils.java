package com.wlwl.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class dateUtils {
    public static Map<String, Object> getPastDate(int past) {
        Map<String, Object> map = new HashMap<>();
        List<Date> dateList = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < past; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            Date today = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String result = format.format(today);
            dateList.add(today);
            dates.add(result);
        }
        map.put("dateList", dateList);
        map.put("dates", dates);
        return map;
    }

    public static String format(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}

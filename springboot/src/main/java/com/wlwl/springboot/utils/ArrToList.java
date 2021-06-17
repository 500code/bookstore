package com.wlwl.springboot.utils;

import java.util.ArrayList;
import java.util.Collections;

public class ArrToList {
    public static String getRecord(String record, int id) {
//        去除空格
        record = record.replace(" ", "");
        String recordArr[] = record.split(","); //根据,分割成数组

        ArrayList<String> list = new ArrayList<>(recordArr.length);
        Collections.addAll(list, recordArr);
        System.out.print("list前==>");
        System.out.println(list);
        list.remove(id + "");
        System.out.print("list后==>");
        System.out.println(list);
        return list.toString().replace("[", "").replace("]", "");
    }
}

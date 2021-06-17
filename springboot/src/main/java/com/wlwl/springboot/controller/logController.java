package com.wlwl.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.log;
import com.wlwl.springboot.service.logService;
import com.wlwl.springboot.utils.dateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class logController {

    @Autowired
    private logService logService;

    @GetMapping("/log")
    //查询日志
    public R getLog(@RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<log> page = new Page<>(pno, 4);
        QueryWrapper<log> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("ldate");
        IPage<log> page1 = logService.page(page, wrapper);
        System.out.println(page1);
        if (page1.getTotal() != 0) {
            return R.ok().code(20000).data("list", page1);
        }
        return R.error().code(21003);
    }

    @GetMapping("/searchLog")
    public R searchLog(String key, @RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<log> page = new Page<>(pno, 4);
        QueryWrapper<log> wrapper = new QueryWrapper<>();
        wrapper.like("bname", key);
        IPage<log> page1 = logService.page(page, wrapper);
        if (page1.getTotal() != 0) {
            return R.ok().code(20000).data("list", page1);
        }
        return R.error().code(21003);
    }

    //    表格
    @GetMapping("getEchats")
    public R getEchats() {
//        获取最近七天
        QueryWrapper<log> wrapper = new QueryWrapper<>();
        List<String> dateList = dateUtils.getPastDate(7);
        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dateList);

//        还书
        List<Integer> countList = new ArrayList<>();
        for (String date : dateList) {
            wrapper.eq("operating", "0");//还书
            wrapper.eq("sdate", date);
            countList.add(logService.count(wrapper));
            wrapper.clear();
        }
        System.out.println("还书的list===========");
        System.out.println(countList);
        map.put("outCountList", countList);

        //   借书
        countList=new ArrayList<>();
        for (String date : dateList) {
            wrapper.eq("operating", "1");//借书
            wrapper.eq("sdate", date);
            countList.add(logService.count(wrapper));
            wrapper.clear();
        }
        System.out.println("借书的list===========");
        System.out.println(countList);
        map.put("inCountList", countList);

        System.out.println("===============");
        System.out.println(map);

        return R.ok().data("data", map);
    }
}

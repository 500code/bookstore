package com.wlwl.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.log;
import com.wlwl.springboot.service.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        QueryWrapper<log> wrapper = new QueryWrapper<>();
        wrapper.eq("operating", "0");//还书
        int outCount = logService.count(wrapper);
        wrapper = new QueryWrapper<>();
        wrapper.eq("operating", "1");//借书
        int inCount=logService.count(wrapper);

        return R.ok();
    }
}

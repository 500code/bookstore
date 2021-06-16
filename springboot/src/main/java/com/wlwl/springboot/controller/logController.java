package com.wlwl.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.log;
import com.wlwl.springboot.service.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class logController {

    @Autowired
    private logService logService;
    @GetMapping("/log")
    public R getLog( @RequestParam(value = "pno", defaultValue = "1") int pno){
        IPage<log> page = new Page<>(pno,4);
        QueryWrapper<log> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("ldate");
        IPage<log> page1 = logService.page(page,wrapper);
        System.out.println(page1);
        if(page1!=null){
            return R.ok().code(20000).data("list",page1);
        }
        return R.error().code(21003);
    }
}

package com.wlwl.springboot.controller;


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
    public R getLog(){
        List<log> log = logService.getLog(3, 3);
        System.out.println(log);
        return R.ok().data("log",log);
    }
}

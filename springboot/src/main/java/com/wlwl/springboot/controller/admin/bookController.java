package com.wlwl.springboot.controller.admin;


import com.wlwl.springboot.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class bookController {

    @Autowired


    @GetMapping("getBookList")
    public R getBookList(){
        return R.ok().data("11",111);
    }
}

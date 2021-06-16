package com.wlwl.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.Book;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/index")
public class indexController {
    @Autowired
    private bookService bookService;

    @GetMapping("/popularBooks")
    //热门图书推荐
    public R popularBooks(@RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<Book> page = new Page<>(pno, 8);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("count");
        IPage<Book> page1 = bookService.page(page, wrapper);
        System.out.println(page1);
        if (page1.getTotal() != 0) {
            return R.ok().code(20000).data("list", page1);
        }
        return R.error().code(21003);
    }
}

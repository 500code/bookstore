package com.wlwl.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.Book;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class bookController {

    @Autowired
    private bookService bookService;

    @GetMapping("getBookList")
    public R getBookList() {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        IPage<Book> page = new Page<>(1, 2);
        IPage<Book> page1 = bookService.page(page);
        return R.ok().data("list", page1);
    }

    @PostMapping("addBook")
    public R addBook(Book book) {
        if (book == null) {
            return R.error();
        }

        boolean flag = bookService.save(book);
        if (flag) {
            return R.ok();
        }
        return R.error();
    }
}

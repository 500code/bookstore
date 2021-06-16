package com.wlwl.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.Book;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.service.UserService;
import com.wlwl.springboot.service.bookService;
import com.wlwl.springboot.vo.bookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class bookController {

    @Autowired
    private bookService bookService;
    @Autowired
    private UserService userService;

    //查询书记
    @GetMapping("/getBookList")
    public R getBookList(@RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<Book> page = new Page<>(pno, 4);
        IPage<Book> page1 = bookService.page(page);
        if (page1 != null) {
            return R.ok().code(20000).data("list", page1);
        } else {
            return R.error().code(21003);
        }

    }

    //添加书籍
    @PostMapping("/addBook")
    public R addBook(Book book, MultipartFile ppic) throws IOException {
        System.out.println(ppic);
        System.out.println(book);
        if (StringUtils.hasLength(book.getBname()) && StringUtils.hasLength(book.getAuthor())) {
            if (!ppic.isEmpty()) {
                String pic = UUID.randomUUID().toString().replace("-", "");
                String fileName = ppic.getOriginalFilename();
                String FileTypeName = fileName.substring(fileName.lastIndexOf("."));
                String Name = pic + FileTypeName;
                System.out.println("name=" + Name);
                ppic.transferTo(new File("D:\\JavaStudy\\bookstore\\book\\vue\\src\\assets\\booksupload\\" + Name));
                book.setNum(book.getTotal());
                book.setPic(Name);
                boolean flag = bookService.save(book);
                if (flag) {
                    return R.ok().code(20000);
                }
                System.out.println("book=" + book);
            }
        }
        return R.error();
    }

    //搜索
    @GetMapping("/search")
    public R search(String key, @RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<Book> page = new Page<>(pno, 4);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.like("bname", key).or().like("author", key).or().like("taglist", key);
        IPage<Book> page1 = bookService.page(page, wrapper);
        if (page1 != null) {
            System.out.println(page1);
            return R.ok().code(20000).data("list", page1);
        }
        return R.error();
    }

    //    修改
    @PostMapping("/update")
    public R updataBook(Book book) {
        System.out.println(book);
        UpdateWrapper<Book> wrapper = new UpdateWrapper<>();
        wrapper.eq("bid", book.getBid());
        boolean falg = bookService.update(book, wrapper);
        if (falg) {
            return R.ok();
        }
        return R.error();
    }

    // 删除
    @PostMapping("/deleteBook")
    public R delete(Integer bid) {
        System.out.println(bid);
        boolean b = bookService.removeById(bid);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    //书籍借阅排行
    @GetMapping("/bookRanking")
    public R bookRanking(@RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<Book> page = new Page<>(pno, 5);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("count");
        IPage<Book> page1 = bookService.page(page, wrapper);
        System.out.println(page1);
        if (page1 != null) {
            return R.ok().data("list", page1);
        }
        return R.error();
    }

    //个人借阅排行
    @GetMapping("userRanking")
    public R userRanking(@RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<User> page = new Page<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("ucount");
        IPage<User> page1 = userService.page(page);
        System.out.println(page1);
        if (page1 != null) {
            return R.ok().data("list", page1);
        }
        return R.error();
    }

    //个人借阅排行搜索
    @GetMapping("/userSearch")
    public R userSearch(String key, @RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<User> page = new Page<>(pno, 4);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("uname", key);
        IPage<User> page1 = userService.page(page, wrapper);
        System.out.println(page1);
        if (page1 != null) {
            System.out.println(page1);
            return R.ok().code(20000).data("list", page1);
        }
        return R.error();
    }

    //借书
    @GetMapping("/getBook")
    public R getBook(bookVo vo) {
        System.out.println(vo);
        UpdateWrapper<bookVo> wrapper = new UpdateWrapper<>();
        wrapper.eq("uid",vo.getUid());

        return R.ok();
    }
}

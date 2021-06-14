package com.wlwl.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.Book;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class bookController {

    @Autowired
    private bookService bookService;

    @GetMapping("/getBookList")
    public R getBookList(int pno) {
        IPage<Book> page = new Page<>(pno, 4);
        IPage<Book> page1 = bookService.page(page);
        if (page1!=null){
            return R.ok().code(20000).data("list",page1);
        }else {
            return  R.error().code(21003);
        }

    }

    @PostMapping("/addBook")
    public R addBook(Book book, MultipartFile ppic) throws IOException {
        System.out.println(ppic);
        if(StringUtils.hasLength(book.getBname())&&StringUtils.hasLength(book.getAuthor())){
            if (!ppic.isEmpty()) {
                String pic=UUID.randomUUID().toString().replace("-","");
                String fileName = ppic.getOriginalFilename();
                String FileTypeName = fileName.substring(fileName.lastIndexOf("."));
                String Name = pic + FileTypeName;
                System.out.println("name="+Name);
                ppic.transferTo(new File("D:\\JavaStudy\\bookstore\\book\\vue\\src\\assets\\booksupload\\" + Name));
                book.setNum(book.getTotal());
                book.setPic(Name);
                boolean flag = bookService.save(book);
                if(flag){
                   return R.ok().code(20000);
                }
                System.out.println("book="+book);
            }
        }
        return R.error();
    }
}

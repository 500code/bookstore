package com.wlwl.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlwl.springboot.entity.Book;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.entity.log;
import com.wlwl.springboot.service.UserService;
import com.wlwl.springboot.service.bookService;
import com.wlwl.springboot.service.logService;
import com.wlwl.springboot.utils.ArrToList;
import com.wlwl.springboot.utils.dateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class bookController {

    @Autowired
    private bookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private logService logService;

    //查询书籍
    @GetMapping("/getBookList")
    public R getBookList(@RequestParam(value = "pno", defaultValue = "1") int pno) {
        IPage<Book> page = new Page<>(pno, 4);
        IPage<Book> page1 = bookService.page(page);
        if (page1.getTotal() != 0) {
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
        if (page1.getTotal() != 0) {
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
            return R.ok().code(20000);
        }
        return R.error();
    }

    // 删除
    @PostMapping("/deleteBook")
    public R delete(Integer bid) {
        System.out.println(bid);
        boolean b = bookService.removeById(bid);
        if (b) {
            return R.ok().code(20000);
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
        if (page1.getTotal() != 0) {
            return R.ok().code(20000).data("list", page1);
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
        if (page1.getTotal() != 0) {
            return R.ok().code(20000).data("list", page1);
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
        if (page1.getTotal() != 0) {
            System.out.println(page1);
            return R.ok().code(20000).data("list", page1);
        }
        return R.error();
    }

    //借书
    @GetMapping("/getBook")
    public R getBook(@RequestParam("uid") int uid, @RequestParam("bid") int bid) {
        User user = userService.getById(uid);
        Book book = bookService.getById(bid);
//        查看库存与用户是否还能借书
        if (user.getNum() >= 5) {
            return R.error().message("已超出借书最大值，无法继续借书");
        }
        if (book.getNum() < 1) {
            System.out.println(book.getNum());
            return R.error().message("该书被借光了哦");
        }
//       处理用户借书后
        StringBuffer buffer = new StringBuffer();
        if (StringUtils.hasLength(user.getRecord())) {  //当前还借了其他的书的情况
//            原来的图书列表
            buffer.append(user.getRecord());
            buffer.append(",");
//            添加这次借书的bid
            buffer.append(book.getBid().toString());
        } else {  //当前未借其他的书
            buffer.append(book.getBid().toString());
        }
        //更新用户借书列表
        user.setRecord(buffer.toString());
//        当前借书次数加一
        user.setNum(user.getNum() + 1);
//        借书累计次数加一
        user.setUcount(user.getUcount() + 1);

        //          处理图书被借后
//        清空StringBuffer里面的内容
        buffer = new StringBuffer();
        //       添加借书人uid
        if (StringUtils.hasLength(book.getRecord())) {  //当前还借了其他的书的情况
            buffer.append(book.getRecord());
            buffer.append(",");
//            添加这次借书的uid
            buffer.append(user.getUid());
        } else {  //当前未借其他的书
            buffer.append(user.getUid());
        }
        book.setRecord(buffer.toString());//        更新这本书借书人名单
        book.setNum(book.getNum() - 1); //库存减一
        book.setCount(book.getCount() + 1); //被借次数加一

//        更新数据库
        boolean b = userService.saveOrUpdate(user);
        boolean b1 = bookService.saveOrUpdate(book);

//        添加日志
        log log = new log();
        log.setLdate(new Date());
        log.setBname(book.getBname());
        log.setUid(user.getUid());
        log.setUname(user.getUname());
        log.setOperating(1);
        log.setReputation(user.getReputation());
        log.setBid(book.getBid());
        log.setSdate(dateUtils.format(new Date()));
        logService.save(log);

        if (b && b1) {
            return R.ok().code(20000).message("借书成功");
        }
        return R.error().message("借书失败");
    }

    //    还书
    @PostMapping("returnBook")
    public R returnBook(@RequestParam("uid") int uid, @RequestParam("bid") int bid) {
        User user = userService.getById(uid);
        Book book = bookService.getById(bid);
//       处理user
        user.setNum(user.getNum() - 1); //能借书次数加一
        String record = user.getRecord();
        System.out.println(record);
        record = ArrToList.getRecord(record, bid);
        System.out.println(record);
        user.setRecord(record);

//        处理book
        record = book.getRecord();
        book.setNum(book.getNum() + 1); //库存加一
        record = ArrToList.getRecord(record, uid);
        System.out.println(record);
        book.setRecord(record);

        //        更新数据库
        boolean b = userService.saveOrUpdate(user);
        boolean b1 = bookService.saveOrUpdate(book);
        //        添加日志
        log log = new log();
        log.setLdate(new Date());
        log.setBname(book.getBname());
        log.setUid(user.getUid());
        log.setUname(user.getUname());
        log.setOperating(0);
        log.setReputation(user.getReputation());
        log.setBid(book.getBid());
        log.setSdate(dateUtils.format(new Date()));
        logService.save(log);

        if (b && b1) {
            return R.ok().code(20000).message("还书成功");
        }
        return R.error().message("还书失败");
    }

    @GetMapping("getCount")
    public R getCount() {
        int count = bookService.count();
        int log = logService.count();
        int ucount = userService.count();
        Map<String, Object> map = new HashMap();
        map.put("count", count);
        map.put("log", log);
        map.put("ucount", ucount);
        return R.ok().data(map);
    }

}

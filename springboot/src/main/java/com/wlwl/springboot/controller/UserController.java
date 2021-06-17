package com.wlwl.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlwl.springboot.entity.Book;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.service.UserService;
import com.wlwl.springboot.service.bookService;
import com.wlwl.springboot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private bookService bookService;

    @PostMapping("/login")
    public R login(User user) {
        System.out.println(user);
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> wapper = new QueryWrapper<>();
        wapper.eq("uname", user.getUname());
        wapper.eq("upwd", user.getUpwd());
        User one = userService.getOne(wapper);
        System.out.println(one);
        if (one != null) {
            Map<String, String> payload = new HashMap<>();
            payload.put("user", one.getUname());
            payload.put("uid", one.getUid() + "");
            String token = JwtUtils.getToken(payload);
            map.put("token", token);
            map.put("uid", one.getUid());
            return R.ok().code(20000).data(map);
        } else {
            map.put("state", false);
            map.put("msg", "查无此账号，请先注册");
            return R.error().code(21003).data(map);
        }
    }

    @PostMapping("/register")
    public R register(User user) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname", user.getUname());
        queryWrapper.eq("upwd", user.getUpwd());
        int count = userService.count(queryWrapper);
        if (count > 0) {
            map.put("msg", "该账号已存在");
            return R.error().code(21003).data(map);
        } else {
            boolean save = userService.save(user);
            if (save == true) {
                map.put("msg", "注册成功");
                return R.ok().code(20000).data(map);
            } else {
                map.put("msg", "注册失败");
                return R.error().code(20001).data(map);
            }
        }
    }

    @GetMapping("getBookByUid/{uid}")
//    public R getBookByUid(@PathVariable Integer uid) {
//        System.out.println(uid);
//        Map<String, Object> map = new HashMap<>();
//        User user = userService.getById(uid);
//        map.put("user", user);
//        String record = user.getRecord();
//        System.out.println(record);
//        if (StringUtils.hasLength(record)) {
//            List<Book> books = new ArrayList<>();
//            if (!record.contains(",")) {
//                System.out.println("进来了");
//                books.add(bookService.getById(record));
//                map.put("books", books);
//                return R.ok().data(map);
//            }
//            String[] split = record.split(",");
//
//            for (String s : split) {
//                books.add(bookService.getById(s));
//            }
//            map.put("books", books);
//            return R.ok().data(map);
//        }
//        return R.ok().data(map);
//    }

    public R getBookByUid(@PathVariable Integer uid) {
        System.out.println(uid);
        Map<String, Object> map = new HashMap<>();
        User user = userService.getById(uid);
        map.put("user", user);
        String record = user.getRecord();
        System.out.println(record);
        if (StringUtils.hasLength(record)) {
            List<Book> books = new ArrayList<>();
            if (!record.contains(",")) {
                books.add(bookService.getById(record));
                map.put("books", books);
                return R.ok().data(map);
            }
            String[] split = record.split(",");
            for (String s : split) {
                System.out.println("s=============="+s);
                System.out.println("=========================="+ bookService.getById(s));
                books.add(bookService.getById(s));
            }
            map.put("books", books);
            return R.ok().data(map);
        }
        return R.ok().data(map);
    }

}

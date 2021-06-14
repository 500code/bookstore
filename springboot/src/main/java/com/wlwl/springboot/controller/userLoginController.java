package com.wlwl.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.service.UserService;
import com.wlwl.springboot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class userLoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(User user){
        System.out.println(user);
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<User> wapper = new QueryWrapper<>();
        wapper.eq("uname",user.getUname());
        wapper.eq("upwd",user.getUpwd());
        User one = userService.getOne(wapper);
        if (one!=null){
            Map<String,String> payload = new HashMap<>();
            payload.put("user",one.getUname());
            String token = JwtUtils.getToken(payload);
            map.put("msg","登陆成功");
            map.put("token",token);
            return R.ok().code(20000).data(map);
        }else {
            map.put("state",false);
            map.put("msg","查无此账号，请先注册");
            return R.error().code(21003).data(map);
        }
    }
}

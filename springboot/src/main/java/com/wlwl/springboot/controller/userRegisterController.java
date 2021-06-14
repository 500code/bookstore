package com.wlwl.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class userRegisterController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public R register(User user){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname", user.getUname());
        queryWrapper.eq("upwd", user.getUpwd());
        int count = userService.count(queryWrapper);
        if (count > 0) {
            map.put("msg","该账号已存在");
            return R.error().code(21003).data(map);
        } else {
            boolean save = userService.save(user);
            if(save==true){
                map.put("msg","注册成功");
                return R.ok().code(20000).data(map);
            }else {
                map.put("msg","注册失败");
                return R.error().code(20001).data(map);
            }
        }
    }


}

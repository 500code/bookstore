package com.wlwl.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.service.userLoginService;
import com.wlwl.springboot.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    private userLoginService userLoginService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUname("wlwl");
        user.setUpwd("123");
        QueryWrapper<User> wapper = new QueryWrapper<>();
        wapper.eq("uname",user.getUname());
        wapper.eq("upwd",user.getUpwd());
        User one = userLoginService.getOne(wapper);
        System.out.println(one);
        Map<String,String> map = new HashMap<>();
        map.put("state","登录成功");
        map.put("user",one.getUname());
        String token = JwtUtils.getToken(map);
        System.out.println(token);
        System.out.println("==============");
        System.out.println(JwtUtils.verify(token).getClaims());
    }

}

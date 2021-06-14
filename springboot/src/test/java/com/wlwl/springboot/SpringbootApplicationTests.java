package com.wlwl.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.service.UserService;
import com.wlwl.springboot.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public R login(User user){
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
            map.put("msg","账号密码不对");
            return R.ok().code(21003).data(map);
        }
    }

    @Test
    void register() {
        User user = new User();
        user.setUname("哈哈");
        user.setUpwd("123");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname", user.getUname());
        queryWrapper.eq("upwd", user.getUpwd());
        int count = userService.count(queryWrapper);
        if (count > 0) {
            System.out.println("该账号已存在");
        } else {
            boolean save = userService.save(user);
            System.out.println(save);
        }
    }

}

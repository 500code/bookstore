package com.wlwl.springboot.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlwl.springboot.entity.Admin;
import com.wlwl.springboot.service.adminLoginService;
import com.wlwl.springboot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class adminLoginController {
    @Autowired
    private adminLoginService adminLoginService;
    @PostMapping("/login")
    public Map<String,Object> adminLogin(Admin admin){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("aname",admin.getAname());
        wrapper.eq("apwd",admin.getApwd());
        Admin ad = adminLoginService.getOne(wrapper);
        if (ad!=null){
            Map<String,String> payload = new HashMap<>();
            payload.put("admin",ad.getAname());
            String token = JwtUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","登陆成功");
            map.put("token",token);
            return map;
        }else {
            map.put("state",false);
            map.put("msg","账号密码出错");
            return map;
        }
    }
}

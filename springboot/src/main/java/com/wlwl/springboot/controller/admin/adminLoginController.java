package com.wlwl.springboot.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlwl.springboot.entity.Admin;
import com.wlwl.springboot.entity.R;
import com.wlwl.springboot.service.AdminService;
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
    private AdminService adminService;
    @PostMapping("/login")
    public R adminLogin(Admin admin){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("aname",admin.getAname());
        wrapper.eq("apwd",admin.getApwd());
        Admin ad = adminService.getOne(wrapper);
        if (ad!=null){
            Map<String,String> payload = new HashMap<>();
            payload.put("admin",ad.getAname());
            String token = JwtUtils.getToken(payload);
            map.put("msg","登陆成功");
            map.put("admin",ad.getAname());
            map.put("token",token);
            System.out.println(map);
            return R.ok().code(20000).data(map);
        }else {
            map.put("msg","账号密码出错");
            return R.error().code(21003).data(map);
        }
    }
}

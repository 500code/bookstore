package com.wlwl.sprigboot.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlwl.sprigboot.entity.User;
import com.wlwl.sprigboot.mapper.loginMapper;
import com.wlwl.sprigboot.service.loginService;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl extends ServiceImpl<loginMapper, User> implements loginService {
}

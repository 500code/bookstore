package com.wlwl.springboot.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlwl.springboot.entity.User;
import com.wlwl.springboot.mapper.loginMapper;
import com.wlwl.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<loginMapper, User> implements UserService {
}

package com.wlwl.springboot.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlwl.springboot.entity.Admin;
import com.wlwl.springboot.mapper.admin.adminLoginMapper;
import com.wlwl.springboot.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<adminLoginMapper, Admin> implements AdminService {
}

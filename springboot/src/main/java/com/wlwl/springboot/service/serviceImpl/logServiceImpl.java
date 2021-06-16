package com.wlwl.springboot.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlwl.springboot.entity.log;
import com.wlwl.springboot.mapper.logMapper;
import com.wlwl.springboot.service.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class logServiceImpl extends ServiceImpl<logMapper, log> implements logService {

    @Autowired
    private logMapper logMapper;


    @Override
    public List<com.wlwl.springboot.entity.log> getLog(int bid, int uid) {
        return logMapper.getLog(bid, uid);
    }
}

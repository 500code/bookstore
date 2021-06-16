package com.wlwl.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.wlwl.springboot.entity.log;

import java.util.List;

public interface logService extends IService<log>{

    List<log> getLog(int bid,int uid);
}

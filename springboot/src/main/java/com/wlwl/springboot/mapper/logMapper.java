package com.wlwl.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlwl.springboot.entity.log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//连表查询
@Mapper
public interface logMapper extends BaseMapper<log> {
//
    @Select("select u.uid uid,u.uname uname,u.reputation reputation,b.bname  bname from user u,book b where bid=${bid} and uid=${uid}")
    List<log> getLog(@Param("bid") int bid,@Param("uid") int uid);
}

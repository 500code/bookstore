package com.wlwl.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
    private int uid;
    private String uname;
    private String upwd;
    private String ulogo;
    private int umax=5;
//    当前借了几次书
    private int num;
//    当前借了那几本 用，隔开
    private String record;
    private int ucount;
    private int ustate;
    private String utoken;
    private Integer reputation;
//    private String vip;
}

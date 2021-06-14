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
    private int umax;
    private String ubid;
    private int ucount;
    private int ustate;
    private String utoken;
//    private String vip;
}

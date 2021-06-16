package com.wlwl.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class log {
//    日期
    private Date date;
//    用户id
    private Integer uid;
//    用户名
    private String uname;
//    书名
    private String bname;
//    信用值
    private Integer reputation;
//    还书0   借书1
    private Integer operating;

}

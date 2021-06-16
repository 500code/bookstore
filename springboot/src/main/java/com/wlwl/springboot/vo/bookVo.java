package com.wlwl.springboot.vo;

import com.wlwl.springboot.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class bookVo extends Book {

//    用户id
    private Integer uid;
//    用户名
    private String uname;
//    用户总借书次数
    private Integer count;
//    用户还能借几次书
    private Integer num;
}

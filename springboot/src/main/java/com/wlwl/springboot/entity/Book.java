package com.wlwl.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@TableName("Book")
public class Book {
    //    主键 书的id
//    @TableId(type = IdType.AUTO)
    private Integer bid;
    //    书名
    private String bname;
    //    作者
    private String author;
    //    标签列表 ,号分开
    private String taglist;
    //    价格
    private BigDecimal money;
    //   图片的名称
    private String pic;
    //  总共有多少本
    private Integer total;
    // 库存还有多少本
    private Integer num;
    //  书的简介
    private String bdesc;
    // 书的状态  0被下架
    private Integer state;
    //  被借次数
    private Integer count;

}

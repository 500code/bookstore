package com.wlwl.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Admin {
    @TableId(type = IdType.AUTO)
    private int aid;
    private String aname;
    private String apwd;
    private String alogo;
}

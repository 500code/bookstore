package com.wlwl.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Admin {
    private int aid;
    private String aname;
    private String apwd;
    private String alogo;
}

package com.wyx.testspringboot.entity;

import lombok.Data;

/**
 * @ClassName Permission
 * @Author 一熹
 * @Date 2022/8/8 11:36
 * @Version 1.8
 **/
@Data
public class Permission {
    private Integer id;//自增主键
    private String empId;
    private String priority;//权限 root / user
}

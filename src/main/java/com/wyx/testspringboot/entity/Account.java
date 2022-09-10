package com.wyx.testspringboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * @ClassName Account
 * @Author 一熹
 * @Date 2022/8/4 16:03
 * @Version 1.8
 **/
@Data
@Getter
@Setter
public class Account {
    private String empId;
    private String email;
    private String password;
    private Date registerTime;
    private Date loginTime;
    private String salt;//盐 用于加密
}

package com.wyx.testspringboot.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.dto
 * @Author: Origami
 * @Date: 2022/9/4 18:49
 */
@Data
@Document(collection = "work-log")
public class LogBean {

    @Id
    private String id;

    @Indexed
    private Integer userId;

    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private String ip;

    private String className;//类名

    private String method;//方法名

    private String reqParam;//请求
}

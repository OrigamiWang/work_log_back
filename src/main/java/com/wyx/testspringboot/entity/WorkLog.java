package com.wyx.testspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;

/**
 * @ClassName WorkLog
 * @Author 一熹
 * @Date 2022/7/31 16:35
 * @Version 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkLog {

    private Integer no; //不展示在表格中
    private String empId;
    private Date modifyTime;    //提交或修改日志时间 //不展示在表格中，但会作为是否能够对该日志进行修改删除的依据
    private Date logDay;    //日志记录的是哪一天 //展示在表格中
    private String name;
    private String content;
    private Time workTime;  //工作时长

}

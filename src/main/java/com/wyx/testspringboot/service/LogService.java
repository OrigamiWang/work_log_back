package com.wyx.testspringboot.service;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.entity.WorkLog;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @ClassName LogService
 * @Author 一熹
 * @Date 2022/7/31 17:01
 * @Version 1.8
 **/

/***
 * @description: 对日志进行增删改查的service
 * @param: null
 * @return:
 * @author: 一熹
 * @date: 2022/7/31 17:01
 */


public interface LogService {
    CommonResult<List<WorkLog>> getLogList();

    CommonResult deleteByNo(Integer no);

    CommonResult updateByNo(Integer no, String empId, String name, String content, Date logDay, Time workTime);

    CommonResult insert(String empId, String name, Date logDay, String content, Time workTime);

    Integer getCount();

    Integer fuzzySelectByEmpId(String empId);


    CommonResult insertAccount(String email, String password);
}

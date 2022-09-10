package com.wyx.testspringboot.dao;

import com.wyx.testspringboot.entity.WorkLog;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @ClassName LogMapper
 * @Author 一熹
 * @Date 2022/7/31 17:01
 * @Version 1.8
 **/


/***
 * @description: 对日志进行增删改查的mapper
 * @param: null
 * @return:
 * @author: 一熹
 * @date: 2022/7/31 17:05
 */

public interface LogMapper {
    List<WorkLog> getLogList();

    void deleteByNo(Integer no);

    void updateByNo(@Param("no") Integer no, @Param("empId") String empId, @Param("name") String name, @Param("content") String content, @Param("logDay") Date logDay, @Param("workTime") Time workTime);

    void insert(@Param("empId") String empId, @Param("name") String name, @Param("logDay") Date logDay, @Param("content") String content, @Param("workTime") Time workTime);

    Integer getCount();

    Integer fuzzySelectByEmpId(@Param("empId") String empId);

    //创建账号
    void insertAccount(String empId, String email, String password, String salt);

}

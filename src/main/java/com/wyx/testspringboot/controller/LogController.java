package com.wyx.testspringboot.controller;

import com.wyx.testspringboot.MD5.MyMd5;
import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.config.Swagger2Config;
import com.wyx.testspringboot.entity.WorkLog;
import com.wyx.testspringboot.service.LogService;
import com.wyx.testspringboot.service.PriorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

/**
 * @ClassName logController
 * @Author 一熹
 * @Date 2022/7/31 16:58
 * @Version 1.8
 **/

/***
 * @description: 用于对日志增删改查的接口
 * @param: null
 * @return:
 * @author: 一熹
 * @date: 2022/7/31 16:59
 */

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/log")
@Api(tags = {Swagger2Config.TAG_1})
public class LogController {

    @Autowired
    LogService logService;

    @Autowired
    PriorityService permissionService;

    @ApiOperation("获取所有日志")
    //返回所有日志
    @GetMapping("/getLogList")
    public CommonResult<List<WorkLog>> getLogList() {
        return logService.getLogList();
    }

    @ApiOperation("根据主键删除日志")
    //根据no删除日志
    @GetMapping("/deleteByNo")
    public CommonResult deleteByNo(@RequestParam("no") Integer no) {
        return logService.deleteByNo(no);
    }

    @ApiOperation("根据主键更新日志")
    @GetMapping("/updateByNo")
    public CommonResult updateByNo(Integer no, String empId, String name, String content, Date logDay, Time workTime) {
        return logService.updateByNo(no, empId, name, content, logDay, workTime);
    }

    @ApiOperation("插入日志")
    @GetMapping("/insert")
    public CommonResult insert(String empId, String name, String content, Date logDay, Time workTime) {
        return logService.insert(empId, name, logDay, content, workTime);
    }

    //获取日志总数
    @ApiOperation("获取日志总数")
    @GetMapping("/getCount")
    public CommonResult<Integer> getCount() {
        Integer count = logService.getCount();
        if (count == null) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(count);
        }
    }

    //通过empId模糊查询日志总数
    @ApiOperation("通过empId模糊查询日志总数")
    @GetMapping("/fuzzySelectByEmpId")
    public CommonResult<Integer> fuzzySelectByEmpId(@RequestParam("empId") String empId) {
        Integer count = logService.fuzzySelectByEmpId(empId);
        if (count == null) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(count);
        }
    }


    @ApiOperation("生成账户")
    @GetMapping("/insertAccount")
    public CommonResult insertAccount(String email, String password) {
        return logService.insertAccount(email, password);
    }


}

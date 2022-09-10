package com.wyx.testspringboot.controller;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.config.Swagger2Config;
import com.wyx.testspringboot.entity.Permission;
import com.wyx.testspringboot.service.PriorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PermissionController
 * @Author 一熹
 * @Date 2022/8/8 11:41
 * @Version 1.8
 **/
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/permission")
@Api(tags = {Swagger2Config.TAG_3})
public class PriorityController {
    @Autowired
    PriorityService priorityService;

    @ApiOperation("获取所有权限")
    @GetMapping("/getPermissionList")
    public CommonResult<List<Permission>> getPermissionList() {
        return priorityService.getList();
    }

    @ApiOperation("获取权限总数")
    @GetMapping("/getPermissionCount")
    public CommonResult<Integer> getPermissionCount() {
        Integer permissionCount = priorityService.getPermissionCount();
        if (permissionCount == null) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(permissionCount);
        }
    }


    @ApiOperation("通过工号模糊查询总数")
    @GetMapping("/fuzzyGetCountByEmpId")
    public CommonResult<Integer> fuzzyGetCountByEmpId(String empId) {
        Integer count = priorityService.fuzzyGetCountByEmpId(empId);
        if (count == null) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(count);
        }
    }

    @ApiOperation("通过工号获取权限")
    @GetMapping("/getPriorityByEmpId")
    public CommonResult<String> getPriorityByEmpId(String empId) {
        String priorityByEmpId = priorityService.getPriorityByEmpId(empId);
        if (priorityByEmpId == null) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(priorityByEmpId);
        }
    }

    @ApiOperation("通过邮箱获取权限")
    @GetMapping("/getPriorityByEmail")
    public CommonResult<String> getPriorityByEmail(String email) {
        String priorityByEmail = priorityService.getPriorityByEmail(email);
        if (priorityByEmail == null) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(priorityByEmail);
        }
    }

    @ApiOperation("通过工号修改权限")
    @GetMapping("/updatePriorityByEmpId")
    public CommonResult updatePriorityByEmpId(String priority, String empId) {
        return priorityService.updatePriorityByEmpId(priority, empId);
    }

}

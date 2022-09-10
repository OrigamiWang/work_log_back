package com.wyx.testspringboot.service.impl;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.entity.Permission;
import com.wyx.testspringboot.dao.PriorityMapper;
import com.wyx.testspringboot.service.PriorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PermissionServiceImpl
 * @Author 一熹
 * @Date 2022/8/8 11:40
 * @Version 1.8
 **/
@Service
public class PriorityServiceImpl implements PriorityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriorityServiceImpl.class);

    @Autowired
    PriorityMapper priorityMapper;

    @Override
    public CommonResult<List<Permission>> getList() {
        try {
            List<Permission> list = priorityMapper.getList();
            return CommonResult.success(list);
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }

    @Override
    public void insertPermission(String empId, String permission) {
        priorityMapper.insertPermission(empId, permission);
    }

    @Override
    public Integer getPermissionCount() {
        try {
            return priorityMapper.getPermissionCount();
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Integer fuzzyGetCountByEmpId(String empId) {
        try {
            return priorityMapper.fuzzyGetCountByEmpId(empId);
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public String getPriorityByEmpId(String empId) {
        try {
            return priorityMapper.getPriorityByEmpId(empId);
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public String getPriorityByEmail(String email) {
        try {
            return priorityMapper.getPriorityByEmail(email);
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public CommonResult updatePriorityByEmpId(String priority, String empId) {
        try {
            priorityMapper.updatePriorityByEmpId(priority, empId);
            return CommonResult.success(null, "修改成功");
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }
}

package com.wyx.testspringboot.service;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.entity.Permission;

import java.util.List;

public interface PriorityService {
    CommonResult<List<Permission>> getList();

    void insertPermission(String empId, String permission);

    Integer getPermissionCount();

    Integer fuzzyGetCountByEmpId(String empId);

    String getPriorityByEmpId(String empId);

    String getPriorityByEmail(String email);

    CommonResult updatePriorityByEmpId(String priority, String empId);
}

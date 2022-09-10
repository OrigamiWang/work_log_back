package com.wyx.testspringboot.dao;

import com.wyx.testspringboot.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface PriorityMapper {
    List<Permission>  getList();

    void insertPermission(String empId, String permission);

    Integer getPermissionCount();

    Integer fuzzyGetCountByEmpId(String empId);

    String getPriorityByEmpId(String empId);

    String getPriorityByEmail(String email);

    void updatePriorityByEmpId(String priority, String empId);
}

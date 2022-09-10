package com.wyx.testspringboot.dao;

import com.wyx.testspringboot.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface LoginMapper {

    // 通过工号查询账户是否存在， 若存在则返回账户，否则返回null
    Account selectAccountByEmpId(String empId);

    Account selectAccountByEmail(String email);

    List<String> getEmailList();

    void updatePassword(String password, String email);

    List<String> getEmpIdList();

    String getEmailByEmpId(String empId);
}

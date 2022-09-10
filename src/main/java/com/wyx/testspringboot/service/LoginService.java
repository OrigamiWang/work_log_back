package com.wyx.testspringboot.service;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.entity.Account;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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


public interface LoginService {

    CommonResult<Map<String, Object>> loginByEmpId(HttpSession session, @Param("empId") String empId, @Param("password") String password);

    CommonResult<Map<String, Object>> loginByEmail(HttpSession session, @Param("email") String email, @Param("password") String password);

    CommonResult<String> getEmailByEmpId(String empId);

    List<String> getEmailList();

    CommonResult updatePassword(String password, String email);

    List<String> getEmpIdList();

    CommonResult<Account> judgeLogin(String token, HttpSession session);

    Account selectAccountByEmail(String email);
}

package com.wyx.testspringboot.controller;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.config.Swagger2Config;
import com.wyx.testspringboot.entity.Account;
import com.wyx.testspringboot.service.LogService;
import com.wyx.testspringboot.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/login")
@Api(tags = {Swagger2Config.TAG_2})
public class LoginController {
    @Resource
    LoginService loginService;

    @Resource
    LogService logService;

    @ApiOperation("判断是否登录")
    @GetMapping("/judgeLogin")
    public CommonResult<Account> judgeLogin(String token, HttpSession session) {
       return loginService.judgeLogin(token, session);
    }

    @ApiOperation("通过邮箱登录")
    @GetMapping(value = "/loginByEmail")
    public CommonResult<Map<String, Object>> loginByEmail(HttpSession session, String email, String password) {
       return loginService.loginByEmail(session, email, password);
    }

    @ApiOperation("通过工号登录")
    @GetMapping(value = "/loginByEmpId")
    public CommonResult<Map<String, Object>> loginByEmpId(HttpSession session, String empId, String password) {
        return loginService.loginByEmpId(session, empId, password);
    }

    @ApiOperation("获取所有邮箱")
    @GetMapping("/getEmailList")
    public CommonResult<List<String>> getEmailList() {
        List<String> emailList = loginService.getEmailList();
        if(emailList == null) {
            return CommonResult.failed("email 不存在");
        } else {
            return CommonResult.success(emailList);
        }
    }

    @ApiOperation("获取所有工号")
    @GetMapping("/getEmpIdList")
    public CommonResult<List<String>> getEmpIdList() {
        List<String> empIdList = loginService.getEmpIdList();
        if(empIdList == null) {
            return CommonResult.failed("empId 不存在");
        } else {
            return CommonResult.success(empIdList);
        }
    }


    @ApiOperation("通过工号获取指定邮箱")
    @GetMapping("/getEmailByEmpId")
    public CommonResult<String> getEmailByEmpId(String empId) {
       return loginService.getEmailByEmpId(empId);
    }

    @ApiOperation("更新密码")
    @GetMapping("/updatePassword")
    public CommonResult updatePassword(String password, String email) {
        return loginService.updatePassword(password, email);
    }

}

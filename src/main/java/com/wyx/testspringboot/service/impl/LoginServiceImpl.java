package com.wyx.testspringboot.service.impl;

import com.wyx.testspringboot.MD5.MyMd5;
import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.common.utils.JwtTokenUtil;
import com.wyx.testspringboot.entity.Account;
import com.wyx.testspringboot.dao.LoginMapper;
import com.wyx.testspringboot.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName LogServiceImpl
 * @Author 一熹
 * @Date 2022/7/31 17:06
 * @Version 1.8
 **/
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Resource
    LoginMapper loginMapper;


    @Override
    public CommonResult<Map<String, Object>> loginByEmpId(HttpSession session, String empId, String password) {

        try { //使用token工具类生成token串
            String token = jwtTokenUtil.generateToken(empId, password);
            //根据工号密码查找用户
            Account account = loginMapper.selectAccountByEmpId(empId);

            if (account != null) {
                //账号存在
                String salt = account.getSalt();
                MyMd5 myMd5 = new MyMd5();
                String finalPswd = myMd5.encrypt(myMd5.encrypt(password) + myMd5.encrypt(salt));

                if (Objects.equals(account.getPassword(), finalPswd)) {
                    //密码相等
                    //将用户对象放到session中
                    session.setAttribute("USER_INFO", account);
                    //用户无操作30分钟需重新登录
                    session.setMaxInactiveInterval(30 * 60);

                    Map<String, Object> map = new HashMap<>();
                    map.put("USERINFO", account);
                    map.put("SESSIONID", session.getId());
                    map.put("token", token);
                    return CommonResult.success(map);
                } else {
                    return CommonResult.failed();
                }
            } else {
                return CommonResult.failed();
            }
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult<Map<String, Object>> loginByEmail(HttpSession session, String email, String password) {


        try { //使用token工具类生成token串
            String token = jwtTokenUtil.generateToken(email, password);
            //根据用户名密码查找用户
            Account account = loginMapper.selectAccountByEmail(email);
            if (account != null) {
                //账号存在
                MyMd5 myMd5 = new MyMd5();
                String salt = account.getSalt();

                String finalPswd = myMd5.encrypt(myMd5.encrypt(password) + myMd5.encrypt(salt));

                if (Objects.equals(account.getPassword(), finalPswd)) {
                    //将用户对象放到session中
                    session.setAttribute("USER_INFO", account);
                    //用户无操作30分钟需重新登录
                    session.setMaxInactiveInterval(30 * 60);
                    Map<String, Object> map = new HashMap<>();
                    map.put("USERINFO", account);
                    map.put("SESSIONID", session.getId());
                    map.put("token", token);
                    return CommonResult.success(map);
                } else {
                    return CommonResult.failed();
                }
            } else {
                return CommonResult.failed();
            }
        } catch (Exception e) {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult<String> getEmailByEmpId(String empId) {
        if (empId == null) {
            return CommonResult.failed();
        } else {
            try {
                String email = loginMapper.getEmailByEmpId(empId);
                return CommonResult.success(email);
            } catch (Exception e) {
                LOGGER.warn("未知异常: {}", e.getMessage());
                return CommonResult.failed();
            }
        }
    }

    @Override
    public List<String> getEmailList() {
        return loginMapper.getEmailList();
    }

    @Override
    public CommonResult updatePassword(String password, String email) {
        try {
            Account account = loginMapper.selectAccountByEmail(email);
            if (account != null) {
                MyMd5 myMd5 = new MyMd5();
                String salt = account.getSalt();
                String finalPswd = myMd5.encrypt(myMd5.encrypt(password) + myMd5.encrypt(salt));
                loginMapper.updatePassword(finalPswd, email);
                return CommonResult.success(null, "密码修改成功");
            } else {
                return CommonResult.failed();
            }
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }


    @Override
    public List<String> getEmpIdList() {
        return loginMapper.getEmpIdList();
    }

    @Override
    public CommonResult<Account> judgeLogin(String token, HttpSession session) {
        try {
            String userNameFromToken = jwtTokenUtil.getUserNameFromToken(token);
            System.out.println("userNameFromToken = " + userNameFromToken);
            if (jwtTokenUtil.validateToken(token,userNameFromToken)) {

                return CommonResult.success(null, "登录成功");
            } else {
                return CommonResult.unauthorized(null, "未登录");
            }
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }

    public Account selectAccountByEmail(String email) {
        return loginMapper.selectAccountByEmail(email);
    }

}

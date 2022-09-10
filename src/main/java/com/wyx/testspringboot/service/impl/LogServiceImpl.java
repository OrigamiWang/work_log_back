package com.wyx.testspringboot.service.impl;

import com.wyx.testspringboot.MD5.MyMd5;
import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.dao.PriorityMapper;
import com.wyx.testspringboot.entity.WorkLog;
import com.wyx.testspringboot.dao.LogMapper;
import com.wyx.testspringboot.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Random;

/**
 * @ClassName LogServiceImpl
 * @Author 一熹
 * @Date 2022/7/31 17:06
 * @Version 1.8
 **/
@Service
public class LogServiceImpl implements LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    LogMapper logMapper;

    @Autowired
    PriorityMapper priorityMapper;

    //工号分配按照从小到大
    public static String empId = "0000";

    public static boolean isValid(String empId, String name, String content, Date logDay, Time workTime) {
        String logDayStr = logDay.toString();
        String workTimeStr = workTime.toString();
        String regForWorkTime = "([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
        String regForLogDay = "^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-" +
                "(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-" +
                "(29|30)|(0[13578]|1[02])-31)|([0-9]{2}" +
                "(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]" +
                "|[13579][26])00)-02-29)$";
        return logDayStr.matches(regForLogDay)
                && workTimeStr.matches(regForWorkTime)
                && empId.length() <= 20
                && name.length() <= 20
                && content.length() <= 200;
    }

    public static String addSalt() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            //五位数
            sb.append(str.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    @Override
    public CommonResult<List<WorkLog>> getLogList() {
        try {
            List<WorkLog> logList = logMapper.getLogList();
            return CommonResult.success(logList);
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult deleteByNo(Integer no) {
        try {
            logMapper.deleteByNo(no);
            return CommonResult.success(null, "删除成功");

        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult updateByNo(@RequestParam("no") Integer no,
                                   @RequestParam("empId") String empId,
                                   @RequestParam("name") String name,
                                   @RequestParam("content") String content,
                                   @RequestParam("logDay") Date logDay,
                                   @RequestParam("workTime") Time workTime) {
        try {
            if (isValid(empId, name, content, logDay, workTime)) {
                logMapper.updateByNo(no, empId, name, content, logDay, workTime);
                return CommonResult.success(null, "更新成功");
            }
            return CommonResult.validateFailed("数据无效");
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }

    }

    @Override
    public CommonResult insert(String empId, String name, Date logDay, String content, Time workTime) {
        try {
            if (isValid(empId, name, content, logDay, workTime)) {
                logMapper.insert(empId, name, logDay, content, workTime);
                return CommonResult.success(null, "添加成功");
            }
            return CommonResult.validateFailed("数据无效");
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }


    @Override
    public Integer getCount() {
        try {
            return logMapper.getCount();
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Integer fuzzySelectByEmpId(String empId) {

        try {
            return logMapper.fuzzySelectByEmpId(empId);
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return null;
        }
    }


    @Override
    public CommonResult insertAccount(String email, String password) {
        try {
            //生成盐
            String salt = addSalt();
            //密码加密
            MyMd5 md5 = new MyMd5();
            //加密规则： finalPswd = Md5( Md5(pswd) + Md5(salt) )
            String finalPswd = md5.encrypt(md5.encrypt(password) + md5.encrypt(salt));
            //工号递增
            int empNum = Integer.parseInt(empId) + 1;
            if (empNum < 10) {
                empId = "000" + empNum;
            } else if (empNum < 100) {
                empId = "00" + empNum;
                empId = "0" + empNum;
            }
            //创建账户
            logMapper.insertAccount(empId, email, finalPswd, salt);
            //添加权限
            priorityMapper.insertPermission(empId, "user");
            return CommonResult.success(null, "添加成功");
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }
}

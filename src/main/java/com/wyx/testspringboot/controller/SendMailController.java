package com.wyx.testspringboot.controller;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.config.Swagger2Config;
import com.wyx.testspringboot.entity.Account;
import com.wyx.testspringboot.service.LoginService;
import com.wyx.testspringboot.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @ClassName SendMailController
 * @Author 一熹
 * @Date 2022/7/20 18:54
 * @Version 1.8
 **/
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sendMail")
@Api(tags = {Swagger2Config.TAG_4})
public class SendMailController {


    @Autowired
    private MailService mailService;


    @ApiOperation("发送文本邮件")
    @GetMapping("/sendTextMail")
    public CommonResult<String> sendTextMail(@RequestParam("to") String to) {
        return mailService.sendTextMailMessage(to);
    }

    @ApiOperation("验证码登录")
    @GetMapping("/LoginByVerificationCode")
    public CommonResult<Map<String, Object>> LoginByVerificationCode(HttpSession session, String email) {
        return mailService.LoginByVerificationCode(session, email);
    }

}

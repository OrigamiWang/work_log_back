package com.wyx.testspringboot.controller;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.config.Swagger2Config;
import com.wyx.testspringboot.service.VerificationCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.controller
 * @Author: Origami
 * @Date: 2022/9/4 13:15
 */

//@CrossOrigin(origins = "*")
@RestController
@Api(tags = {Swagger2Config.TAG_5})
@RequestMapping("/verify")
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;


    @ApiOperation("获取验证码")
    @GetMapping("/generateVerificationCode")
    public CommonResult<String> generateVerificationCode(@RequestParam String email) {
        return CommonResult.success(verificationCodeService.generateVerificationCode(email));
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/verifyVerificationCode")
    public CommonResult<String> verifyVerificationCode(@RequestParam String email,
                                                        @RequestParam String verificationCode) {

        return verificationCodeService.verifyVerificationCode(email, verificationCode);
    }



}

package com.wyx.testspringboot.service;

import com.wyx.testspringboot.common.api.CommonResult;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.service
 * @Author: Origami
 * @Date: 2022/9/4 13:18
 */
public interface VerificationCodeService {

    String generateVerificationCode(String email);

    CommonResult<String> verifyVerificationCode(String email, String verificationCode);


}

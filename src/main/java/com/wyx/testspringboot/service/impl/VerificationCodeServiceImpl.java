package com.wyx.testspringboot.service.impl;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.service.RedisService;
import com.wyx.testspringboot.service.VerificationCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.service.impl
 * @Author: Origami
 * @Date: 2022/9/4 13:19
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Value("${redis.key.verification.code}")
    private String REDIS_KEY_VERIFICATION_CODE;

    @Value("${redis.key.verification.expireTime}")
    private String REDIS_KEY_EXPIRE_TIME;

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);

    @Autowired
    private RedisService redisService;


    @Override
    public String generateVerificationCode(String email) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(r.nextInt(10));
        }
        redisService.set(REDIS_KEY_VERIFICATION_CODE + email, sb.toString());
        redisService.expire(REDIS_KEY_VERIFICATION_CODE + email, Integer.parseInt(REDIS_KEY_EXPIRE_TIME));


        LOGGER.info("验证码: {}", sb.toString());
        return sb.toString();
    }

    @Override
    public CommonResult<String> verifyVerificationCode(String email, String verificationCode) {
        //先判断验证码是否过期
        Long expireTime = redisService.isExpire(REDIS_KEY_VERIFICATION_CODE + email);
        LOGGER.info("验证码剩余有效时间(s): {}", expireTime);
        if (expireTime > 0) {
            //再判断验证码是否相等
            boolean isEqual = Objects.equals(redisService.get(REDIS_KEY_VERIFICATION_CODE + email), verificationCode);
            if(isEqual) {
                return CommonResult.success("验证成功！");
            } else {
                return CommonResult.failed("邮箱与验证码不匹配！");
            }
        } else {
            return CommonResult.failed("验证码失效或不存在！");
        }
    }
}

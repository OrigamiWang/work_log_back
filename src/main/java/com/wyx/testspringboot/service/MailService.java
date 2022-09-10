package com.wyx.testspringboot.service;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.common.utils.JwtTokenUtil;
import com.wyx.testspringboot.entity.Account;
import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName JavaMailServiceImpl
 * @Author 一熹
 * @Date 2022/7/20 18:48
 * @Version 1.8
 **/
@Service
public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    private String verificationCode;
    /**
     * 注入邮件工具类
     */
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    VerificationCodeService verificationCodeService;

    @Autowired
    LoginService loginService;

    @Value("${spring.mail.username}")
    private String sendMailer;

    /**
     * 检测邮件信息类
     *
     * @param to
     * @param subject
     * @param text
     */
    private void checkMail(String to, String subject, String text) {
        if (StringUtils.isEmpty(to)) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(subject)) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }


    public CommonResult<String> sendTextMailMessage(String to) {
        System.out.println("to = " + to);
        String subject = "Origami | 验证码";
        String s1 = "【Origami】 你正在进行【邮箱";

        String s = to.split("@")[1];
        int length = to.split("@")[0].length();
        String usernamePrefix = to.split("@")[0].substring(0, 2);
        String usernameSuffix = to.split("@")[0].substring(length - 1, length);
        int starLen = length - 3;
        String stars = "";
        for (int i = 0; i < starLen; i++) {
            stars += "*";
        }
        String s2 = "注册验证】，验证码";
        String s3 = "。提供给他人会导致自己的邮箱被占用和资产损失，若非本人操作，请修改邮箱";
        verificationCode = verificationCodeService.generateVerificationCode(to);

        String text = "" + s1 + usernamePrefix + stars + usernameSuffix + "@" + s + s2 + verificationCode + s3;

        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(text);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            LOGGER.info("发送邮件成功：{}", sendMailer);
            return CommonResult.success(null, "发送邮件成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
            LOGGER.warn("发送邮件失败：{}", e.getMessage());
            return CommonResult.failed("发送邮件失败");
        }
    }

    public CommonResult<Map<String, Object>> LoginByVerificationCode(HttpSession session, String email) {
        try {
            Account account = loginService.selectAccountByEmail(email);
            if (account != null) {
                //账户已注册
                session.setAttribute("USER_INFO", account);
                session.setMaxInactiveInterval(30 * 60);
                String token = jwtTokenUtil.generateToken(email, String.valueOf(verificationCode));
                Map<String, Object> map = new HashMap<>();
                map.put("info", account);
                map.put("token", token);
                map.put("SESSIONID", session.getId());
                return CommonResult.success(map);
            } else {
                //账号未创建
                return CommonResult.failed("账号未创建");
            }
        } catch (Exception e) {
            LOGGER.warn("未知异常: {}", e.getMessage());
            return CommonResult.failed();
        }
    }

}

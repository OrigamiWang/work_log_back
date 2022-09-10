package com.wyx.testspringboot.Component;

import com.wyx.testspringboot.common.utils.IpUtil;
import com.wyx.testspringboot.dto.LogBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.Component
 * @Author: Origami
 * @Date: 2022/9/4 18:57
 */
@Component
@Aspect
@Order(1)
public class MongoDbLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbLogAspect.class);

    @Autowired
    private MongoTemplate mongoTemplate;


    //定义一个切入点
    //切入点表达式
//    execution(方法修饰符 返回类型 方法所属的包.类名.方法名称(方法参数)
    @Pointcut("execution(* com.wyx.testspringboot.controller.LogController.*(..))")
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }


    //@AfterReturning这个注解是返回后通知的注解
    @AfterReturning(value = "logPointCut()", returning = "rtv")
    //JoinPoint是连接点的意思我们要获取到的如类名，方法名，请求参数等都是从连接点中取出来的
    public void afterLog(JoinPoint joinpoint, Object rtv) {
        System.out.println("进去切点。。。。。");
        LogBean logBean = new LogBean();
        logBean.setCreateDate(new Date());
        //获取类名
        String classname = joinpoint.getTarget().getClass().getSimpleName();
        logBean.setClassName(classname);
        //获取方法名
        String method = joinpoint.getSignature().getName();
        logBean.setMethod(method);
        //获取请求参数
        String reqparam = "";
        logBean.setReqParam(reqparam);
        //返回值
        if (rtv != null) {
            logBean.setReqParam(rtv.toString());
        }
        //获取request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
		/*	UserBean user = (UserBean) request.getSession().getAttribute("user");
		if(user!=null){
			logBean.setUserId(user.getId());
		}*/
        //获取ip地址是封装好的一个类
        String ip = IpUtil.getUserIP(request);
        logBean.setIp(ip);
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        logBean.setUsername(user.getName());
        //保存mongodb
        mongoTemplate.save(logBean);
        System.out.println("日志存储成功.........");
    }


}

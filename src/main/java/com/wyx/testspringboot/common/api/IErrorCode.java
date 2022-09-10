package com.wyx.testspringboot.common.api;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.common.api
 * @Author: Origami
 * @Date: 2022/9/3 11:00
 */

/**
 * 封装api的错误码
 */
public interface IErrorCode {

    long getCode();

    String getMessage();
}

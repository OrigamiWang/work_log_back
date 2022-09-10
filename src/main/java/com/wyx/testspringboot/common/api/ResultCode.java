package com.wyx.testspringboot.common.api;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.common.api
 * @Author: Origami
 * @Date: 2022/9/3 11:01
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已过期"),
    FORBIDDEN(401, "没有相关权限");

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    private long code;

    private String message;


    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

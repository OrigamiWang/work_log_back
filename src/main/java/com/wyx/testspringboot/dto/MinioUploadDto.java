package com.wyx.testspringboot.dto;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.dto
 * @Author: Origami
 * @Date: 2022/9/8 8:26
 */

/**
 * 文件上传返回的结果
 */
public class MinioUploadDto {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

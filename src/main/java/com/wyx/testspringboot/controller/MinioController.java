package com.wyx.testspringboot.controller;

import com.wyx.testspringboot.common.api.CommonResult;
import com.wyx.testspringboot.config.Swagger2Config;
import com.wyx.testspringboot.dto.MinioUploadDto;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.controller
 * @Author: Origami
 * @Date: 2022/9/7 22:20
 */

@RestController
@Api(tags = {Swagger2Config.TAG_6})
@RequestMapping("/avatar")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;


    @ApiOperation("文件上传")
    @PostMapping(value = "/upload")
    public CommonResult<MinioUploadDto> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("empId") String empId) {
        try {
            //桶的命名规则： 用于传输头像： avatar + empId
            //创建一个MinIO的Java客户端
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            String objectName = "avatar" + empId;
            // 设置存储对象名称
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            LOGGER.info("文件上传成功!");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName("avatar" + empId);

            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }


    @ApiOperation("加载头像")
    @PostMapping("/load")
    public CommonResult<String> loadAvatar(String empId) {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            String avatarName = "avatar" + empId;
            InputStream avatar = minioClient.getObject("avatar", avatarName);
            return CommonResult.success("用户上传过头像");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return CommonResult.failed("用户未上传过头像! ");
        }
    }

}
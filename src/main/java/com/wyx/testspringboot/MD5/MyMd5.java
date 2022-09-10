package com.wyx.testspringboot.MD5;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @ClassName myMd5
 * @Author 一熹
 * @Date 2022/7/18 21:24
 * @Version 1.8
 **/
public class MyMd5 {

    public String encrypt(String nativePswd) {
        String cipherText = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(nativePswd.getBytes(StandardCharsets.UTF_8));
            cipherText = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cipherText;
    }
}

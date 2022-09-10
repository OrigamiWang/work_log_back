//package com.wyx.testspringboot.common.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ClassName TokenUtil
// * @Author 一熹
// * @Date 2022/8/5 12:06
// * @Version 1.8
// **/
//public class TokenUtil {
//    //设置30分钟过期
//    private static final long EXPIRE_DATE = 30 * 60 * 1000;
//    //token秘钥
//    private static final String TOKEN_SECRET = "Origami";
//
////    private static String username = "zdj" ;
////    private static String password = "123" ;
//
//    public static String tokenTest(String username, String password) {
//
//        String token = "";
//        try {
//            //过期时间
//            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
//            //秘钥及加密算法
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            //设置头部信息
//            Map<String, Object> header = new HashMap<>();
//            header.put("typ", "JWT");
//            header.put("alg", "HS256");
//            //携带username，password信息，生成签名
//            token = JWT.create()
//                    .withHeader(header)
//                    .withClaim("username", username)
//                    .withClaim("password", password).withExpiresAt(date)
//                    .sign(algorithm);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return token;
//    }
//
//    public static boolean verify(String token) {
//        /**
//         * @desc 验证token，通过返回true
//         * @create 2019/1/18/018 9:39
//         * @params [token]需要校验的串
//         **/
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//}

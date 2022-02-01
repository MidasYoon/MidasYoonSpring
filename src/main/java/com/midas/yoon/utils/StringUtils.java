package com.midas.yoon.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class StringUtils extends org.springframework.util.StringUtils {
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }
}

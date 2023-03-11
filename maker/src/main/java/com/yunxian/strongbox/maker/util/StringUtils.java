package com.yunxian.strongbox.maker.util;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/3/11 12:13 PM
 */
public class StringUtils {

    private static final Pattern PATTERN = Pattern.compile("^[!-~]*$");

    /**
     * 判断字符串内容都是可打印的ascii字符
     *
     * @param str 待检查的字符串
     * @return true表示字符串都是可打印字符串，否则不是
     */
    public static boolean isPrintAscii(@NotNull String str) {
        return PATTERN.matcher(str).matches();
    }

}
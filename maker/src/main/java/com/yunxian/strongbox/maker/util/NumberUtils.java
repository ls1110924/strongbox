package com.yunxian.strongbox.maker.util;

import org.jetbrains.annotations.NotNull;

/**
 * 数字工具类
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/3/11 11:37 PM
 */
public class NumberUtils {

    public static long convertLong(@NotNull byte[] arr) {
        long result = 0;
        result |= arr[0];
        for (int i = 1; i < arr.length; i++) {
            result <<= 8;
            result |= (arr[i] & 0xff);
        }
        return result;
    }

    public static byte[] convert2Bytes(int num) {
        byte[] result = new byte[4];
        for (int i = 3; i >= 0; i--) {
            result[i] = (byte) (num & 0xff);
            num >>>= 8;
        }
        return result;
    }

    public static byte[] convert2Bytes(long num) {
        return convert2Bytes(num, 8);
    }

    public static byte[] convert2Bytes(long num, int byteLength) {
        byte[] result = new byte[byteLength];
        for (int i = (byteLength - 1); i >= 0; i--) {
            result[i] = (byte) (num & 0xff);
            num >>>= 8;
        }
        return result;
    }


}

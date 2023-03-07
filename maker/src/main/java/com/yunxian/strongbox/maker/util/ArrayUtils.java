package com.yunxian.strongbox.maker.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 数据相关工具类
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/2/27 10:59 PM
 */
public class ArrayUtils {

    private ArrayUtils() {
        throw new IllegalStateException("couldn't init instance");
    }

    /**
     * 检查两个字节数组是否一致
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return true表示一致，否则不一致
     */
    public static boolean equals(@Nullable byte[] arr1, @Nullable byte[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        } else if (arr1 == null || arr2 == null) {
            return false;
        } else if (arr1.length != arr2.length) {
            return false;
        } else {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static long convertLong(@NotNull byte[] arr) {
        long result = 0;
        result |= arr[0];
        for (int i = 1; i < arr.length; i++) {
            result <<= 8;
            result |= (arr[i] & 0xff);
        }
        return result;
    }

}

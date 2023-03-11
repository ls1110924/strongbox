package com.yunxian.strongbox.maker.util;

import java.util.HashMap;
import java.util.Map;

/**
 * CRC冗余校验的辅助工具类
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/3/11 10:22 PM
 */
public final class CrcUtils {

    /**
     * 获取CRC32的快速查表值
     *
     * @param bytes 查表key
     * @return 查表key对应的快速缓存
     */
    public static byte[] getCrc32Value(byte[] bytes) {
        int crc = 0xffffffff;
        for (byte aByte : bytes) {
            crc = (crc >>> 8) ^ CRC32Cache.CACHE.get((crc & 0xFF) ^ aByte);
        }
        crc ^= 0xffffffff;
        return NumberUtils.convert2Bytes(crc);
    }

    private static class CRC32Cache {
        private static final Map<Integer, Integer> CACHE = new HashMap<>();

        static {
            for (int i = 0; i < 256; i++) {
                int crc = i;
                for (int j = 0; j < 8; j++) {
                    if ((crc & 1) == 1) {
                        crc = (crc >>> 1) ^ 0xEDB88320;
                    } else {
                        crc >>>= 1;
                    }
                }
                CACHE.put(i, crc);
            }
        }
    }

}

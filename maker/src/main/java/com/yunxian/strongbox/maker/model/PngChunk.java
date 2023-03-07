package com.yunxian.strongbox.maker.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Png图片的一个chunk数据块
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/2/13 9:32 PM
 */
public class PngChunk {

    // 数据块长度，4字节（只使用低32位）
    private final byte[] length;
    // 数据块类型码，固定4字节
    private final byte[] type;
    // 数据快，不定长
    private byte[] data;
    // 循环冗余校验，固定4字节
    private final byte[] crc;

    public PngChunk() {
        length = new byte[4];
        type = new byte[4];
        crc = new byte[4];
    }

    @NotNull
    public byte[] getLength() {
        return length;
    }

    @NotNull
    public byte[] getType() {
        return type;
    }

    public void setType(@NotNull byte[] type) {
        copyFourBytes(type, this.type);
    }

    @Nullable
    public byte[] getData() {
        return data;
    }

    public void setData(@NotNull byte[] data) {
        this.data = data;
    }

    @NotNull
    public byte[] getCrc() {
        return crc;
    }

    public void setCrc(@NotNull byte[] crc) {
        copyFourBytes(crc, this.crc);
    }

    private static void copyFourBytes(byte[] src, byte[] dest) {
        if (src != null && src.length == 4) {
            System.arraycopy(src, 0, dest, 0, 4);
        } else {
            throw new IllegalArgumentException("the parameter of byte array is illegal");
        }
    }

}

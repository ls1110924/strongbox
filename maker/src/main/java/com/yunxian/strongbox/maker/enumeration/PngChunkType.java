package com.yunxian.strongbox.maker.enumeration;

import org.jetbrains.annotations.NotNull;

/**
 * PNG图片数据块类型枚举
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/2/13 9:43 PM
 */
public enum PngChunkType {

    HEADER("IHDR", true),
    CARDINAL_BLOCK("cHRM"),
    GAMA("gAMA"),
    SAMPLE_BIT("sBIT"),
    PALETTE("PLTE", true),
    BACKGROUND("bKGD"),
    HISTOGRAM("hIST"),
    TRANSPARENT_BLOCK("tRNS"),
    OFFS("oFFs"),
    PHYSICS_BLOCK("pHYs"),
    SCALE("sCAL"),
    DATA("IDAT", true),
    TIME("tIME"),
    TEXT("tEXt", false),
    // Adobe特有的chunk块类型
    ITXT("iTXt", false),
    ZIP_TEXT("zTXt"),
    FRAC("fRAc"),
    GIF_G("gIFg"),
    GIF_T("gIFt"),
    GIF_X("gIFx"),
    END("IEND", true);

    @NotNull
    public final String type;
    // 是否为关键数据块，true为关键数据块，否则为辅助数据块
    public final boolean critical;

    PngChunkType(@NotNull String type) {
        this(type, false);
    }

    PngChunkType(@NotNull String type, boolean critical) {
        this.type = type;
        this.critical = critical;
    }

}

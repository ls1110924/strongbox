package com.yunxian.strongbox.maker.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PNG图片的Model定义
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/2/13 9:32 PM
 */
public class PngModel implements ImgModel {

    @NotNull
    private final byte[] fileHeader = new byte[8];
    private PngChunk headerChunk = null;
    @NotNull
    private final List<PngChunk> chunks = new ArrayList<>();
    private PngChunk endChunk = null;

    public PngModel() {
    }

    @NotNull
    public byte[] getFileHeader() {
        return fileHeader;
    }

    public void setFileHeader(byte[] fileHeader) {
        if (fileHeader != null && fileHeader.length == 8) {
            System.arraycopy(fileHeader, 0, this.fileHeader, 0, 8);
        } else {
            throw new IllegalArgumentException("the fileHeader parameter is illegal");
        }
    }

    public PngChunk getHeaderChunk() {
        return headerChunk;
    }

    public void setHeaderChunk(PngChunk headerChunk) {
        this.headerChunk = headerChunk;
    }

    @NotNull
    public List<PngChunk> getChunks() {
        return Collections.unmodifiableList(chunks);
    }

    public void setChunks(@NotNull List<PngChunk> chunks) {
        this.chunks.clear();
        this.chunks.addAll(chunks);
    }

    public void appendChunk(@Nullable PngChunk chunk) {
        if (chunk != null) {
            this.chunks.add(chunk);
        }
    }

    public PngChunk getEndChunk() {
        return endChunk;
    }

    public void setEndChunk(PngChunk endChunk) {
        this.endChunk = endChunk;
    }
}

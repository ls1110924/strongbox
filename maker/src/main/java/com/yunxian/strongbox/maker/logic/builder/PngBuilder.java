package com.yunxian.strongbox.maker.logic.builder;

import com.alibaba.fastjson.JSON;
import com.yunxian.strongbox.maker.enumeration.PngChunkType;
import com.yunxian.strongbox.maker.model.PngChunk;
import com.yunxian.strongbox.maker.model.PngModel;
import com.yunxian.strongbox.maker.util.CrcUtils;
import com.yunxian.strongbox.maker.util.NumberUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * PNG格式的保险箱图片生成器
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/3/8 10:49 PM
 */
public class PngBuilder implements IBuilder<PngModel> {

    @Override
    public boolean build(@NotNull String dest, @NotNull PngModel model, @NotNull Map<String, String> params) {
        if (!preCheck(params)) {
            throw new IllegalArgumentException("the params char is illegal, only support print ascii, plz check");
        }
        byte[] paramsBytes = JSON.toJSONString(params).getBytes(StandardCharsets.UTF_8);

        PngChunk txtChunk = new PngChunk();
        System.arraycopy(NumberUtils.convert2Bytes(paramsBytes.length), 0, txtChunk.getLength(), 0, 4);
        txtChunk.setType(PngChunkType.ITXT.type.getBytes(StandardCharsets.UTF_8));
        txtChunk.setData(paramsBytes);

        byte[] crcContent = new byte[paramsBytes.length + 4];
        System.arraycopy(txtChunk.getType(), 0, crcContent, 0, 4);
        System.arraycopy(paramsBytes, 0, crcContent, 4, paramsBytes.length);
        txtChunk.setCrc(CrcUtils.getCrc32Value(crcContent));

        try (OutputStream output = new FileOutputStream(dest)) {
            // 系统文件头
            output.write(model.getFileHeader());

            // 文件头
            writeChunk(output, model.getHeaderChunk());

            // 文件体
            for (PngChunk chunk : model.getChunks()) {
                writeChunk(output, chunk);
            }

            // 密钥体
            writeChunk(output, txtChunk);

            // 结尾
            writeChunk(output, model.getEndChunk());

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private static void writeChunk(OutputStream output, PngChunk chunk) throws IOException {
        output.write(chunk.getLength());
        output.write(chunk.getType());
        if (chunk.getData() == null) {
            throw new IllegalArgumentException("the data of chunk shouldn't be null");
        }
        output.write(chunk.getData());
        output.write(chunk.getCrc());
    }

}

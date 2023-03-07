package com.yunxian.strongbox.maker.logic.parser;

import com.yunxian.strongbox.maker.enumeration.PngChunkType;
import com.yunxian.strongbox.maker.model.PngChunk;
import com.yunxian.strongbox.maker.model.PngModel;
import com.yunxian.strongbox.maker.util.ArrayUtils;
import com.yunxian.strongbox.maker.util.Constants;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * PNG图片解析器
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/2/14 10:30 PM
 */
public class PngParser implements IParser<PngModel> {

    @Nullable
    @Override
    public PngModel parse(String filepath) {
        if (filepath == null || filepath.length() == 0) {
            throw new IllegalArgumentException("the filepath parameter is illegal");
        }
        try (InputStream input = new BufferedInputStream(new FileInputStream(filepath))) {
            PngModel pngModel = new PngModel();

            // 检查header
            int readLen = input.read(pngModel.getFileHeader());
            if (readLen != 8 || !ArrayUtils.equals(pngModel.getFileHeader(), Constants.PNG_HEADER)) {
                throw new IllegalArgumentException("the file isn't a illegal file");
            }

            while (true) {
                PngChunk chunk = new PngChunk();
                readLen = input.read(chunk.getLength());
                if (readLen != 4) {
                    throw new IllegalArgumentException("the file isn't a illegal file");
                }
                readLen = input.read(chunk.getType());
                if (readLen != 4) {
                    throw new IllegalArgumentException("the file isn't a illegal file");
                }
                long chunkLen = ArrayUtils.convertLong(chunk.getLength());
                ByteArrayOutputStream dataOutput = new ByteArrayOutputStream();
                for (int i = 0; i < chunkLen; i++) {
                    int c = input.read();
                    if (c == -1) {
                        throw new IllegalArgumentException("the file isn't a illegal file");
                    }
                    dataOutput.write(c);
                }
                chunk.setData(dataOutput.toByteArray());

                readLen = input.read(chunk.getCrc());
                if (readLen != 4) {
                    throw new IllegalArgumentException("the file isn't a illegal file");
                }
                pngModel.appendChunk(chunk);

                if (PngChunkType.END.type.equals(new String(chunk.getType(), StandardCharsets.US_ASCII))) {
                    // 正常结束
                    if (input.read() != -1) {
                        throw new IllegalArgumentException("the file isn't a illegal file");
                    }
                    break;
                }
            }

            return pngModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

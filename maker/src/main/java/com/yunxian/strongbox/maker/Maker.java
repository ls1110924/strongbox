package com.yunxian.strongbox.maker;

import com.yunxian.strongbox.maker.logic.builder.IBuilder;
import com.yunxian.strongbox.maker.logic.builder.PngBuilder;
import com.yunxian.strongbox.maker.logic.parser.IParser;
import com.yunxian.strongbox.maker.logic.parser.PngParser;
import com.yunxian.strongbox.maker.model.PngModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Maker {

    public static void main(String[] args) {
        String path = Maker.class.getClassLoader().getResource("template/box-sec.png").getPath();

        IParser<PngModel> parser = new PngParser();
        PngModel pngModel = parser.parse(path);
        System.out.println(pngModel.getChunks().size());

        File sourceFile = new File(path);

        IBuilder<PngModel> builder = new PngBuilder();
        Map<String, String> params = new HashMap<>();
        params.put("appId", "00x01");
        params.put("des", "x1gfw214ga1231");
        builder.build(new File(sourceFile.getParentFile(), "box-sec-output.png").getAbsolutePath(), pngModel, params);

    }

}
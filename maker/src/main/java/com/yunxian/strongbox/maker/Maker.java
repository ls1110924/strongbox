package com.yunxian.strongbox.maker;

import com.yunxian.strongbox.maker.logic.builder.IBuilder;
import com.yunxian.strongbox.maker.logic.builder.PngBuilder;
import com.yunxian.strongbox.maker.logic.parser.IParser;
import com.yunxian.strongbox.maker.logic.parser.PngParser;
import com.yunxian.strongbox.maker.model.PngModel;
import com.yunxian.strongbox.maker.model.SecretKeyModel;

import java.io.File;

public class Maker {

    public static void main(String[] args) {
        String path = Maker.class.getClassLoader().getResource("template/box-sec.png").getPath();

        IParser<PngModel> parser = new PngParser();
        PngModel pngModel = parser.parse(path);
        System.out.println(pngModel.getChunks().size());

        File sourceFile = new File(path);

        SecretKeyModel keyModel = new SecretKeyModel();
        keyModel.setAppId("100");
        keyModel.appendIndexKey("123213");
        keyModel.appendParamKey("appId", "00x01");
        keyModel.appendParamKey("des", "x1gfw214ga1231");
        IBuilder<PngModel> builder = new PngBuilder();
        builder.build(new File(sourceFile.getParentFile(), "box-sec-output.png").getAbsolutePath(), pngModel, keyModel);

    }

}
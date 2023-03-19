package com.yunxian.strongbox.maker.logic.builder;

import com.yunxian.strongbox.maker.model.ImgModel;
import com.yunxian.strongbox.maker.model.SecretKeyModel;
import com.yunxian.strongbox.maker.util.StringUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * 保险箱图片构造器
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/3/8 10:32 PM
 */
public interface IBuilder<T extends ImgModel> {

    default boolean preCheck(@NotNull Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!StringUtils.isPrintAscii(entry.getKey()) || !StringUtils.isPrintAscii(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    boolean build(@NotNull String dest, @NotNull T model, @NotNull SecretKeyModel keyModel);

}

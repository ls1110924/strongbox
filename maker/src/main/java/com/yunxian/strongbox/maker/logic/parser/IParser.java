package com.yunxian.strongbox.maker.logic.parser;

import com.yunxian.strongbox.maker.model.ImgModel;

import org.jetbrains.annotations.Nullable;

/**
 * 解析器接口定义
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/2/14 10:34 PM
 */
public interface IParser<T extends ImgModel> {

    @Nullable
    T parse(String filepath);

}

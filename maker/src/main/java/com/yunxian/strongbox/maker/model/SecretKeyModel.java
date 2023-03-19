package com.yunxian.strongbox.maker.model;

import com.yunxian.strongbox.maker.util.StringUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 密钥Model
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/3/19 7:19 PM
 */
public class SecretKeyModel {

    private String appId;

    // 位置索引密钥
    private final List<String> indexKeys = new ArrayList<>();

    // 参数索引密钥
    private final Map<String, String> paramKeys = new HashMap<>();

    /**
     * 密钥Model数据是否合法
     *
     * @return true表示密钥数据合法，否则非法
     */
    public boolean isLegal() {
        return !StringUtils.isEmpty(appId) && (indexKeys.size() != 0 || paramKeys.size() != 0);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(@NotNull String appId) {
        this.appId = appId;
    }

    @NotNull
    public List<String> getIndexKeys() {
        return Collections.unmodifiableList(indexKeys);
    }

    public String getPositionIndexKey(int position) {
        if (position >= 0 && position < indexKeys.size()) {
            return indexKeys.get(position);
        } else {
            throw new IndexOutOfBoundsException("there isn't exist the positioned key which index is " + position);
        }
    }

    public void setIndexKeys(@NotNull List<String> indexKeys) {
        this.indexKeys.clear();
        this.indexKeys.addAll(indexKeys);
    }

    public void appendIndexKey(@NotNull String key) {
        this.indexKeys.add(key);
    }

    @NotNull
    public Map<String, String> getParamKeys() {
        return paramKeys;
    }

    @Nullable
    public String getParamKey(@NotNull String param) {
        return getParamKey(param, null);
    }

    @Nullable
    public String getParamKey(@NotNull String param, String defaultValue) {
        String value = paramKeys.get(param);
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    public void setParamKeys(@NotNull Map<String, String> paramKeys) {
        this.paramKeys.clear();
        this.paramKeys.putAll(paramKeys);
    }

    public void appendParamKey(@NotNull String key, @NotNull String value) {
        String preValue = paramKeys.put(key, value);
        if (!StringUtils.isEmpty(preValue)) {
            throw new IllegalArgumentException("there is exist the same key, which value is " + value);
        }
    }

}

package com.yunxian.strongbox.maker.util;

/**
 * 常量类
 *
 * @author A Shuai
 * @email ls1110924@gmail.com
 * @date 2023/2/27 11:09 PM
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("couldn't init instance");
    }

    // PNG图片的文件头
    public static final byte[] PNG_HEADER = {
            (byte) 0x89,    // 检测传输系统是否支持8位字符编码
            (byte) 0x50, (byte) 0x4E, (byte) 0x47,  // PNG字母对应的ASCII码值
            (byte) 0x0D, (byte) 0x0A,   // DOS风格的换行符
            (byte) 0x1A,    // DOS命令下，阻止文件显示的文件结束符
            (byte) 0x0A     // Unix风格的换行符
    };

}

package com.pramyness.demo;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/7.
 */
public class Test
{

    public static void main(String[] args)
    {
        // String regEx = "[1]?";

        String words = "にほんご（かな）ニホンゴ（カナ）1sdfasdfasdf您的说法撒的发生的阿斯顿发斯蒂芬dsdddd#￥%@#%￥@#%￥";
        String result = patternZh(words);
        System.out.println(result);
    }

    private static String patternZh(String words)
    {
        String regEx = "[\u4e00-\u9fa5]?"; // 匹配中文字符的正则表达式
//         String regEx = "[^\\x00-\\xff]?"; //匹配双字节字符(包括汉字在内)
        Pattern pattern = Pattern.compile(regEx, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(words);
        StringBuffer strBuf = new StringBuffer(0);
        while (matcher.find())
        {
            if (StringUtils.isNotBlank(matcher.group()))
            {
                strBuf.append(matcher.group());
            }
        }
        return strBuf.toString();
    }
}
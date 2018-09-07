package com.pramyness.demo.handler;

import com.pramyness.demo.Order;
import org.apache.commons.lang3.StringUtils;

import java.nio.CharBuffer;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public class WordHandler extends AbstractHandler {

    private int count;

    private boolean isContinue = false;

    @Override
    public Order getOrder() {
        return Order.W;
    }

    @Override
    public void doHandle(char[] chars, int len) {
//        String s = line.replaceAll("[^a-zA-Z0-9_\\-\\u4e00-\\u9fa5]"," ");
//        String s = line.replaceAll("[\\p{Nd}\\u9fa5-\\uffe5\\p{Punct}\\s&&[^-]]"," ");
//        count+=StringUtils.split(s," ").length;
//        System.out.println(chars.length+","+len);
        for (int i = 0; i < len; i++) {

            if (i == 0 && isContinue) {
                if (!Character.isLetter(chars[0]) && chars[i] != '-' && chars[i] != '_') {
                    increase();
                }
            }


            if (Character.isLetter(chars[i])) {
                if (isChinese(chars[i])) {
                    if (isContinue) {
                        increase();
                    }
                    increase();
                } else {
                    while (i < len && isEnglish(chars[i])) {
                        i++;
                    }
                    if (i == len) {
                        isContinue = true;
                    } else {
                        increase();
                    }
                }
            }
        }

    }

    private void increase() {
        count++;
        isContinue = false;
    }

    private boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    private boolean isEnglish(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '-' || c == '_';
    }


    @Override
    public void doGet() {
        System.out.println("单词总数:" + count);
    }

    @Override
    protected void doClear() {
        count = 0;
        isContinue = false;
    }

}

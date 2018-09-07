package com.pramyness.demo.handler;

import com.pramyness.demo.Order;
import com.pramyness.demo.handler.base.AbstractHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public class WordHandler extends AbstractHandler {

    private int count;


    @Override
    public Order getOrder() {
        return Order.W;
    }

    @Override
    public void doHandle(String line) {
        String s = line.replaceAll("[\\p{Nd}\\u9fa5-\\uffe5\\p{Punct}\\s&&[^-]]", " ");
        count += StringUtils.split(s, " ").length;
    }


    @Override
    public void doGet() {
        System.out.println("单词总数:" + count);
    }

    @Override
    protected void doClear() {
        count = 0;
    }

}

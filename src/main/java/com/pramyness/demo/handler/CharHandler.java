package com.pramyness.demo.handler;

import com.pramyness.demo.Order;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public class CharHandler extends AbstractHandler {


    private long count = 0;


    @Override
    public Order getOrder() {
        return Order.C;
    }

    @Override
    public void doHandle(char[] chars , int len) {
        count+=len;
    }

    @Override
    public void doGet() {
        System.out.println("字符数目:"+count);
    }

    @Override
    protected void doClear() {
        count=0;
    }

}

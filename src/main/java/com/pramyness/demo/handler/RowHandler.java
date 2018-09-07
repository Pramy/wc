package com.pramyness.demo.handler;

import com.pramyness.demo.Order;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public class RowHandler extends AbstractHandler {

    private int sum =1;
    @Override
    public Order getOrder() {
        return Order.L;
    }


    @Override
    public void doHandle(char[] chars , int len) {
        for (int i = 0; i < len; i++) {
            if (chars[i] == '\n') {
                sum++;
            }
        }
    }

    @Override
    public void doGet() {
        System.out.println("行数:"+sum);
    }

    @Override
    protected void doClear() {
        sum=1;
    }
}

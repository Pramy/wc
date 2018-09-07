package com.pramyness.demo.handler;

import com.pramyness.demo.handler.base.AbstractHandler;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public class LineHandler extends AbstractHandler {

    private int sum ;


    @Override
    public void doHandle(String line) {
        sum++;
    }

    @Override
    public void doGet() {
        System.out.println("行数:"+sum);
    }

    @Override
    protected void doClear() {
        sum=0;
    }
}

package com.pramyness.demo.handler;

import com.pramyness.demo.handler.base.AbstractHandler;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public class CharHandler extends AbstractHandler {


    private long count = 0;


    @Override
    public void doHandle(String line) {
        count += line.length() + System.lineSeparator().length();
    }

    @Override
    public void doGet() {
        System.out.println("字符数目:" + count);
    }

    @Override
    protected void doClear() {
        count = 0;
    }

}

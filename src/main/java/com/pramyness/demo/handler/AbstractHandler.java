package com.pramyness.demo.handler;

import lombok.Getter;
import lombok.Setter;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public abstract class AbstractHandler implements Handler{

    @Getter
    @Setter
    private Handler next;

    @Override
    public void handle(char[] chars , int len) {
        doHandle(chars, len);
        if (next != null) {
            next.handle(chars, len);
        }
    }

    @Override
    public void get() {
        doGet();
        if (next != null) {
            next.get();
        }
    }

    @Override
    public void clear() {
        doClear();
        if (next != null) {
            next.clear();
        }
    }


    protected abstract void doHandle(char[] chars , int len);

    protected abstract void doGet();

    protected abstract void doClear();
}

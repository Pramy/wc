package com.pramyness.demo.handler.base;

import com.pramyness.demo.handler.base.Handler;
import lombok.Getter;
import lombok.Setter;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public abstract class AbstractHandler implements Handler {

    @Setter
    private Handler next;

    @Override
    public void handle(String line) {
        doHandle(line);
        if (next != null) {
            next.handle(line);
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


    protected abstract void doHandle(String line);

    protected abstract void doGet();

    protected abstract void doClear();
}

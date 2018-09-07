package com.pramyness.demo.handler.base;

import com.pramyness.demo.Order;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public interface Handler {

    Order getOrder();

    void handle(String line);

    void get();

    void clear();
}

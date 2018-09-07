package com.pramyness.demo.handler;

import com.pramyness.demo.AppTest;
import com.pramyness.demo.handler.base.Handler;
import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/7.
 */
public class RowHandlerTest extends AppTest {

    private Handler handler = new RowHandler();

    @Test
    public void doHandle() throws IOException {

        init(TEST_PATH+"handler/RowHandlerTest.java");
        handle(handler);
    }

    @After
    public void doGet() {
        handler.get();
    }
}
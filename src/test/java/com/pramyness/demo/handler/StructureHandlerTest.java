package com.pramyness.demo.handler;

import com.pramyness.demo.AppTest;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/7.
 */
public class StructureHandlerTest extends AppTest {

    private final StructureHandler handler = new StructureHandler();

    @Test
    public void doHandle() throws IOException {
        init(TEST_PATH+"AppTest.java");
        handle(handler);
    }

    @After
    public void print() {
        handler.get();
    }

}
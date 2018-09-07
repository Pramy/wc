package com.pramyness.demo.handler;

import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.Assert.*;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
public class CharHandlerTest {

    private CharHandler charHandler = new CharHandler();

    @Test
    public void doHandle() throws IOException {
        System.out.println(Integer.toBinaryString(20013));
    }

    @After
    public void doGet() {
        charHandler.get();
    }
}
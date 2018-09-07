package com.pramyness.demo;

import com.pramyness.demo.handler.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

    private static final Map<String, Handler> MAP;

    private static boolean IS_RECURSION = false;

    private static String NAME;

    private static Handler HEAD;

    static {
        MAP = new HashMap<>();
        MAP.put(Order.L.getCommand(), new RowHandler());
        MAP.put(Order.C.getCommand(), new CharHandler());
        MAP.put(Order.W.getCommand(), new WordHandler());
    }

    public static void main(String[] args) throws IOException {
        File file = build(args);
        String name = file.getName();
        if (file.isDirectory()) {
            NAME = ".*";
        } else {
            file = file.getParentFile();
            NAME = name.replaceAll("\\.", "\\\\.").replaceAll("[*?]", ".*");
        }
        if (file == null|| !file.exists()){
            throw new RuntimeException("文件:不存在");
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                read(file1);
            }
        }
    }

    private static void read(File file) throws IOException {
        if (file.isFile() && file.getName().matches(NAME) && file.canRead()) {
            System.out.println(file.getPath());
            FileReader fileReader = new FileReader(file);
            BufferedReader bf = new BufferedReader(fileReader);
            char[] chars = new char[1024];
            int len;
            if (HEAD != null) {
                while ((len = bf.read(chars)) != -1) {
                    HEAD.handle(chars, len);
                }
                HEAD.get();
                HEAD.clear();
            }

        }
        if (IS_RECURSION) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    read(file1);
                }
            }
        }
    }

    private static File build(String[] args) {
        Handler pos = null;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(Order.S.getCommand())) {
                IS_RECURSION = true;
            } else {
                Handler handler = MAP.get(args[i]);
                if (handler != null) {
                    if (HEAD == null) {
                        HEAD = handler;
                    } else if (pos instanceof AbstractHandler) {
                        ((AbstractHandler) pos).setNext(handler);
                    }
                    pos = handler;
                } else {
                    throw new RuntimeException("参数:" + args[i] + "无效");
                }
            }
        }
        return new File(args[args.length - 1]);
    }
}

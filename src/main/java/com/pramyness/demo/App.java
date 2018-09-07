package com.pramyness.demo;

import com.pramyness.demo.handler.*;
import com.pramyness.demo.handler.base.AbstractHandler;
import com.pramyness.demo.handler.base.Handler;

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
        File file = new File(App.class.getResource("/").getPath()+"com/pramyness/demo/handler");
        System.out.println(file.getPath());
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    String name = file1.getName();
                    name = name.substring(0,name.indexOf("."));
                    try {
                        Object o = Class.forName("com.pramyness.demo.handler."+name).newInstance();
                        if (o instanceof Handler) {
                            MAP.put(((Handler) o).getOrder().getCommand(), (Handler) o);
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
        if (file == null || !file.exists()) {
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
            String s;
            if (HEAD != null) {
                while ((s = bf.readLine()) != null) {
                    HEAD.handle(s);
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
        return args.length >= 1 ? new File(args[args.length - 1]) : new File("");
    }
}

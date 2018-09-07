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

    //将处理器存在map中
    static {
        MAP = new HashMap<>();
        MAP.put(Order.L.getCommand(),new LineHandler());
        MAP.put(Order.W.getCommand(),new WordHandler());
        MAP.put(Order.C.getCommand(),new CharHandler());
        MAP.put(Order.A.getCommand(),new StructureHandler());
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

    /**
     * 处理文件
     * @param file 需要分析的文件
     * @throws IOException io
     */
    private static void read(File file) throws IOException {
        if (file.isFile() && file.getName().matches(NAME) && file.canRead()) {
            System.out.println(file.getPath());
            FileReader fileReader = new FileReader(file);
            BufferedReader bf = new BufferedReader(fileReader);
            String s;
            if (HEAD != null) {
                while ((s = bf.readLine()) != null) {
                    //1.将读到的一行字符串从头带尾依次处理
                    HEAD.handle(s);
                }
                //2.处理完成后获取处理结果
                HEAD.get();
                //3.清除处理结果以便接下来的处理
                HEAD.clear();
            }

        }
        //判断是否要递归处理
        if (IS_RECURSION) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    read(file1);
                }
            }
        }
    }

    /**
     * 构建处理链,然后分析是否要递归查找
     * @param args orders
     * @return file
     */
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

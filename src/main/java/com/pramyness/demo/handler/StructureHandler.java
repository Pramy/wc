package com.pramyness.demo.handler;

import com.pramyness.demo.handler.base.AbstractHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/7.
 */
public class StructureHandler extends AbstractHandler {
    //空行
    private int emptyLine;
    //代码行
    private int codeLine;
    //注释行
    private int annotationLine;
    //注释开始标志
    private boolean isContinue;

    /**
     * @param line line
     */
    @Override
    protected void doHandle(String line) {
        //如果进入了多行注释/* 那必须得匹配*/才可以结束,并且/* */中间的所有行都是注释行
        if (isContinue) {
            if (line.matches(".*\\*/\\s*")) {
                isContinue = false;
            }
            annotationLine++;
        } else {
            //匹配全部是空格或格式控制字符，如果包括代码，则只有不超过一个可显示的字符
            if (StringUtils.isAllBlank(line) || line.matches("\\s*\\p{Graph}\\s*")) {
                emptyLine++;
            //匹配单行注释// 和/* */
            } else if (line.matches("\\s*?\\s*//.*") || line.matches(".*/\\*.*\\*/.*")) {
                annotationLine++;
            //匹配/*
            } else if (line.matches(".*/\\*.*")) {
                isContinue = true;
                annotationLine++;
            //匹配代码行
            } else {
                codeLine++;
            }
        }
    }
;
    @Override
    protected void doGet() {
        System.out.println("空白行:" + emptyLine);
        System.out.println("注释行:" + annotationLine);
        System.out.println("代码行:" + codeLine);
    }

    @Override
    protected void doClear() {
        emptyLine=0;
        annotationLine=0;
        codeLine=0;
        isContinue=false;
    }

}

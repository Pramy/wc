package com.pramyness.demo.handler;

import com.pramyness.demo.Order;
import com.pramyness.demo.handler.base.AbstractHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/7.
 */
public class StructureHandler extends AbstractHandler {

    private int emptyLine;

    private int codeLine;

    private int annotationLine;

    private boolean isContinue;

    /**
     * @param line
     */
    @Override
    protected void doHandle(String line) {

        if (isContinue) {
            if (line.matches(".*\\*/\\s*")) {
                isContinue = false;
            }
            annotationLine++;
        } else {
            if (StringUtils.isAllBlank(line) || line.matches("\\s*\\p{Graph}\\s*")) {
                emptyLine++;
            } else if (line.matches("\\s*}\\s*//.*") || line.matches(".*/\\*.*\\*/.*")) {
                annotationLine++;
            } else if (line.matches(".*/\\*.*")) {
                isContinue = true;
                annotationLine++;
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

    @Override
    public Order getOrder() {
        return Order.A;
    }

}

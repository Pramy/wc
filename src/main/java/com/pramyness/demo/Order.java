package com.pramyness.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2018/9/6.
 */
@Getter
@AllArgsConstructor
public enum Order {

    C("-c","字符数"),W("-w","词的数目"),L("-l","行数"),
    S("-s","递归"),A("-a","");

    private final String command;

    private final String msg;
}

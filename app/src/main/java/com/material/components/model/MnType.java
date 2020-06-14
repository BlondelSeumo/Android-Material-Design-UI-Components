package com.material.components.model;


/**
 * Menu type for MainMenu.java
 */
public enum MnType {

    NOR(1001), HEAD(1002), SUB(1003), DIV(1004);

    private final int value;

    MnType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

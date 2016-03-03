package com.bookvideo.library.domain;

public class Link {
    public static final Type defaultType = Type.WELCOME;
    private Type type;
    private String target;
    private String data;

    public Link() {

    }

    public Type getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public String getData() {
        return data;
    }

    public enum Type {
        WELCOME, DASHBOARD, CONTENT, MENU
    }
}

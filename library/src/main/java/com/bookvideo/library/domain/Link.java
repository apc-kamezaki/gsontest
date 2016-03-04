package com.bookvideo.library.domain;

public class Link {
    public static final DocumentType defaultType = DocumentType.WELCOME;
    private DocumentType type;
    private String target;
    private String data;

    public DocumentType getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public String getData() {
        return data;
    }

}

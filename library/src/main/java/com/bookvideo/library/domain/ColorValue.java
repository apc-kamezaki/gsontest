package com.bookvideo.library.domain;

public class ColorValue {
    private int color;

    ColorValue(int value) {
        this.color = value;
    }

    public int getColor() {
        return color;
    }

    public static ColorValue getColorInstance(String str) {
        return new ColorValue(ColorUtil.fromString(str));
    }
}
